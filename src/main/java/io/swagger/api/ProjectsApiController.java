package io.swagger.api;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;

import io.swagger.model.Error;
import io.swagger.model.Feature;
import io.swagger.model.FeatureData;
import io.swagger.model.FeatureId;

import java.util.ArrayList;
import java.util.List;
import io.swagger.model.NewFeatureData;
import io.swagger.model.NewProjectData;
import io.swagger.model.NewRelease;
import io.swagger.model.Plan;
import io.swagger.model.Project;
import io.swagger.model.ProjectData;
import io.swagger.model.Release;
import io.swagger.model.ReleaseData;
import io.swagger.model.Resource;
import io.swagger.model.ResourceData;
import io.swagger.model.ResourceId;
import io.swagger.model.Skill;
import io.swagger.model.SkillData;
import io.swagger.model.SkillId;

import io.swagger.annotations.*;

import org.joda.time.DateTime;
import org.joda.time.Weeks;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringCodegen", date = "2017-04-10T09:10:24.462Z")

@Controller
public class ProjectsApiController implements ProjectsApi {
	

	Connection dbConnection = null;
	String url = "jdbc:mysql://localhost:3306/DataBaseController";
	String userName = "root"; 
	String password = "Thelouk09002";
	
	public Connection getConnection() {
		Connection connection = null;
		/*System.out.println("-------- PostgreSQL "
				+ "JDBC Connection Testing ------------");*/
		
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {

			System.out.println("Where is your PostgreSQL JDBC Driver? "
					+ "Include in your library path!");
			e.printStackTrace();
			return connection;
		}

		//System.out.println("PostgreSQL JDBC Driver Registered!");
		try {
			connection = DriverManager.getConnection(url, userName,password);

		} catch (SQLException e) {

			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return connection;
		}

		if (connection != null) {
			//System.out.println("You made it, take control your database now!");
		} else {
			System.out.println("Failed to make connection!");
		}
		return connection;
	}
	
	public String transformProjectId(String projectId) {		
		if (projectId.equals("siemens")) return "1"; 
		else if (projectId.equals("senercon")) return "2";
		else if (projectId.equals("atos")) return "3";
		else return projectId;
	}

    public ResponseEntity<Feature> addDependenciesToFeature(@ApiParam(value = "ID of the project (e.g. \"1\" or \"siemens\")",required=true ) @PathVariable("projectId") String projectId,
        @ApiParam(value = "ID of the realese",required=true ) @PathVariable("featureId") BigDecimal featureId,
        @ApiParam(value = "Array of Feature ids" ,required=true ) @RequestBody List<FeatureId> body) {
        // do some magic!
    	projectId = transformProjectId(projectId);
    	Connection con = null;
    	Feature feature = new Feature();
		try {
			con = getConnection();
			
			for (int i = 0; i < body.size(); ++i) {
				FeatureId fId = body.get(i);
				Integer f = fId.getFeatureId();
				String query = "INSERT INTO featuresDependencies (id_feature, dependencies) VALUES (" + featureId + "," + f + ")"; 
				System.out.println("QUERY: " + query);
				PreparedStatement statement = con.prepareStatement(query);
				statement.execute();
			}
			
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from features where idProject = " + projectId + " and id = " + featureId);

			while (rs.next()){
				int id = rs.getInt("id");
				int code = rs.getInt("code");
				String name = rs.getString("name");
				String description = rs.getString("description");
				BigDecimal effort = rs.getBigDecimal("effort");
				Date deadline = rs.getDate("deadline");
				int priority = rs.getInt("priority");
				
				feature.setId(id);
				feature.setCode(code);
				feature.setName(name);
				feature.setDescription(description);
				feature.setEffort(effort);
				feature.setDeadline(deadline);
				feature.setPriority(priority);
			}	
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
        HttpHeaders responseHeaders = new HttpHeaders();    
        return new ResponseEntity<Feature>(feature, responseHeaders, HttpStatus.OK);
    }

    public ResponseEntity<Void> addFeaturesToRelease(@ApiParam(value = "ID of the project (e.g. \"1\" or \"siemens\")",required=true ) @PathVariable("projectId") String projectId,
        @ApiParam(value = "ID of the realese",required=true ) @PathVariable("releaseId") BigDecimal releaseId,
        @ApiParam(value = "Array of Feature ids" ,required=true ) @RequestBody List<FeatureId> body) {
        // do some magic!
    	projectId = transformProjectId(projectId);
    	Connection con = null;
    	
		try {
			con = getConnection();
			
			for (int i = 0; i < body.size(); ++i) {
				FeatureId f = body.get(i);
				Integer featureId = f.getFeatureId();
				String query = "UPDATE features SET id_release = " + releaseId + " WHERE id = " + featureId +  " AND idProject = " + projectId;
				//System.out.println("QUERY: " + query);
				PreparedStatement statement = con.prepareStatement(query);
				statement.execute();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
        HttpHeaders responseHeaders = new HttpHeaders();    
        return new ResponseEntity<Void>(HttpStatus.OK); //COMENTAR PROFES
    }

    public ResponseEntity<Release> addNewReleaseToProject(@ApiParam(value = "ID of the project (e.g. \"1\" or \"siemens\")",required=true ) @PathVariable("projectId") String projectId,
        @ApiParam(value = "Parameters of the new Release" ,required=true ) @RequestBody NewRelease body) {
        // do some magic!
    	String name = body.getName();
    	String description = body.getDescription();
    	DateTime startsAt = body.getStartsAt();
    	DateTime deadline = body.getDeadline();
    	Release release = new Release();
    	
    	projectId = transformProjectId(projectId);
    	Connection con = null;

		try {
			con = getConnection();
			
			PreparedStatement statement = con.prepareStatement("INSERT INTO releases (idProject, name, description, starts_at, deadline)  VALUES ('"+ projectId +"','"+ name +"','"+ description + "','"+ startsAt + "','" + deadline +"')", Statement.RETURN_GENERATED_KEYS);
			int affectedRows = statement.executeUpdate();

			Integer idReleaseResultant = null;
			try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	            	System.out.println("ID " + generatedKeys.getInt(1));
	            	idReleaseResultant = generatedKeys.getInt(1);
	                //user.setId(generatedKeys.getLong(1));
	            }
	            else {
	                throw new SQLException("Create failed");
	            }
	        }	
			
			release.setId(idReleaseResultant);
			release.setName(name);
			release.setDescription(description);

			LocalDate date = startsAt.toDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			Date startsat = Date.valueOf(date);
			release.startsAt(startsat);
			
			LocalDate date2 = deadline.toDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			Date deadlineAux = Date.valueOf(date2);
			release.deadline(deadlineAux);
			
			System.out.println(startsat + "    " + deadlineAux);
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
        HttpHeaders responseHeaders = new HttpHeaders();    	
        return new ResponseEntity<Release>(release, responseHeaders, HttpStatus.OK);
    }

    public ResponseEntity<Resource> addNewResourceToProject(@ApiParam(value = "ID of the project (e.g. \"1\" or \"siemens\")",required=true ) @PathVariable("projectId") String projectId,
        @ApiParam(value = "Resource parameters" ,required=true ) @RequestBody ResourceData body) {
        // do some magic!
    	String name = body.getName();
    	String description = body.getDescription();
    	BigDecimal availability = body.getAvailability();
    	Resource resource = new Resource();
    	
    	projectId = transformProjectId(projectId);
    	Connection con = null;

		try {
			con = getConnection();
			
			PreparedStatement statement = con.prepareStatement("INSERT INTO resources (idProject, name, description, availability)  VALUES ('"+ projectId +"','"+ name +"','"+ description + "','"+ availability + "')", Statement.RETURN_GENERATED_KEYS);
			int affectedRows = statement.executeUpdate();

			Integer idResourceResultant = null;
			try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	            	System.out.println("ID " + generatedKeys.getInt(1));
	            	idResourceResultant = generatedKeys.getInt(1);
	                //user.setId(generatedKeys.getLong(1));
	            }
	            else {
	                throw new SQLException("Create failed");
	            }
	        }	
			
			resource.setId(idResourceResultant);
			resource.setName(name);
			resource.setDescription(description);
			//resource.setAvailability(availability); COMENTAR PROFES
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
        HttpHeaders responseHeaders = new HttpHeaders();    	
        return new ResponseEntity<Resource>(resource, responseHeaders, HttpStatus.OK);
    }

    public ResponseEntity<Skill> addNewSkillToProject(@ApiParam(value = "ID of the project (e.g. \"1\" or \"siemens\")",required=true ) @PathVariable("projectId") String projectId,
        @ApiParam(value = "Skill parameters" ,required=true ) @RequestBody SkillData body) {
        // do some magic!
    	String name = body.getName();
    	String description = body.getDescription();
    	Skill skill = new Skill();
    	
    	projectId = transformProjectId(projectId);
    	Connection con = null;

		try {
			con = getConnection();
			
			PreparedStatement statement = con.prepareStatement("INSERT INTO skills (idProject, name, description)  VALUES ('"+ projectId +"','"+ name +"','"+ description + "')", Statement.RETURN_GENERATED_KEYS);
			int affectedRows = statement.executeUpdate();

			Integer idSkillResultant = null;
			try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	            	System.out.println("ID " + generatedKeys.getInt(1));
	            	idSkillResultant = generatedKeys.getInt(1);
	                //user.setId(generatedKeys.getLong(1));
	            }
	            else {
	                throw new SQLException("Create failed");
	            }
	        }	
			skill.setId(idSkillResultant);
			skill.setName(name);
			skill.setDescription(description);
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
        HttpHeaders responseHeaders = new HttpHeaders();    	
        return new ResponseEntity<Skill>(skill, responseHeaders, HttpStatus.OK);
    }

