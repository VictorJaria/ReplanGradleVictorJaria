package io.swagger.api;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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

import java.util.List;


@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringCodegen", date = "2017-04-10T09:10:24.462Z")

@Controller
public class ProjectsApiController implements ProjectsApi {
	

	Connection dbConnection = null;
	String url = "jdbc:mysql://localhost:3306/DataBaseController";
	String userName = "root"; 
	String password = "Thelouk09002";
	
	public Connection getConnection() {
		Connection connection = null;
		System.out.println("-------- PostgreSQL "
				+ "JDBC Connection Testing ------------");
		
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {

			System.out.println("Where is your PostgreSQL JDBC Driver? "
					+ "Include in your library path!");
			e.printStackTrace();
			return connection;
		}

		System.out.println("PostgreSQL JDBC Driver Registered!");
		try {
			connection = DriverManager.getConnection(url, userName,password);

		} catch (SQLException e) {

			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return connection;
		}

		if (connection != null) {
			System.out.println("You made it, take control your database now!");
		} else {
			System.out.println("Failed to make connection!");
		}
		return connection;
	}

    public ResponseEntity<Feature> addDependenciesToFeature(@ApiParam(value = "ID of the project (e.g. \"1\" or \"siemens\")",required=true ) @PathVariable("projectId") String projectId,
        @ApiParam(value = "ID of the realese",required=true ) @PathVariable("featureId") BigDecimal featureId,
        @ApiParam(value = "Array of Feature ids" ,required=true ) @RequestBody List<FeatureId> body) {
        // do some magic!
        return new ResponseEntity<Feature>(HttpStatus.OK);
    }

