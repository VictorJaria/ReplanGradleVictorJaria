package testing;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;
import org.springframework.http.ResponseEntity;

import io.swagger.api.ProjectsApiController;
import io.swagger.model.NewProjectData;
import io.swagger.model.Project;
import io.swagger.model.ProjectData;


public class ProjectTest {

	ProjectsApiController apiController = new ProjectsApiController();
	
	@Test
	public void test() {
		/*CREATE*/
		
		/*creació dades Project*/
		
		NewProjectData projectData = new NewProjectData();
		projectData.setName("Project Test");
		projectData.setDescription("Project per provar el test");
		projectData.setEffortUnit("hour");
		projectData.setHoursPerEffortUnit(new BigDecimal("6"));
		projectData.setHoursPerWeekAndFullTimeResource(new BigDecimal("90"));

		ResponseEntity<Project> response2  = apiController.createProject(projectData);

		/*READ*/
		System.out.println(response2.getBody());
		String idProject = response2.getBody().getId();
		ResponseEntity<Project> response = apiController.getProject(idProject);    

		Project project = response.getBody();
		
		System.out.println(project);
		Boolean isCorrect = true;

		if (! project.getName().equals("Project Test")) isCorrect = false;		
		if (! project.getDescription().equals("Project per provar el test")) isCorrect = false;
		if (! project.getEffortUnit().equals("hour")) isCorrect = false;
		if (project.getHoursPerEffortUnit().compareTo(new BigDecimal("6")) != 0) isCorrect = false;
		if (project.getHoursPerWeekAndFullTimeResource().compareTo(new BigDecimal("90")) != 0) isCorrect = false;

		//assertEquals(true, isCorrect);
		/*UPDATE*/
		ProjectData pd = new ProjectData();

		pd.setEffortUnit("days");
		pd.setHoursPerEffortUnit(new BigDecimal("7"));
		pd.setHoursPerWeekAndFullTimeResource(new BigDecimal("91"));

		ResponseEntity<Project> responseModify  = apiController.modifyProject(idProject, pd);
		ResponseEntity<Project> response3 = apiController.getProject(idProject); 
		
		project = response3.getBody();

		if (! project.getEffortUnit().equals("days")) isCorrect = false;
		if (project.getHoursPerEffortUnit().compareTo(new BigDecimal("7")) != 0) isCorrect = false;
		if (project.getHoursPerWeekAndFullTimeResource().compareTo(new BigDecimal("91")) != 0) isCorrect = false;
		
		//assertEquals(true, isCorrect);
		/*DELETE*/
		ResponseEntity<Void> responseDelete  = apiController.deleteProject(idProject);
		ResponseEntity<Project> response4  = apiController.getProject(idProject); 

		System.out.println(response4.getBody());
		assertEquals(true, isCorrect);
	}

}
