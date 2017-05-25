package testing;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.http.ResponseEntity;

import io.swagger.api.ProjectsApiController;
import io.swagger.model.Release;
import io.swagger.model.Resource;
import io.swagger.model.ResourceId;


public class ResourcesToReleaseTest {

	ProjectsApiController apiController = new ProjectsApiController();

	@Test
	public void test() {
		Boolean isCorrect = true;	
		
		ResponseEntity<List<Release>> allReleaseBefore  = apiController.getReleases("1");
		List<Release> listaReleaseBefore = allReleaseBefore.getBody();

		for (int i = 0; i < listaReleaseBefore.size(); ++i) {
			Release rel = listaReleaseBefore.get(i);
			List<Resource> llista = rel.getResources();
			for (int j = 0; j < llista.size(); ++j) {
				Resource res = llista.get(j);
				if (rel.getId() == 2 && res.getId() == 4) {
					isCorrect = false;
					System.out.println("YA EXISTE LA DEPENDENCIA");
				}
			}
		}

		Integer inte = 4;
		ResourceId rI = new ResourceId();
		rI.setResourceId(inte);
		List<ResourceId> llista = new ArrayList<ResourceId>();
		llista.add(rI);
		
		apiController.addResourcesToRelease("1", new BigDecimal("2"), llista);
		
		ResponseEntity<List<Release>> allReleaseBefore2  = apiController.getReleases("1");
		List<Release> listaReleaseBefore2 = allReleaseBefore2.getBody();
		
		isCorrect = false;
		
		for (int i = 0; i < listaReleaseBefore2.size(); ++i) {
			Release rel = listaReleaseBefore2.get(i);
			List<Resource> llista2 = rel.getResources();
			for (int j = 0; j < llista2.size(); ++j) {
				Resource res = llista2.get(j);
				System.out.println("RELEASE: " + rel.getId());
				System.out.println("RESOURCE: " + res.getId());
				if (rel.getId() == 2 && res.getId() == 4) {
					isCorrect = true;
					System.out.println("BIEEEN");
				}
			}
		}
		
		BigDecimal b = new BigDecimal("4");
		List<BigDecimal> llista2 = new ArrayList<BigDecimal>();
		llista2.add(b);
		
		apiController.deleteResourcesFromRelease("1", new BigDecimal("2"), llista2);
		
		ResponseEntity<List<Release>> allResourcesAfter  = apiController.getReleases("1");
		List<Release> listaReleaseAfter = allResourcesAfter.getBody();

		for (int i = 0; i < listaReleaseAfter.size(); ++i) {
			Release rel = listaReleaseAfter.get(i);
			List<Resource> llista3 = rel.getResources();
			for (int j = 0; j < llista3.size(); ++j) {
				Resource res = llista3.get(j);
				System.out.println("RELEASE: " + rel.getId());
				System.out.println("RESOURCE: " + res.getId());
				if (rel.getId() == 2 && res.getId() == 4) {
					isCorrect = false;
					System.out.println("MAAAAL");
				}
			}
		}
		assertEquals(true, isCorrect);
	}

}