    public ResponseEntity<Void> addFeaturesToRelease(@ApiParam(value = "ID of the project (e.g. \"1\" or \"siemens\")",required=true ) @PathVariable("projectId") String projectId,
        @ApiParam(value = "ID of the realese",required=true ) @PathVariable("releaseId") BigDecimal releaseId,
        @ApiParam(value = "Array of Feature ids" ,required=true ) @RequestBody List<FeatureId> body) {
        // do some magic!
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<Release> addNewReleaseToProject(@ApiParam(value = "ID of the project (e.g. \"1\" or \"siemens\")",required=true ) @PathVariable("projectId") String projectId,
        @ApiParam(value = "Parameters of the new Release" ,required=true ) @RequestBody NewRelease body) {
        // do some magic!
        return new ResponseEntity<Release>(HttpStatus.OK);
    }

    public ResponseEntity<Resource> addNewResourceToProject(@ApiParam(value = "ID of the project (e.g. \"1\" or \"siemens\")",required=true ) @PathVariable("projectId") String projectId,
        @ApiParam(value = "Resource parameters" ,required=true ) @RequestBody ResourceData body) {
        // do some magic!
        return new ResponseEntity<Resource>(HttpStatus.OK);
    }

    public ResponseEntity<Skill> addNewSkillToProject(@ApiParam(value = "ID of the project (e.g. \"1\" or \"siemens\")",required=true ) @PathVariable("projectId") String projectId,
        @ApiParam(value = "Skill parameters" ,required=true ) @RequestBody SkillData body) {
        // do some magic!
        return new ResponseEntity<Skill>(HttpStatus.OK);
    }

    public ResponseEntity<Release> addResourcesToRelease(@ApiParam(value = "ID of the project (e.g. \"1\" or \"siemens\")",required=true ) @PathVariable("projectId") String projectId,
        @ApiParam(value = "ID of the realese",required=true ) @PathVariable("releaseId") BigDecimal releaseId,
        @ApiParam(value = "Array of Resource ids" ,required=true ) @RequestBody List<ResourceId> body) {
        // do some magic!
        return new ResponseEntity<Release>(HttpStatus.OK);
    }

    public ResponseEntity<Feature> addSkillsToFeature(@ApiParam(value = "ID of the project (e.g. \"1\" or \"siemens\")",required=true ) @PathVariable("projectId") String projectId,
        @ApiParam(value = "ID of the realese",required=true ) @PathVariable("featureId") BigDecimal featureId,
        @ApiParam(value = "Array of Skill ids" ,required=true ) @RequestBody List<SkillId> body) {
        // do some magic!
        return new ResponseEntity<Feature>(HttpStatus.OK);
    }

    public ResponseEntity<Resource> addSkillsToResource(@ApiParam(value = "ID of the project (e.g. \"1\" or \"siemens\")",required=true ) @PathVariable("projectId") String projectId,
        @ApiParam(value = "ID of the resource",required=true ) @PathVariable("resourceId") BigDecimal resourceId,
        @ApiParam(value = "Array of Skill ids" ,required=true ) @RequestBody List<SkillId> body) {
        // do some magic!
        return new ResponseEntity<Resource>(HttpStatus.OK);
    }

    public ResponseEntity<Void> cancelLastReleasePlan(@ApiParam(value = "ID of the project (e.g. \"1\" or \"siemens\")",required=true ) @PathVariable("projectId") String projectId,
        @ApiParam(value = "ID of the realese",required=true ) @PathVariable("releaseId") BigDecimal releaseId) {
        // do some magic!
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<Feature> createFeature(@ApiParam(value = "ID of the project (e.g. \"1\" or \"siemens\")",required=true ) @PathVariable("projectId") String projectId,
        @ApiParam(value = "Project parameters" ,required=true ) @RequestBody NewFeatureData body) {
        // do some magic!
        return new ResponseEntity<Feature>(HttpStatus.OK);
    }

    public ResponseEntity<Project> createProject(@ApiParam(value = "Project parameters" ,required=true ) @RequestBody NewProjectData body) {
        // do some magic!
        return new ResponseEntity<Project>(HttpStatus.OK);
    }

    public ResponseEntity<Feature> deleteDependenciesFromFeature(@ApiParam(value = "ID of the project (e.g. \"1\" or \"siemens\")",required=true ) @PathVariable("projectId") String projectId,
        @ApiParam(value = "ID of the feature",required=true ) @PathVariable("featureId") BigDecimal featureId,
        @ApiParam(value = "IDs of the features to remove as dependencies", required = true) @RequestParam(value = "featureId2", required = true) List<BigDecimal> featureId2) {
        // do some magic!
        return new ResponseEntity<Feature>(HttpStatus.OK);
    }

    public ResponseEntity<Void> deleteFeature(@ApiParam(value = "ID of the project (e.g. \"1\" or \"siemens\")",required=true ) @PathVariable("projectId") String projectId,
        @ApiParam(value = "ID of the feature",required=true ) @PathVariable("featureId") BigDecimal featureId) {
        // do some magic!
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<Void> deleteProject(@ApiParam(value = "ID of the project (e.g. \"1\" or \"siemens\")",required=true ) @PathVariable("projectId") String projectId) {
        // do some magic!
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<Void> deleteRelease(@ApiParam(value = "ID of the project (e.g. \"1\" or \"siemens\")",required=true ) @PathVariable("projectId") String projectId,
        @ApiParam(value = "ID of the project",required=true ) @PathVariable("releaseId") BigDecimal releaseId) {
        // do some magic!
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<Void> deleteResource(@ApiParam(value = "ID of the project (e.g. \"1\" or \"siemens\")",required=true ) @PathVariable("projectId") String projectId,
        @ApiParam(value = "ID of the resource",required=true ) @PathVariable("resourceId") BigDecimal resourceId) {
        // do some magic!
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<Release> deleteResourcesFromRelease(@ApiParam(value = "ID of the project (e.g. \"1\" or \"siemens\")",required=true ) @PathVariable("projectId") String projectId,
        @ApiParam(value = "ID of the realese",required=true ) @PathVariable("releaseId") BigDecimal releaseId,
        @ApiParam(value = "IDs of the resources to remove", required = true) @RequestParam(value = "resourceId", required = true) List<BigDecimal> resourceId) {
        // do some magic!
        return new ResponseEntity<Release>(HttpStatus.OK);
    }

    public ResponseEntity<Void> deleteSkill(@ApiParam(value = "ID of the project (e.g. \"1\" or \"siemens\")",required=true ) @PathVariable("projectId") String projectId,
        @ApiParam(value = "ID of the project",required=true ) @PathVariable("skillId") BigDecimal skillId) {
        // do some magic!
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<Feature> deleteSkillsFromFeature(@ApiParam(value = "ID of the project (e.g. \"1\" or \"siemens\")",required=true ) @PathVariable("projectId") String projectId,
        @ApiParam(value = "ID of the realese",required=true ) @PathVariable("featureId") BigDecimal featureId,
        @ApiParam(value = "IDs of the skills to remove", required = true) @RequestParam(value = "skillId", required = true) List<BigDecimal> skillId) {
        // do some magic!
        return new ResponseEntity<Feature>(HttpStatus.OK);
    }

    public ResponseEntity<Resource> deleteSkillsFromResource(@ApiParam(value = "ID of the project (e.g. \"1\" or \"siemens\")",required=true ) @PathVariable("projectId") String projectId,
        @ApiParam(value = "ID of the resource",required=true ) @PathVariable("resourceId") BigDecimal resourceId,
        @ApiParam(value = "IDs of the skills to remove", required = true) @RequestParam(value = "skillId", required = true) List<BigDecimal> skillId) {
        // do some magic!
        return new ResponseEntity<Resource>(HttpStatus.OK);
    }

    public ResponseEntity<Feature> getFeature(@ApiParam(value = "ID of the project (e.g. \"1\" or \"siemens\")",required=true ) @PathVariable("projectId") String projectId,
        @ApiParam(value = "ID of the feature",required=true ) @PathVariable("featureId") BigDecimal featureId) {
        // do some magic!
        return new ResponseEntity<Feature>(HttpStatus.OK);
    }

    public ResponseEntity<List<Feature>> getFeatures(@ApiParam(value = "ID of the project (e.g. \"1\" or \"siemens\")",required=true ) @PathVariable("projectId") String projectId,
        @ApiParam(value = "any | pending | scheduled", allowableValues = "ANY, PENDING, SCHEDULED") @RequestParam(value = "status", required = false) String status) {
        // do some magic!
        return new ResponseEntity<List<Feature>>(HttpStatus.OK);
    }

    public ResponseEntity<Project> getProject(@ApiParam(value = "ID of the project (e.g. \"1\" or \"siemens\")",required=true ) @PathVariable("projectId") String projectId) {
        // do some magic!
    	Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		int id;
		String name = null;
		String description = null;
		String effortUnit = null;
		BigDecimal hoursPerEffortUnit = null;
		BigDecimal hoursPerWeekAndFullTimeResource = null;
		//String resources = null;
		
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
        //project.setResources(resources);
        
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<Project>(project, responseHeaders, HttpStatus.OK);
    }

    public ResponseEntity<List<Resource>> getProjectResources(@ApiParam(value = "ID of the project (e.g. \"1\" or \"siemens\")",required=true ) @PathVariable("projectId") String projectId) {
        // do some magic!
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
				
				Resource resource = new Resource();
				resource.setId(id);
				resource.setName(name);
				resource.setDescription(description);
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
        return new ResponseEntity<List<Project>>(HttpStatus.OK);
    }

    public ResponseEntity<Release> getRelease(@ApiParam(value = "ID of the project (e.g. \"1\" or \"siemens\")",required=true ) @PathVariable("projectId") String projectId,
        @ApiParam(value = "ID of the release",required=true ) @PathVariable("releaseId") BigDecimal releaseId) {
        // do some magic!
        return new ResponseEntity<Release>(HttpStatus.OK);
    }

    public ResponseEntity<List<Feature>> getReleaseFeatures(@ApiParam(value = "ID of the project (e.g. \"1\" or \"siemens\")",required=true ) @PathVariable("projectId") String projectId,
        @ApiParam(value = "ID of the realese",required=true ) @PathVariable("releaseId") BigDecimal releaseId) {
        // do some magic!
        return new ResponseEntity<List<Feature>>(HttpStatus.OK);
    }

    public ResponseEntity<Plan> getReleasePlan(@ApiParam(value = "ID of the project (e.g. \"1\" or \"siemens\")",required=true ) @PathVariable("projectId") String projectId,
        @ApiParam(value = "ID of the realese",required=true ) @PathVariable("releaseId") BigDecimal releaseId) {
        // do some magic!
        return new ResponseEntity<Plan>(HttpStatus.OK);
    }

    public ResponseEntity<List<Release>> getReleases(@ApiParam(value = "ID of the project (e.g. \"1\" or \"siemens\")",required=true ) @PathVariable("projectId") String projectId) {
        // do some magic!
        return new ResponseEntity<List<Release>>(HttpStatus.OK);
    }

    public ResponseEntity<Feature> modifyFeature(@ApiParam(value = "ID of the project (e.g. \"1\" or \"siemens\")",required=true ) @PathVariable("projectId") String projectId,
        @ApiParam(value = "ID of the feature",required=true ) @PathVariable("featureId") BigDecimal featureId,
        @ApiParam(value = "Feature parameters that can be modified" ,required=true ) @RequestBody FeatureData body) {
        // do some magic!
        return new ResponseEntity<Feature>(HttpStatus.OK);
    }

    public ResponseEntity<Project> modifyProject(@ApiParam(value = "ID of the project (e.g. \"1\" or \"siemens\")",required=true ) @PathVariable("projectId") String projectId,
        @ApiParam(value = "Project's parameters that can be modified" ,required=true ) @RequestBody ProjectData body) {
        // do some magic!
        return new ResponseEntity<Project>(HttpStatus.OK);
    }

    public ResponseEntity<Release> modifyRelease(@ApiParam(value = "ID of the project (e.g. \"1\" or \"siemens\")",required=true ) @PathVariable("projectId") String projectId,
        @ApiParam(value = "ID of the project",required=true ) @PathVariable("releaseId") BigDecimal releaseId,
        @ApiParam(value = "Release parameters that can be modified" ,required=true ) @RequestBody ReleaseData body) {
        // do some magic!
        return new ResponseEntity<Release>(HttpStatus.OK);
    }

    public ResponseEntity<Resource> modifyResource(@ApiParam(value = "ID of the project (e.g. \"1\" or \"siemens\")",required=true ) @PathVariable("projectId") String projectId,
        @ApiParam(value = "ID of the resource",required=true ) @PathVariable("resourceId") BigDecimal resourceId,
        @ApiParam(value = "Resource parameters that can be modified" ,required=true ) @RequestBody ResourceData body) {
        // do some magic!
        return new ResponseEntity<Resource>(HttpStatus.OK);
    }

    public ResponseEntity<Void> modifySkill(@ApiParam(value = "ID of the project (e.g. \"1\" or \"siemens\")",required=true ) @PathVariable("projectId") String projectId,
        @ApiParam(value = "ID of the project",required=true ) @PathVariable("skillId") BigDecimal skillId,
        @ApiParam(value = "Skill parameters that can be modified" ,required=true ) @RequestBody SkillData body) {
        // do some magic!
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<Void> removeFeaturesFromRelease(@ApiParam(value = "ID of the project (e.g. \"1\" or \"siemens\")",required=true ) @PathVariable("projectId") String projectId,
        @ApiParam(value = "ID of the realese",required=true ) @PathVariable("releaseId") BigDecimal releaseId,
        @ApiParam(value = "IDs of the features to remove", required = true) @RequestParam(value = "featureId", required = true) List<BigDecimal> featureId) {
        // do some magic!
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
