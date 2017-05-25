package testing;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.http.ResponseEntity;

import io.swagger.api.ProjectsApiController;
import io.swagger.model.Feature;
import io.swagger.model.Skill;
import io.swagger.model.SkillId;

public class SkillsToFeatureTest {

	ProjectsApiController apiController = new ProjectsApiController();

	@Test
	public void test() {
		Boolean isCorrect = true;		
		
		ResponseEntity<List<Feature>> allFeaturesBefore  = apiController.getFeatures("1", "");
		List<Feature> listaFeaturesBefore = allFeaturesBefore.getBody();

		for (int i = 0; i < listaFeaturesBefore.size(); ++i) {
			Feature f = listaFeaturesBefore.get(i);
			List<Skill> llista = f.getRequiredSkills();
			for (int j = 0; j < llista.size(); ++j) {
				Skill s = llista.get(j);
				if (f.getId() == 5 && s.getId() == 2) {
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
		
		apiController.addSkillsToFeature("1", new BigDecimal("5"), llista);
		
		
		ResponseEntity<List<Feature>> allFeaturesBefore2  = apiController.getFeatures("1", "");
		List<Feature> listaFeaturesBefore2 = allFeaturesBefore2.getBody();
		
		System.out.println("SOY EL CORRECT: " + isCorrect);
		isCorrect = false;
		for (int i = 0; i < listaFeaturesBefore2.size(); ++i) {
			Feature f = listaFeaturesBefore2.get(i);
			List<Skill> llista2 = f.getRequiredSkills();
			for (int j = 0; j < llista2.size(); ++j) {
				Skill s = llista2.get(j);
				if (f.getId() == 5 && s.getId() == 2) {
					isCorrect = true;
					System.out.println("BIEEEN");
				}
			}
		}
		
		BigDecimal b = new BigDecimal("2");
		List<BigDecimal> llista2 = new ArrayList<BigDecimal>();
		llista2.add(b);
		
		apiController.deleteSkillsFromFeature("1", new BigDecimal("5"), llista2);
		
		ResponseEntity<List<Feature>> allFeaturesAfter  = apiController.getFeatures("1", "");
		List<Feature> listaFeaturesAfter = allFeaturesAfter.getBody();

		for (int i = 0; i < listaFeaturesAfter.size(); ++i) {
			Feature f = listaFeaturesAfter.get(i);
			List<Skill> llista3 = f.getRequiredSkills();
			for (int j = 0; j < llista3.size(); ++j) {
				Skill s = llista3.get(j);
				if (f.getId() == 5 && s.getId() == 2) {
					isCorrect = false;
					System.out.println("MAAAAL");
				}
			}
		}

		assertEquals(true, isCorrect);
	}

}