    public ResponseEntity<Release> addResourcesToRelease(@ApiParam(value = "ID of the project (e.g. \"1\" or \"siemens\")",required=true ) @PathVariable("projectId") String projectId,
        @ApiParam(value = "ID of the realese",required=true ) @PathVariable("releaseId") BigDecimal releaseId,
        @ApiParam(value = "Array of Resource ids" ,required=true ) @RequestBody List<ResourceId> body) {
        // do some magic!
    	projectId = transformProjectId(projectId);
    	Release release = new Release();
    	Connection con = null;
		try {
			con = getConnection();
			
			for (int i = 0; i < body.size(); ++i) {
				ResourceId r = body.get(i);
				Integer resourceId = r.getResourceId();
				PreparedStatement statement = con.prepareStatement("INSERT INTO releaseResource (id_release, id_resource)  VALUES ('"+ releaseId + "','"+ resourceId + "')");
				statement.execute();
			}
			
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from releases where idProject = " + projectId + " and id = " + releaseId);
			
			while(rs.next()){
				Integer id = rs.getInt("id");
				String name = rs.getString("name");
				String description = rs.getString("description");
				Date starts_at = rs.getDate("starts_at");
				Date deadline = rs.getDate("deadline");
				
				release.setId(id);
				release.setName(name);
				release.setDescription(description);
				release.setStartsAt(starts_at);
				release.setDeadline(deadline);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		HttpHeaders responseHeaders = new HttpHeaders();    	
        return new ResponseEntity<Release>(release, responseHeaders, HttpStatus.OK);
    }

    public ResponseEntity<Feature> addSkillsToFeature(@ApiParam(value = "ID of the project (e.g. \"1\" or \"siemens\")",required=true ) @PathVariable("projectId") String projectId,
        @ApiParam(value = "ID of the realese",required=true ) @PathVariable("featureId") BigDecimal featureId,
        @ApiParam(value = "Array of Skill ids" ,required=true ) @RequestBody List<SkillId> body) {
        // do some magic!
    	projectId = transformProjectId(projectId);
    	Feature feature = new Feature();
    	Connection con = null;
		try {
			con = getConnection();
			
			for (int i = 0; i < body.size(); ++i) {
				SkillId s = body.get(i);
				Integer skillId = s.getSkillId();
				PreparedStatement statement = con.prepareStatement("INSERT INTO featureSkill (id_feature, id_skill)  VALUES ('"+ featureId + "','"+ skillId + "')");
				statement.execute();
			}
			
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from features where idProject = " + projectId + " and id = " + featureId);

			while (rs.next()){
				int id = rs.getInt("id");
				int code = rs.getInt("code");
				String name = rs.getString("name");
				String description = rs.getString("description");
				BigDecimal effort = rs.getBigDecimal("effort");
				Date deadline = rs.getDate("deadline");
				int priority = rs.getInt("priority");
				
				feature.setId(id);
				feature.setCode(code);
				feature.setName(name);
				feature.setDescription(description);
				feature.setEffort(effort);
				feature.setDeadline(deadline);
				feature.setPriority(priority);
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		HttpHeaders responseHeaders = new HttpHeaders();    	
        return new ResponseEntity<Feature>(feature, responseHeaders, HttpStatus.OK);
    }

    public ResponseEntity<Resource> addSkillsToResource(@ApiParam(value = "ID of the project (e.g. \"1\" or \"siemens\")",required=true ) @PathVariable("projectId") String projectId,
        @ApiParam(value = "ID of the resource",required=true ) @PathVariable("resourceId") BigDecimal resourceId,
        @ApiParam(value = "Array of Skill ids" ,required=true ) @RequestBody List<SkillId> body) {
        // do some magic!
    	projectId = transformProjectId(projectId);
    	Resource resource = new Resource();
    	Connection con = null;
		try {
			con = getConnection();
			
			for (int i = 0; i < body.size(); ++i) {
				SkillId s = body.get(i);
				Integer skillId = s.getSkillId();
				PreparedStatement statement = con.prepareStatement("INSERT INTO resourceSkill (id_resource, id_skill)  VALUES ('"+ resourceId + "','"+ skillId + "')");
				statement.execute();
			}
			
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from resources where idProject = " + projectId + " and id = " + resourceId);

			while(rs.next()){
				Integer id = rs.getInt("id");
				String name = rs.getString("name");
				String description = rs.getString("description");
				Integer availability = rs.getInt("availability");
				
				resource.setId(id);
				resource.setName(name);
				resource.setDescription(description);
				resource.setAvailability(availability);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		HttpHeaders responseHeaders = new HttpHeaders();    	
        return new ResponseEntity<Resource>(resource, responseHeaders, HttpStatus.OK);
    }

    public ResponseEntity<Void> cancelLastReleasePlan(@ApiParam(value = "ID of the project (e.g. \"1\" or \"siemens\")",required=true ) @PathVariable("projectId") String projectId,
        @ApiParam(value = "ID of the realese",required=true ) @PathVariable("releaseId") BigDecimal releaseId) {
        // do some magic!
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<Feature> createFeature(@ApiParam(value = "ID of the project (e.g. \"1\" or \"siemens\")",required=true ) @PathVariable("projectId") String projectId,
        @ApiParam(value = "Project parameters" ,required=true ) @RequestBody NewFeatureData body) {
        // do some magic!
    	Integer code = body.getCode();
    	String name = body.getName();
    	String description = body.getDescription();
    	BigDecimal effort = body.getEffort();
    	DateTime deadline = body.getDeadline();
    	Integer priority = body.getPriority();
    	
    	projectId = transformProjectId(projectId);
    	Feature feature = new Feature();
    	Connection con = null;
		try {
			con = getConnection();
			
			
			PreparedStatement statement = con.prepareStatement("INSERT INTO features (idProject, code, name, description, effort, deadline, priority)  VALUES ('"+ projectId +"','"+ code +"','"+ name +"','"+ description + "','"+ effort + "','"+ deadline + "','"+ priority + "')", Statement.RETURN_GENERATED_KEYS);
			int affectedRows = statement.executeUpdate();
			
			Integer idFeatureResultant = null;
			try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	            	System.out.println("ID " + generatedKeys.getInt(1));
	            	idFeatureResultant = generatedKeys.getInt(1);
	                //user.setId(generatedKeys.getLong(1));
	            }
	            else {
	                throw new SQLException("Create failed");
	            }
	        }
			//System.out.println(idFeatureResultant);
			feature.setId(idFeatureResultant);
			feature.setCode(code);
			feature.setName(name);
			feature.setDescription(description);
			feature.setEffort(effort);
			//feature.setDeadline(deadline); //COMENTAR PROFES
			feature.setPriority(priority);
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
        HttpHeaders responseHeaders = new HttpHeaders();    	
        return new ResponseEntity<Feature>(feature, responseHeaders, HttpStatus.OK);
    }

    public ResponseEntity<Project> createProject(@ApiParam(value = "Project parameters" ,required=true ) @RequestBody NewProjectData body) {
        // do some magic!
    	String name = body.getName();
    	String description = body.getDescription();
    	String effortUnit = body.getEffortUnit();
    	BigDecimal hoursPerEffortUnit = body.getHoursPerEffortUnit();
    	BigDecimal hoursPerWeekAndFullTimeResource = body.getHoursPerWeekAndFullTimeResource(); 
    	
    	Project project = new Project();
    	
    	Connection con = null;
		
		try {
			con = getConnection();
			
			PreparedStatement statement = con.prepareStatement("INSERT INTO projects (name, description, effort_unit, hours_per_effort_unit, hours_per_week_and_full_time_resource)  VALUES ('"+ name +"','"+ description + "','"+ effortUnit + "','"+ hoursPerEffortUnit + "','"+ hoursPerWeekAndFullTimeResource + "')", Statement.RETURN_GENERATED_KEYS);
			int affectedRows = statement.executeUpdate();

			Integer idProjectResultant = null;
			try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	            	System.out.println("ID " + generatedKeys.getInt(1));
	            	idProjectResultant = generatedKeys.getInt(1);
	                //user.setId(generatedKeys.getLong(1));
	            }
	            else {
	                throw new SQLException("Create failed");
	            }
	        }	
			
			project.setId(idProjectResultant.toString());
			project.setName(name);
			project.setDescription(description);
			project.setEffortUnit(effortUnit);
			project.setHoursPerEffortUnit(hoursPerEffortUnit);
			project.setHoursPerWeekAndFullTimeResource(hoursPerWeekAndFullTimeResource);
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
        HttpHeaders responseHeaders = new HttpHeaders();    	
        return new ResponseEntity<Project>(project, responseHeaders, HttpStatus.OK);
    }

    public ResponseEntity<Feature> deleteDependenciesFromFeature(@ApiParam(value = "ID of the project (e.g. \"1\" or \"siemens\")",required=true ) @PathVariable("projectId") String projectId,
        @ApiParam(value = "ID of the feature",required=true ) @PathVariable("featureId") BigDecimal featureId,
        @ApiParam(value = "IDs of the features to remove as dependencies", required = true) @RequestParam(value = "featureId2", required = true) List<BigDecimal> featureId2) {
        // do some magic!
    	projectId = transformProjectId(projectId);
    	Feature feature = new Feature();
    	Connection con = null;
		try {
			con = getConnection();
			
			for (int i = 0; i < featureId2.size(); ++i) {
				BigDecimal f = featureId2.get(i);
				PreparedStatement statement = con.prepareStatement("DELETE FROM featuresDependencies WHERE id_feature =  "+ featureId + " and dependencies = "+ f);
				statement.execute();
			}
			
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from features where idProject = " + projectId + " and id = " + featureId);

			while(rs.next()){
				int id = rs.getInt("id");
				int code = rs.getInt("code");
				String name = rs.getString("name");
				String description = rs.getString("description");
				BigDecimal effort = rs.getBigDecimal("effort");
				Date deadline = rs.getDate("deadline");
				int priority = rs.getInt("priority");
				
				feature.setId(id);
				feature.setCode(code);
				feature.setName(name);
				feature.setDescription(description);
				feature.setEffort(effort);
				feature.setDeadline(deadline);
				feature.setPriority(priority);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		HttpHeaders responseHeaders = new HttpHeaders();    	
        return new ResponseEntity<Feature>(feature, responseHeaders, HttpStatus.OK);
    }

    public ResponseEntity<Void> deleteFeature(@ApiParam(value = "ID of the project (e.g. \"1\" or \"siemens\")",required=true ) @PathVariable("projectId") String projectId,
        @ApiParam(value = "ID of the feature",required=true ) @PathVariable("featureId") BigDecimal featureId) {
        // do some magic!
    	projectId = transformProjectId(projectId);
    	Connection con = null;
		
		try {
			con = getConnection();
			PreparedStatement statement = con.prepareStatement("DELETE FROM features WHERE idProject = " + projectId + " AND id = " + featureId);
			statement.execute();
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<Void> deleteProject(@ApiParam(value = "ID of the project (e.g. \"1\" or \"siemens\")",required=true ) @PathVariable("projectId") String projectId) {
        // do some magic!
    	projectId = transformProjectId(projectId);
    	Connection con = null;
		
		try {
			con = getConnection();
			PreparedStatement statement = con.prepareStatement("DELETE FROM projects WHERE id = " + projectId);
			statement.execute();
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<Void> deleteRelease(@ApiParam(value = "ID of the project (e.g. \"1\" or \"siemens\")",required=true ) @PathVariable("projectId") String projectId,
        @ApiParam(value = "ID of the project",required=true ) @PathVariable("releaseId") BigDecimal releaseId) {
        // do some magic!
    	projectId = transformProjectId(projectId);
    	Connection con = null;
		
		try {
			con = getConnection();
			PreparedStatement statement = con.prepareStatement("DELETE FROM releases WHERE idProject = " + projectId + " AND id = " + releaseId);
			statement.execute();
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<Void> deleteResource(@ApiParam(value = "ID of the project (e.g. \"1\" or \"siemens\")",required=true ) @PathVariable("projectId") String projectId,
        @ApiParam(value = "ID of the resource",required=true ) @PathVariable("resourceId") BigDecimal resourceId) {
        // do some magic!
    	projectId = transformProjectId(projectId);
    	Connection con = null;
		
		try {
			con = getConnection();
			PreparedStatement statement = con.prepareStatement("DELETE FROM resources WHERE idProject = " + projectId + " AND id = " + resourceId);
			statement.execute();
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<Release> deleteResourcesFromRelease(@ApiParam(value = "ID of the project (e.g. \"1\" or \"siemens\")",required=true ) @PathVariable("projectId") String projectId,
        @ApiParam(value = "ID of the realese",required=true ) @PathVariable("releaseId") BigDecimal releaseId,
        @ApiParam(value = "IDs of the resources to remove", required = true) @RequestParam(value = "resourceId", required = true) List<BigDecimal> resourceId) {
        // do some magic!
    	projectId = transformProjectId(projectId);
    	Release release = new Release();
    	Connection con = null;
		try {
			con = getConnection();
			
			for (int i = 0; i < resourceId.size(); ++i) {
				BigDecimal r = resourceId.get(i);
				PreparedStatement statement = con.prepareStatement("DELETE FROM releaseResource WHERE id_release =  "+ releaseId + " and id_resource = "+ r);
				statement.execute();
			}
			
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from releases where idProject = " + projectId + " and id = " + releaseId);

			while(rs.next()){
				Integer id = rs.getInt("id");
				String name = rs.getString("name");
				String description = rs.getString("description");
				Date starts_at = rs.getDate("starts_at");
				Date deadline = rs.getDate("deadline");
				
				release.setId(id);
				release.setName(name);
				release.setDescription(description);
				release.setStartsAt(starts_at);
				release.setDeadline(deadline);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		HttpHeaders responseHeaders = new HttpHeaders();    	
        return new ResponseEntity<Release>(release, responseHeaders, HttpStatus.OK);
    }

    public ResponseEntity<Void> deleteSkill(@ApiParam(value = "ID of the project (e.g. \"1\" or \"siemens\")",required=true ) @PathVariable("projectId") String projectId,
        @ApiParam(value = "ID of the project",required=true ) @PathVariable("skillId") BigDecimal skillId) {
        // do some magic!
    	projectId = transformProjectId(projectId);
    	Connection con = null;
		
		try {
			con = getConnection();
			PreparedStatement statement = con.prepareStatement("DELETE FROM skills WHERE idProject = " + projectId + " AND id = " + skillId);
			statement.execute();
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<Feature> deleteSkillsFromFeature(@ApiParam(value = "ID of the project (e.g. \"1\" or \"siemens\")",required=true ) @PathVariable("projectId") String projectId,
        @ApiParam(value = "ID of the realese",required=true ) @PathVariable("featureId") BigDecimal featureId,
        @ApiParam(value = "IDs of the skills to remove", required = true) @RequestParam(value = "skillId", required = true) List<BigDecimal> skillId) {
        // do some magic!
    	projectId = transformProjectId(projectId);
    	Feature feature = new Feature();
    	Connection con = null;
		try {
			con = getConnection();
			
			for (int i = 0; i < skillId.size(); ++i) {
				BigDecimal s = skillId.get(i);
				PreparedStatement statement = con.prepareStatement("DELETE FROM featureSkill WHERE id_feature =  "+ featureId + " and id_skill = "+ s);
				statement.execute();
			}
			
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from features where idProject = " + projectId + " and id = " + featureId);

			while (rs.next()){
				int id = rs.getInt("id");
				int code = rs.getInt("code");
				String name = rs.getString("name");
				String description = rs.getString("description");
				BigDecimal effort = rs.getBigDecimal("effort");
				Date deadline = rs.getDate("deadline");
				int priority = rs.getInt("priority");
				
				feature.setId(id);
				feature.setCode(code);
				feature.setName(name);
				feature.setDescription(description);
				feature.setEffort(effort);
				feature.setDeadline(deadline);
				feature.setPriority(priority);
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		HttpHeaders responseHeaders = new HttpHeaders();    	
        return new ResponseEntity<Feature>(feature, responseHeaders, HttpStatus.OK);
    }

    public ResponseEntity<Resource> deleteSkillsFromResource(@ApiParam(value = "ID of the project (e.g. \"1\" or \"siemens\")",required=true ) @PathVariable("projectId") String projectId,
        @ApiParam(value = "ID of the resource",required=true ) @PathVariable("resourceId") BigDecimal resourceId,
        @ApiParam(value = "IDs of the skills to remove", required = true) @RequestParam(value = "skillId", required = true) List<BigDecimal> skillId) {
        // do some magic!
    	projectId = transformProjectId(projectId);
    	Resource resource = new Resource();
    	Connection con = null;
		try {
			con = getConnection();
			
			for (int i = 0; i < skillId.size(); ++i) {
				BigDecimal s = skillId.get(i);
				PreparedStatement statement = con.prepareStatement("DELETE FROM resourceSkill WHERE id_resource =  "+ resourceId + " and id_skill = "+ s);
				statement.execute();
			}
			
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from resources where idProject = " + projectId + " and id = " + resourceId);

			while(rs.next()){
				Integer id = rs.getInt("id");
				String name = rs.getString("name");
				String description = rs.getString("description");
				Integer availability = rs.getInt("availability");
				
				resource.setId(id);
				resource.setName(name);
				resource.setDescription(description);
				resource.setAvailability(availability);
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		HttpHeaders responseHeaders = new HttpHeaders();    	
        return new ResponseEntity<Resource>(resource, responseHeaders, HttpStatus.OK);
    }

    public ResponseEntity<Feature> getFeature(@ApiParam(value = "ID of the project (e.g. \"1\" or \"siemens\")",required=true ) @PathVariable("projectId") String projectId,
        @ApiParam(value = "ID of the feature",required=true ) @PathVariable("featureId") BigDecimal featureId) {
        // do some magic!
    	projectId = transformProjectId(projectId);
    	Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		Feature feature = new Feature();

		try {
			con = getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery("select * from features where idProject = " + projectId + " and id = " + featureId);

			while(rs.next()){
				int id = rs.getInt("id");
				int code = rs.getInt("code");
				String name = rs.getString("name");
				String description = rs.getString("description");
				BigDecimal effort = rs.getBigDecimal("effort");
				Date deadline = rs.getDate("deadline");
				int priority = rs.getInt("priority");
				
				feature.setId(id);
				feature.setCode(code);
				feature.setName(name);
				feature.setDescription(description);
				feature.setEffort(effort);
				feature.setDeadline(deadline);
				feature.setPriority(priority);
				
				List<Skill> requiredSkills = new ArrayList<Skill>();
				
				Statement stmt2 = con.createStatement();
				ResultSet rs2 = stmt2.executeQuery("select * from skills INNER JOIN featureSkill ON featureSkill.id_feature = " + featureId + " AND featureSkill.id_skill = skills.id ");
				while(rs2.next()){
					id = rs2.getInt("id");
					name = rs2.getString("name");
					description = rs2.getString("description");
					
					Skill skill = new Skill();
					skill.setId(id);
					skill.setName(name);
					skill.setDescription(description);
					requiredSkills.add(skill);
				}
				feature.setRequiredSkills(requiredSkills);
				
				List<Feature> dependsOn = new ArrayList<Feature>();
				
				Statement stmt3 = con.createStatement();
				ResultSet rs3 = stmt3.executeQuery("select * from features INNER JOIN featuresDependencies ON featuresDependencies.id_feature = " + featureId + " AND features.id = featuresDependencies.dependencies");
				while(rs3.next()){
					Feature f = new Feature();
					
					int id2 = rs3.getInt("id");
					int code2 = rs3.getInt("code");
					String name2 = rs3.getString("name");
					String description2 = rs3.getString("description");
					BigDecimal effort2 = rs3.getBigDecimal("effort");
					Date deadline2 = rs3.getDate("deadline");
					int priority2 = rs3.getInt("priority");
					
					f.setId(id2);
					f.setCode(code2);
					f.setName(name2);
					f.setDescription(description2);
					f.setEffort(effort2);
					f.setDeadline(deadline2);
					f.setPriority(priority2);
					dependsOn.add(f);
				}
				feature.setDependsOn(dependsOn); 
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
        HttpHeaders responseHeaders = new HttpHeaders();    	
        return new ResponseEntity<Feature>(feature, responseHeaders, HttpStatus.OK);
    }

	public ResponseEntity<List<Feature>> getFeatures(@ApiParam(value = "ID of the project (e.g. \"1\" or \"siemens\")",required=true ) @PathVariable("projectId") String projectId,
        @ApiParam(value = "any | pending | scheduled", allowableValues = "ANY, PENDING, SCHEDULED") @RequestParam(value = "status", required = false) String status) {
        // do some magic!
    	projectId = transformProjectId(projectId);
    	Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		Integer id;
		Integer code = null;
		String name = null;
		String description = null;
		BigDecimal effort = null;
		Date deadline = null;
		Integer priority = null;
		List<Feature> list = new ArrayList<Feature>(); 
		
		try {
			con = getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery("select * from features where idProject = " + projectId);
			while(rs.next()){
				id = rs.getInt("id");
				code = rs.getInt("code");
				name = rs.getString("name");
				description = rs.getString("description");
				effort = rs.getBigDecimal("effort");
				deadline = rs.getDate("deadline");
				priority = rs.getInt("priority");
				
				Feature feature = new Feature();
				feature.setId(id);
				feature.setCode(code);
				feature.setName(name);
				feature.setDescription(description);
				feature.setEffort(effort);
				feature.setDeadline(deadline);
				feature.setPriority(priority);
				
				List<Skill> requiredSkills = new ArrayList<Skill>();
				
				Statement stmt2 = con.createStatement();
				ResultSet rs2 = stmt2.executeQuery("select * from skills INNER JOIN featureSkill ON featureSkill.id_feature = " + id + " AND featureSkill.id_skill = skills.id ");
				while(rs2.next()){
					id = rs2.getInt("id");
					name = rs2.getString("name");
					description = rs2.getString("description");
					
					Skill skill = new Skill();
					skill.setId(id);
					skill.setName(name);
					skill.setDescription(description);
					requiredSkills.add(skill);
				}
				feature.setRequiredSkills(requiredSkills);
				
				List<Feature> dependsOn = new ArrayList<Feature>();
				
				Statement stmt3 = con.createStatement();
				ResultSet rs3 = stmt3.executeQuery("select * from features INNER JOIN featuresDependencies ON featuresDependencies.id_feature = " + id + " AND features.id = featuresDependencies.dependencies");
				while(rs3.next()){
					Feature f = new Feature();
					
					int id2 = rs3.getInt("id");
					int code2 = rs3.getInt("code");
					String name2 = rs3.getString("name");
					String description2 = rs3.getString("description");
					BigDecimal effort2 = rs3.getBigDecimal("effort");
					Date deadline2 = rs3.getDate("deadline");
					int priority2 = rs3.getInt("priority");
					
					
					f.setId(id2);
					f.setCode(code2);
					f.setName(name2);
					f.setDescription(description2);
					f.setEffort(effort2);
					f.setDeadline(deadline2);
					f.setPriority(priority2);
					dependsOn.add(f);
					System.out.println(f);
				}
				feature.setDependsOn(dependsOn); 
				
				list.add(feature);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
        HttpHeaders responseHeaders = new HttpHeaders();    	
        return new ResponseEntity<List<Feature>>(list, responseHeaders, HttpStatus.OK);
    }

    public ResponseEntity<Project> getProject(@ApiParam(value = "ID of the project (e.g. \"1\" or \"siemens\")",required=true ) @PathVariable("projectId") String projectId) {
        // do some magic!
    	projectId = transformProjectId(projectId);
    	Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		Integer id;
		String name = null;
		String description = null;
		String effortUnit = null;
		BigDecimal hoursPerEffortUnit = null;
		BigDecimal hoursPerWeekAndFullTimeResource = null;
		List<Resource> list = new ArrayList<Resource>();
		
		try {
			con = getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery("select * from projects where id = " + projectId);
			while(rs.next()){
				id = rs.getInt("id");
				name = rs.getString("name");
				description = rs.getString("description");
				effortUnit = rs.getString("effort_unit");
				hoursPerEffortUnit = rs.getBigDecimal("hours_per_effort_unit");
				hoursPerWeekAndFullTimeResource = rs.getBigDecimal("hours_per_week_and_full_time_resource");
				
				Statement stmt2 = con.createStatement();
				ResultSet rs2 = stmt2.executeQuery("select * from resources where idProject = " + projectId);
				while(rs2.next()){
					Integer id2 = rs2.getInt("id");
					String name2 = rs2.getString("name");
					String description2 = rs2.getString("description");
					int availability = rs2.getInt("availability");
					
					Resource resource = new Resource();
					resource.setId(id2);
					resource.setName(name2);
					resource.setDescription(description2);
					resource.setAvailability(availability);
					
					List<Skill> listSkills = new ArrayList<Skill>();
					Statement stmt3 = con.createStatement();
					ResultSet rs3 = stmt3.executeQuery("select * from skills INNER JOIN resourceSkill ON resourceSkill.id_resource = " + id2 + " AND resourceSkill.id_skill = skills.id ");
					while(rs3.next()){
						int idS = rs3.getInt("id");
						String nameS = rs3.getString("name");
						String descriptionS = rs3.getString("description");

						Skill skill = new Skill();
						skill.setId(idS);
						skill.setName(nameS);
						skill.setDescription(descriptionS);

						listSkills.add(skill);
					}
					resource.setSkills(listSkills);
					list.add(resource);
				}
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	Project project = new Project();
        project.setId(projectId);
        project.setName(name);
        project.setDescription(description);
        project.setEffortUnit(effortUnit);
        project.setHoursPerEffortUnit(hoursPerEffortUnit);
        project.setHoursPerWeekAndFullTimeResource(hoursPerWeekAndFullTimeResource);
        project.setResources(list);
        
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<Project>(project, responseHeaders, HttpStatus.OK);
    }

    public ResponseEntity<List<Resource>> getProjectResources(@ApiParam(value = "ID of the project (e.g. \"1\" or \"siemens\")",required=true ) @PathVariable("projectId") String projectId) {
        // do some magic!
    	projectId = transformProjectId(projectId);
    	Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		int id;
		String name = null;
		String description = null;
		List<Resource> list = new ArrayList<Resource>(); 
		
		try {
			con = getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery("select * from resources where idProject = " + projectId);
			while(rs.next()){
				id = rs.getInt("id");
				name = rs.getString("name");
				description = rs.getString("description");
				int availability = rs.getInt("availability");
				
				Resource resource = new Resource();
				resource.setId(id);
				resource.setName(name);
				resource.setDescription(description);
				resource.setAvailability(availability);
				
				List<Skill> listSkills = new ArrayList<Skill>();
				Statement stmt2 = con.createStatement();
				ResultSet rs2 = stmt2.executeQuery("select * from skills INNER JOIN resourceSkill ON resourceSkill.id_resource = " + id + " AND resourceSkill.id_skill = skills.id ");
				while(rs2.next()){
					int idS = rs2.getInt("id");
					String nameS = rs2.getString("name");
					String descriptionS = rs2.getString("description");

					Skill skill = new Skill();
					skill.setId(idS);
					skill.setName(nameS);
					skill.setDescription(descriptionS);

					listSkills.add(skill);
				}
				resource.setSkills(listSkills);
				list.add(resource);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
        HttpHeaders responseHeaders = new HttpHeaders();    	
        return new ResponseEntity<List<Resource>>(list, responseHeaders, HttpStatus.OK);
    }

    public ResponseEntity<List<Skill>> getProjectSkills(@ApiParam(value = "ID of the project (e.g. \"1\" or \"siemens\")",required=true ) @PathVariable("projectId") String projectId) {
        // do some magic!
    	projectId = transformProjectId(projectId);
    	Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		int id;
		String name = null;
		String description = null;
		List<Skill> list = new ArrayList<Skill>(); 
		
		try {
			con = getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery("select * from skills where idProject = " + projectId);
			while(rs.next()){
				id = rs.getInt("id");
				name = rs.getString("name");
				description = rs.getString("description");
				
				Skill skill = new Skill();
				skill.setId(id);
				skill.setName(name);
				skill.setDescription(description);
				list.add(skill);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<List<Skill>>(list, responseHeaders, HttpStatus.OK);
    }

    public ResponseEntity<List<Project>> getProjects() {
        // do some magic!
    	Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<Project> projects = new ArrayList<Project>();
		
		try {
			con = getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery("select * from projects");
			while(rs.next()){
				String id = rs.getString("id");
				String name = rs.getString("name");;
				String description = rs.getString("description");
				String effortUnit = rs.getString("effort_unit");
				BigDecimal hoursPerEffortUnit = rs.getBigDecimal("hours_per_effort_unit");
				BigDecimal hoursPerWeekAndFullTimeResource = rs.getBigDecimal("hours_per_week_and_full_time_resource");
				List<Resource> list = new ArrayList<Resource>();
				
				//Connection con2 = getConnection();
				Statement stmt2 = con.createStatement();
				ResultSet rs2 = stmt2.executeQuery("select * from resources where idProject = " + id);
				while(rs2.next()){
					int idR = rs2.getInt("id");
					String nameR = rs2.getString("name");
					String descriptionR = rs2.getString("description");
					int availability = rs2.getInt("availability");
					
					Resource resource = new Resource();
					resource.setId(idR);
					resource.setName(nameR);
					resource.setDescription(descriptionR);
					resource.setAvailability(availability);
					
					List<Skill> listSkills = new ArrayList<Skill>();
					Statement stmt3 = con.createStatement();
					ResultSet rs3 = stmt3.executeQuery("select * from skills INNER JOIN resourceSkill ON resourceSkill.id_resource = " + idR + " AND resourceSkill.id_skill = skills.id ");
					while(rs3.next()){
						int idS = rs3.getInt("id");
						String nameS = rs3.getString("name");
						String descriptionS = rs3.getString("description");

						Skill skill = new Skill();
						skill.setId(idS);
						skill.setName(nameS);
						skill.setDescription(descriptionS);

						listSkills.add(skill);
					}
					resource.setSkills(listSkills);
					list.add(resource);
				}
				
				Project project = new Project();
		        project.setId(id);
		        project.setName(name);
		        project.setDescription(description);
		        project.setEffortUnit(effortUnit);
		        project.setHoursPerEffortUnit(hoursPerEffortUnit);
		        project.setHoursPerWeekAndFullTimeResource(hoursPerWeekAndFullTimeResource);
		        project.setResources(list);
		        
		        projects.add(project);
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
        HttpHeaders responseHeaders = new HttpHeaders();    	
        return new ResponseEntity<List<Project>>(projects, responseHeaders, HttpStatus.OK);
    }

    public ResponseEntity<Release> getRelease(@ApiParam(value = "ID of the project (e.g. \"1\" or \"siemens\")",required=true ) @PathVariable("projectId") String projectId,
        @ApiParam(value = "ID of the release",required=true ) @PathVariable("releaseId") BigDecimal releaseId) {
        // do some magic!
    	projectId = transformProjectId(projectId);
    	Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		Release release = new Release();
		
		try {
			con = getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery("select * from releases where idProject = " + projectId + " and id = " + releaseId);
			
			while(rs.next()){
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String description = rs.getString("description");
				Date starts_at = rs.getDate("starts_at");
				Date deadline = rs.getDate("deadline");
				
				release.setId(id);
				release.setName(name);
				release.setDescription(description);
				release.setStartsAt(starts_at);
				release.setDeadline(deadline);
				
				List<Resource> resources = new ArrayList<Resource>();
				
				Statement stmt2 = con.createStatement();
				ResultSet rs2 = stmt2.executeQuery("select * from resources INNER JOIN releaseResource ON releaseResource.id_release = " + id + " AND releaseResource.id_resource = resources.id");
				while(rs2.next()){
					int idR = rs2.getInt("id");
					String nameR = rs2.getString("name");
					String descriptionR = rs2.getString("description");
					int availability = rs2.getInt("availability");
					
					Resource resource = new Resource();
					resource.setId(idR);
					resource.setName(nameR);
					resource.setDescription(descriptionR);
					resource.setAvailability(availability);
					
					List<Skill> listSkills = new ArrayList<Skill>();
					Statement stmt3 = con.createStatement();
					ResultSet rs3 = stmt3.executeQuery("select * from skills INNER JOIN resourceSkill ON resourceSkill.id_resource = " + idR + " AND resourceSkill.id_skill = skills.id ");
					while(rs3.next()){
						int idS = rs3.getInt("id");
						String nameS = rs3.getString("name");
						String descriptionS = rs3.getString("description");

						Skill skill = new Skill();
						skill.setId(idS);
						skill.setName(nameS);
						skill.setDescription(descriptionS);

						listSkills.add(skill);
					}
					resource.setSkills(listSkills);
					resources.add(resource);
				}
				release.setResources(resources);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
        HttpHeaders responseHeaders = new HttpHeaders();  
        return new ResponseEntity<Release>(release, responseHeaders, HttpStatus.OK);
    }

    public ResponseEntity<List<Feature>> getReleaseFeatures(@ApiParam(value = "ID of the project (e.g. \"1\" or \"siemens\")",required=true ) @PathVariable("projectId") String projectId,
        @ApiParam(value = "ID of the realese",required=true ) @PathVariable("releaseId") BigDecimal releaseId) {
        // do some magic!
    	projectId = transformProjectId(projectId);
    	Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		Integer id;
		Integer code = null;
		String name = null;
		String description = null;
		BigDecimal effort = null;
		Date deadline = null;
		Integer priority = null;
		List<Feature> list = new ArrayList<Feature>(); 
		
		try {
			con = getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery("select * from features where idProject = " + projectId + " and id_release = " + releaseId);
			while(rs.next()){
				id = rs.getInt("id");
				code = rs.getInt("code");
				name = rs.getString("name");
				description = rs.getString("description");
				effort = rs.getBigDecimal("effort");
				deadline = rs.getDate("deadline");
				priority = rs.getInt("priority");
				
				Feature feature = new Feature();
				feature.setId(id);
				feature.setCode(code);
				feature.setName(name);
				feature.setDescription(description);
				feature.setEffort(effort);
				feature.setDeadline(deadline);
				feature.setPriority(priority);
				
				List<Skill> requiredSkills = new ArrayList<Skill>();
				
				Statement stmt2 = con.createStatement();
				ResultSet rs2 = stmt2.executeQuery("select * from skills INNER JOIN featureSkill ON featureSkill.id_feature = " + id + " AND featureSkill.id_skill = skills.id ");
				while(rs2.next()){
					id = rs2.getInt("id");
					name = rs2.getString("name");
					description = rs2.getString("description");
					
					Skill skill = new Skill();
					skill.setId(id);
					skill.setName(name);
					skill.setDescription(description);
					requiredSkills.add(skill);
				}
				//System.out.println("SOMOS LAS REQUIRED SKILLS");
				//System.out.println(requiredSkills);
				feature.setRequiredSkills(requiredSkills);
				
				List<Feature> dependsOn = new ArrayList<Feature>();
				
				Statement stmt3 = con.createStatement();
				System.out.println("IIIIDDD" + id);
				ResultSet rs3 = stmt3.executeQuery("select * from features INNER JOIN featuresDependencies ON featuresDependencies.id_feature = " + feature.getId() + " AND features.id = featuresDependencies.dependencies");
				while(rs3.next()){
					Feature f = new Feature();
					
					int id2 = rs3.getInt("id");
					int code2 = rs3.getInt("code");
					String name2 = rs3.getString("name");
					String description2 = rs3.getString("description");
					BigDecimal effort2 = rs3.getBigDecimal("effort");
					Date deadline2 = rs3.getDate("deadline");
					int priority2 = rs3.getInt("priority");
					
					f.setId(id2);
					f.setCode(code2);
					f.setName(name2);
					f.setDescription(description2);
					f.setEffort(effort2);
					f.setDeadline(deadline2);
					f.setPriority(priority2);
					dependsOn.add(f);
				}
				System.out.println("SOMOS LAS DEPENDS ON");
				System.out.println(dependsOn);
				feature.setDependsOn(dependsOn);
				list.add(feature);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
        HttpHeaders responseHeaders = new HttpHeaders();    	
        return new ResponseEntity<List<Feature>>(list, responseHeaders, HttpStatus.OK);
    }

    @SuppressWarnings("unchecked")
	public ResponseEntity<Plan> getReleasePlan(@ApiParam(value = "ID of the project (e.g. \"1\" or \"siemens\")",required=true ) @PathVariable("projectId") String projectId,
        @ApiParam(value = "ID of the realese",required=true ) @PathVariable("releaseId") BigDecimal releaseId) {
        // do some magic!
    	JSONObject obj = new JSONObject();
    	Plan plan = new Plan();
    	
    	ResponseEntity<Release> r = this.getRelease(projectId, releaseId);
    	Release release = r.getBody();
    	
    	ResponseEntity<Project> p = this.getProject(projectId);
    	Project project = p.getBody();
    	
    	/* nbWeeks and hoursPerWeek */
    	
    	Date startsAt = release.getStartsAt();
    	Date deadLine = release.getDeadline();

    	int nbWeeks = Weeks.weeksBetween(new DateTime(startsAt), new DateTime(deadLine)).getWeeks();
    	BigDecimal hoursPerWeek = project.getHoursPerWeekAndFullTimeResource();
    	
    	
    	obj.put("nbWeeks", nbWeeks);
    	obj.put("hoursPerWeek", hoursPerWeek);
    	
    	/* features */
    	
    	ResponseEntity<List<Feature>> f = this.getReleaseFeatures(projectId, releaseId);
    	List<Feature> features = f.getBody();
    	
    	JSONArray featuresList = new JSONArray();
    	for (int i = 0; i < features.size(); ++i) {
    		Feature actual = features.get(i);
    		
    		JSONObject feat = new JSONObject();
    		feat.put("name", actual.getId());
    		feat.put("duration", (actual.getEffort().multiply(project.getHoursPerEffortUnit())));
    		
    		JSONObject priority = new JSONObject();
    		priority.put("level", actual.getPriority());
    		priority.put("score", actual.getPriority());
    		
    		feat.put("priority", priority);
    		
    		/* required skills de la feature*/
    		JSONArray requiredSkills = new JSONArray();
    		List<Skill> skills = actual.getRequiredSkills();
    		
    		for (int j = 0; j < skills.size(); ++j) {
        		JSONObject skill = new JSONObject();
        		skill.put("name", skills.get(j).getId());
        		requiredSkills.add(skill);
    		}
    		feat.put("required_skills", requiredSkills);
    		
    		/*depends on de la feature*/
    		JSONArray dependsOn = new JSONArray();
    		List<Feature> depends = actual.getDependsOn();
    		
    		System.out.println("SOY LAS DEPENDS ON: " + dependsOn);
    		for (int j = 0; j < depends.size(); ++j) {
        		JSONObject featureDepend = new JSONObject();
        		featureDepend.put("name", depends.get(j).getId());
        		featureDepend.put("duration", depends.get(j).getEffort().multiply(project.getHoursPerEffortUnit()));
        		
        		JSONObject priority2 = new JSONObject();
        		priority2.put("level", depends.get(j).getPriority());
        		priority2.put("score", depends.get(j).getPriority());
        		
        		featureDepend.put("priority", priority);
        		
        		dependsOn.add(featureDepend);
    		}
    		feat.put("depends_on", dependsOn);
    		

    		featuresList.add(feat);
    	}
    	obj.put("features", featuresList); //add features to final object
    	
    	
    	/* resources */
    	ResponseEntity<List<Resource>> res = this.getProjectResources(projectId);
    	List<Resource> resources = res.getBody();
    	
    	JSONArray resourcesList = new JSONArray();
    	for (int i = 0; i < resources.size(); ++i) {
    		Resource actual = resources.get(i);
    	
    		JSONObject resource = new JSONObject();
    		resource.put("name", actual.getId());
    		resource.put("availability", (actual.getAvailability() * 0.01 * 40));
    		
    		JSONArray skillsResource = new JSONArray();
    		for (int j = 0; j < actual.getSkills().size(); ++j) {
    			JSONObject name = new JSONObject();
        		name.put("name", actual.getSkills().get(j).getId());
        		skillsResource.add(name);
    		}
    		resource.put("skills", skillsResource);
    		resourcesList.add(resource);
    	}
    	obj.put("resources", resourcesList);
    	
    	ObjectMapper mapper = new ObjectMapper();
    	try {
			System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
    	/*JSONObject obj = new JSONObject();
        obj.put("name", "mkyong.com");
        obj.put("age", new Integer(100));

        JSONArray list = new JSONArray();
        list.add("msg 1");
        list.add("msg 2");
        list.add("msg 3");

        obj.put("messages", list);*/
    	
        return new ResponseEntity<Plan>(HttpStatus.OK);
    }

    public ResponseEntity<List<Release>> getReleases(@ApiParam(value = "ID of the project (e.g. \"1\" or \"siemens\")",required=true ) @PathVariable("projectId") String projectId) {
        // do some magic!
    	projectId = transformProjectId(projectId);
    	Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		int id;
		String name = null;
		String description = null;
		Date starts_at = null;
		Date deadline = null;
		List<Release> list = new ArrayList<Release>();
		
		try {
			con = getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery("select * from releases where idProject = " + projectId);
			
			while(rs.next()){
				id = rs.getInt("id");
				name = rs.getString("name");
				description = rs.getString("description");
				starts_at = rs.getDate("starts_at");
				deadline = rs.getDate("deadline");
				
				Release release = new Release();
				release.setId(id);
				release.setName(name);
				release.setDescription(description);
				release.setStartsAt(starts_at);
				release.setDeadline(deadline);
				
				List<Resource> resources = new ArrayList<Resource>();
				
				Statement stmt2 = con.createStatement();
				ResultSet rs2 = stmt2.executeQuery("select * from resources INNER JOIN releaseResource ON releaseResource.id_release = " + id + " AND releaseResource.id_resource = resources.id");
				while(rs2.next()){
					int idR = rs2.getInt("id");
					String nameR = rs2.getString("name");
					String descriptionR = rs2.getString("description");
					int availability = rs2.getInt("availability");
					
					Resource resource = new Resource();
					resource.setId(idR);
					resource.setName(nameR);
					resource.setDescription(descriptionR);
					resource.setAvailability(availability);
					
					List<Skill> listSkills = new ArrayList<Skill>();
					Statement stmt3 = con.createStatement();
					ResultSet rs3 = stmt3.executeQuery("select * from skills INNER JOIN resourceSkill ON resourceSkill.id_resource = " + idR + " AND resourceSkill.id_skill = skills.id ");
					while(rs3.next()){
						int idS = rs3.getInt("id");
						String nameS = rs3.getString("name");
						String descriptionS = rs3.getString("description");

						Skill skill = new Skill();
						skill.setId(idS);
						skill.setName(nameS);
						skill.setDescription(descriptionS);

						listSkills.add(skill);
					}
					resource.setSkills(listSkills);
					resources.add(resource);
				}
				release.setResources(resources);
				list.add(release);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
        HttpHeaders responseHeaders = new HttpHeaders();    	
        return new ResponseEntity<List<Release>>(list, responseHeaders, HttpStatus.OK);
    }

    public ResponseEntity<Feature> modifyFeature(@ApiParam(value = "ID of the project (e.g. \"1\" or \"siemens\")",required=true ) @PathVariable("projectId") String projectId,
        @ApiParam(value = "ID of the feature",required=true ) @PathVariable("featureId") BigDecimal featureId,
        @ApiParam(value = "Feature parameters that can be modified" ,required=true ) @RequestBody FeatureData body) {
        // do some magic!    	
    	projectId = transformProjectId(projectId);
    	Connection con = null;
    	Boolean first = true;
    	Feature feature = new Feature();
		
		try {
			String query = "UPDATE features SET ";
			
			if (body.getName() != null) {
				query += "name = '" + body.getName() + "'";
				first = false;
			}
			if (body.getDescription() != null) {
				if (first) query += "description = '" + body.getDescription() + "'";
				else query += ", " + "description = '" + body.getDescription() + "'";
				first = false;
			}
			if (body.getDeadline() != null) {
				if (first) query += "deadline = '" + body.getDeadline() + "'";
				else query += ", " + "deadline = '" + body.getDeadline() + "'";
				first = false;
			}
			if (body.getEffort() != null) {
				if (first) query += "effort = " + body.getEffort();
				else query += ", " + "effort = " + body.getEffort();
				first = false;
			}
			if (body.getPriority() != null) {
				if (first) query += "priority = " + body.getPriority();
				else query += ", " + "priority = " + body.getPriority();
				first = false;
			}
			query += " WHERE id = " + featureId + " AND idProject = " + projectId;  
			System.out.println("QUERY: " + query);
			con = getConnection();
			PreparedStatement statement = con.prepareStatement(query);
			statement.execute();
			
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from features where idProject = " + projectId + " AND id = " + featureId);
			
			while(rs.next()){
				int id = rs.getInt("id");
				int code = rs.getInt("code");
				String name = rs.getString("name");
				String description = rs.getString("description");
				BigDecimal effort = rs.getBigDecimal("effort");
				Date deadline = rs.getDate("deadline");
				int priority = rs.getInt("priority");
				
				feature.setId(id);
				feature.setCode(code);
				feature.setName(name);
				feature.setDescription(description);
				feature.setEffort(effort);
				feature.setDeadline(deadline);
				feature.setPriority(priority);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		HttpHeaders responseHeaders = new HttpHeaders();    	
        return new ResponseEntity<Feature>(feature, responseHeaders, HttpStatus.OK);
    }

    public ResponseEntity<Project> modifyProject(@ApiParam(value = "ID of the project (e.g. \"1\" or \"siemens\")",required=true ) @PathVariable("projectId") String projectId,
        @ApiParam(value = "Project's parameters that can be modified" ,required=true ) @RequestBody ProjectData body) {
        // do some magic!
    	body.getEffortUnit();
    	body.getHoursPerEffortUnit();
    	body.getHoursPerWeekAndFullTimeResource();
    	projectId = transformProjectId(projectId);
    	Connection con = null;
    	Boolean first = true;
    	
    	Project project = new Project();
		
		try {
			String query = "UPDATE projects SET ";
			
			if (body.getEffortUnit() != null) {
				query += "effort_unit = '" + body.getEffortUnit() + "'";
				first = false;
			}
			if (body.getHoursPerEffortUnit() != null) {
				if (first) query += "hours_per_effort_unit = " + body.getHoursPerEffortUnit();
				else query += ", " + "hours_per_effort_unit = " + body.getHoursPerEffortUnit();
				first = false;
			}
			if (body.getHoursPerWeekAndFullTimeResource() != null) {
				if (first) query += "hours_per_week_and_full_time_resource = " + body.getHoursPerWeekAndFullTimeResource();
				else query += ", " + "hours_per_week_and_full_time_resource = " + body.getHoursPerWeekAndFullTimeResource();
				first = false;
			}
			query += " WHERE id = " + projectId;  
			System.out.println("QUERY: " + query);
			con = getConnection();
			PreparedStatement statement = con.prepareStatement(query);
			statement.execute();
			
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from projects where id = " + projectId);
			
			while(rs.next()){
				Integer id = rs.getInt("id");
				String name = rs.getString("name");
				String description = rs.getString("description");
				String effortUnit = rs.getString("effort_unit");
				BigDecimal hoursPerEffortUnit = rs.getBigDecimal("hours_per_effort_unit");
				BigDecimal hoursPerWeekAndFullTimeResource = rs.getBigDecimal("hours_per_week_and_full_time_resource");
				
				project.setId(projectId);
		        project.setName(name);
		        project.setDescription(description);
		        project.setEffortUnit(effortUnit);
		        project.setHoursPerEffortUnit(hoursPerEffortUnit);
		        project.setHoursPerWeekAndFullTimeResource(hoursPerWeekAndFullTimeResource);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		HttpHeaders responseHeaders = new HttpHeaders();    	
        return new ResponseEntity<Project>(project, responseHeaders, HttpStatus.OK);
    }

    public ResponseEntity<Release> modifyRelease(@ApiParam(value = "ID of the project (e.g. \"1\" or \"siemens\")",required=true ) @PathVariable("projectId") String projectId,
        @ApiParam(value = "ID of the project",required=true ) @PathVariable("releaseId") BigDecimal releaseId,
        @ApiParam(value = "Release parameters that can be modified" ,required=true ) @RequestBody ReleaseData body) {
        // do some magic!
    	body.getName();
    	body.getDescription();
    	body.getDeadline();
    	body.getStartsAt();
    	projectId = transformProjectId(projectId);
    	Connection con = null;
    	Boolean first = true;
    	Release release = new Release();
		
		try {
			String query = "UPDATE releases SET ";
			
			if (body.getName() != null) {
				query += "name = '" + body.getName() + "'";
				first = false;
			}
			if (body.getDescription() != null) {
				if (first) query += "description = '" + body.getDescription() + "'";
				else query += ", " + "description = '" + body.getDescription() + "'";
				first = false;
			}
			if (body.getDeadline() != null) {
				if (first) query += "deadline = '" + body.getDeadline() + "'";
				else query += ", " + "deadline = '" + body.getDeadline() + "'";
				first = false;
			}
			if (body.getStartsAt() != null) {
				if (first) query += "starts_at = '" + body.getStartsAt() + "'";
				else query += ", " + "starts_at = '" + body.getStartsAt() + "'";
				first = false;
			}
			query += " WHERE id = " + releaseId + " AND idProject = " + projectId;  
			System.out.println("QUERY: " + query);
			con = getConnection();
			PreparedStatement statement = con.prepareStatement(query);
			statement.execute();
			
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from releases where idProject = " + projectId + " AND id = " + releaseId);
			
			while(rs.next()){
				Integer id = rs.getInt("id");
				String name = rs.getString("name");
				String description = rs.getString("description");
				Date starts_at = rs.getDate("starts_at");
				Date deadline = rs.getDate("deadline");
				
				release.setId(id);
				release.setName(name);
				release.setDescription(description);
				release.setStartsAt(starts_at);
				release.setDeadline(deadline);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		HttpHeaders responseHeaders = new HttpHeaders();    	
        return new ResponseEntity<Release>(release, responseHeaders, HttpStatus.OK);
    }

    public ResponseEntity<Resource> modifyResource(@ApiParam(value = "ID of the project (e.g. \"1\" or \"siemens\")",required=true ) @PathVariable("projectId") String projectId,
        @ApiParam(value = "ID of the resource",required=true ) @PathVariable("resourceId") BigDecimal resourceId,
        @ApiParam(value = "Resource parameters that can be modified" ,required=true ) @RequestBody ResourceData body) {
        // do some magic!
    	projectId = transformProjectId(projectId);
    	Connection con = null;
    	Boolean first = true;
    	Resource resource = new Resource();
		
		try {
			String query = "UPDATE resources SET ";
			
			if (body.getName() != null) {
				query += "name = '" + body.getName() + "'";
				first = false;
			}
			if (body.getDescription() != null) {
				if (first) query += "description = '" + body.getDescription() + "'";
				else query += ", " + "description = '" + body.getDescription() + "'";
				first = false;
			}
			if (body.getAvailability() != null) {
				if (first) query += "availability = " + body.getAvailability();
				else query += ", " + "availability = " + body.getAvailability();
				first = false;
			}
			query += " WHERE id = " + resourceId + " AND idProject = " + projectId;  
			System.out.println("QUERY: " + query);
			con = getConnection();
			PreparedStatement statement = con.prepareStatement(query);
			statement.execute();
			
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from resources where idProject = " + projectId + " AND id = " + resourceId);
			
			while(rs.next()){
				Integer id = rs.getInt("id");
				String description = rs.getString("description");
				String name = rs.getString("name");
				Integer availability = rs.getInt("availability");
				
				resource.setId(id);
				resource.setDescription(description);
				resource.setName(name);
				resource.setAvailability(availability);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		HttpHeaders responseHeaders = new HttpHeaders();    	
        return new ResponseEntity<Resource>(resource, responseHeaders, HttpStatus.OK);
    }

    public ResponseEntity<Skill> modifySkill(@ApiParam(value = "ID of the project (e.g. \"1\" or \"siemens\")",required=true ) @PathVariable("projectId") String projectId,
        @ApiParam(value = "ID of the project",required=true ) @PathVariable("skillId") BigDecimal skillId,
        @ApiParam(value = "Skill parameters that can be modified" ,required=true ) @RequestBody SkillData body) {
        // do some magic!
    	//UPDATE table SET `name`='Javaa' WHERE `id`='1';
    	projectId = transformProjectId(projectId);
    	Connection con = null;
    	Boolean first = true;
    	Skill skill = new Skill();
    	
		try {
			String query = "UPDATE skills SET ";
			
			if (body.getName() != null) {
				query += "name = '" + body.getName() + "'";
				first = false;
			}
			if (body.getDescription() != null) {
				if (first) query += "description = '" + body.getDescription() + "'";
				else query += ", " + "description = '" + body.getDescription() + "'";
				first = false;
			}
			query += " WHERE id = " + skillId + " AND idProject = " + projectId;  
			System.out.println("QUERY: " + query);
			con = getConnection();
			PreparedStatement statement = con.prepareStatement(query);
			statement.execute();
			
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from skills where idProject = " + projectId + " AND id = " + skillId);
			
			while(rs.next()){
				Integer id = rs.getInt("id");
				String name = rs.getString("name");
				String description = rs.getString("description");
				
				skill.setId(id);
				skill.setName(name);
				skill.setDescription(description);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		HttpHeaders responseHeaders = new HttpHeaders();    	
        return new ResponseEntity<Skill>(skill, responseHeaders, HttpStatus.OK);
    }

    public ResponseEntity<Void> removeFeaturesFromRelease(@ApiParam(value = "ID of the project (e.g. \"1\" or \"siemens\")",required=true ) @PathVariable("projectId") String projectId,
        @ApiParam(value = "ID of the realese",required=true ) @PathVariable("releaseId") BigDecimal releaseId,
        @ApiParam(value = "IDs of the features to remove", required = true) @RequestParam(value = "featureId", required = true) List<BigDecimal> featureId) {
        // do some magic!
    	projectId = transformProjectId(projectId);
    	Connection con = null;
    	
		try {
			con = getConnection();
			
			for (int i = 0; i < featureId.size(); ++i) {
				BigDecimal f = featureId.get(i);
				String query = "UPDATE features SET id_release = NULL WHERE id = " + f +  " AND idProject = " + projectId;
				//System.out.println("QUERY: " + query);
				PreparedStatement statement = con.prepareStatement(query);
				statement.execute();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
        HttpHeaders responseHeaders = new HttpHeaders();    
        return new ResponseEntity<Void>(HttpStatus.OK); //COMENTAR PROFES
    }

}
