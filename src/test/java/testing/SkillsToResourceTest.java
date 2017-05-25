package testing;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.http.ResponseEntity;

import io.swagger.api.ProjectsApiController;
import io.swagger.model.Resource;
import io.swagger.model.Skill;
import io.swagger.model.SkillId;

public class SkillsToResourceTest {

	ProjectsApiController apiController = new ProjectsApiController();

	@Test
	public void test() {
		Boolean isCorrect = true;	
		
		ResponseEntity<List<Resource>> allResourceBefore  = apiController.getProjectResources("1");
		List<Resource> listaResourcesBefore = allResourceBefore.getBody();

		for (int i = 0; i < listaResourcesBefore.size(); ++i) {
			Resource r = listaResourcesBefore.get(i);
			List<Skill> llista = r.getSkills();
			for (int j = 0; j < llista.size(); ++j) {
				Skill s = llista.get(j);
				if (r.getId() == 4 && s.getId() == 2) {
					isCorrect = false;
					System.out.println("YA EXISTE LA DEPENDENCIA");
				}
			}
		}

		Integer inte = 2;
		SkillId sI = new SkillId();
		sI.setSkillId(inte);
		List<SkillId> llista = new ArrayList<SkillId>();
		llista.add(sI);
		
		apiController.addSkillsToResource("1", new BigDecimal("4"), llista);
		
		ResponseEntity<List<Resource>> allResourcesBefore2  = apiController.getProjectResources("1");
		List<Resource> listaResourcesBefore2 = allResourcesBefore2.getBody();
		
		isCorrect = false;
		
		for (int i = 0; i < listaResourcesBefore2.size(); ++i) {
			Resource r = listaResourcesBefore2.get(i);
			List<Skill> llista2 = r.getSkills();
			for (int j = 0; j < llista2.size(); ++j) {
				Skill s = llista2.get(j);
				if (r.getId() == 4 && s.getId() == 2) {
					isCorrect = true;
					System.out.println("BIEEEN");
				}
			}
		}
		
		BigDecimal b = new BigDecimal("2");
		List<BigDecimal> llista2 = new ArrayList<BigDecimal>();
		llista2.add(b);
		
		apiController.deleteSkillsFromResource("1", new BigDecimal("4"), llista2);
		
		ResponseEntity<List<Resource>> allResourcesAfter  = apiController.getProjectResources("1");
		List<Resource> listaResourcesAfter = allResourcesAfter.getBody();

		for (int i = 0; i < listaResourcesAfter.size(); ++i) {
			Resource r = listaResourcesAfter.get(i);
			List<Skill> llista3 = r.getSkills();
			for (int j = 0; j < llista3.size(); ++j) {
				Skill s = llista3.get(j);
				if (r.getId() == 4 && s.getId() == 2) {
					isCorrect = false;
					System.out.println("MAAAAL");
				}
			}
		}

		assertEquals(true, isCorrect);
	}

}
