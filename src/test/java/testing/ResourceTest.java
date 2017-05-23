package testing;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.springframework.http.ResponseEntity;

import io.swagger.api.ProjectsApiController;

import io.swagger.model.Resource;
import io.swagger.model.ResourceData;

public class ResourceTest {

	ProjectsApiController apiController = new ProjectsApiController();
	
	@Test
	public void test() {
		/*CREATE*/
		
		/*creació dades Resource*/
		
		ResourceData resourceData = new ResourceData();
		resourceData.setName("Resource Test");
		resourceData.setDescription("Resource per provar el test");
		resourceData.setAvailability(new BigDecimal("41"));

		ResponseEntity<Resource> response2  = apiController.addNewResourceToProject("1", resourceData);

		/*READ*/
		Integer idResource = response2.getBody().getId();
		BigDecimal bigResource = new BigDecimal(idResource.toString());
		ResponseEntity<List<Resource>> response  = apiController.getProjectResources("1");    

		Resource resource = new Resource();
		List<Resource> listaResources = response.getBody();
		for (int i = 0; i < listaResources.size(); ++i) {
			Resource r = listaResources.get(i);
			if (r.getId() == idResource) {
				resource = r;
			}
		}

		Boolean isCorrect = true;

		if (! resource.getName().equals("Resource Test")) isCorrect = false;
		if (! resource.getDescription().equals("Resource per provar el test")) isCorrect = false;
		if (resource.getAvailability() != 41) isCorrect = false;

		//assertEquals(true, isCorrect);
		/*UPDATE*/
		ResourceData rd = new ResourceData();

		rd.setName("Resource Test Modificat");
		rd.setDescription("Resource per provar el test Modificat");
		rd.setAvailability(new BigDecimal("42"));

		ResponseEntity<Resource> responseModify  = apiController.modifyResource("1", bigResource, rd);
		ResponseEntity<List<Resource>> response3  = apiController.getProjectResources("1"); 
		
		listaResources = response3.getBody();
		for (int i = 0; i < listaResources.size(); ++i) {
			Resource r = listaResources.get(i);
			if (r.getId() == idResource) {
				resource = r;
				System.out.println("HOLA SOY UNA RESOURCE " + resource);
			}
		}
		if (! resource.getName().equals("Resource Test Modificat")) isCorrect = false;
		if (! resource.getDescription().equals("Resource per provar el test Modificat")) isCorrect = false;
		if (resource.getAvailability() != 42) isCorrect = false;

		
		/*DELETE*/
		ResponseEntity<Void> responseDelete  = apiController.deleteResource("1", bigResource);
		ResponseEntity<List<Resource>> response4  = apiController.getProjectResources("1"); 

		listaResources = response4.getBody();
		for (int i = 0; i < listaResources.size(); ++i) {
			Resource r = listaResources.get(i);
			if (r.getId() == idResource) {
				resource = r;
				isCorrect = false;
			}
		}
		assertEquals(true, isCorrect);
	}

}
