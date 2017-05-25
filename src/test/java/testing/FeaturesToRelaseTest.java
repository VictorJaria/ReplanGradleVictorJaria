package testing;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.http.ResponseEntity;

import io.swagger.api.ProjectsApiController;

import io.swagger.model.Release;

public class FeaturesToRelaseTest {

	ProjectsApiController apiController = new ProjectsApiController();

	@Test
	public void test() {
		Boolean isCorrect = true;		
		
		/*Totes les features per testejar getFeatures que ho farem al final*/
		ResponseEntity<List<Release>> allReleasesBefore  = apiController.getReleases("1");
		List<Release> listaReleasesBefore = allReleasesBefore.getBody();
		
		System.out.println(listaReleasesBefore);
		/*ESTE TEST AUN NO SE PUEDE HACER*/

		/*for (int i = 0; i < listaFeaturesBefore.size(); ++i) {
			Feature f = listaFeaturesBefore.get(i);
			List<Feature> llista = f.getDependsOn();
			for (int j = 0; j < llista.size(); ++j) {
				Feature dependencia = llista.get(j);
				if (dependencia.getId() == 2) {
					isCorrect = false;
					System.out.println("YA EXISTE LA DEPENDENCIA");
				}
			}
		}*/

		/*Integer inte = 2;
		FeatureId fI = new FeatureId();
		fI.setFeatureId(inte);
		List<FeatureId> llista = new ArrayList<FeatureId>();
		llista.add(fI);
		
		apiController.addDependenciesToFeature("1", new BigDecimal("5"), llista);
		
		
		ResponseEntity<List<Feature>> allFeaturesBefore2  = apiController.getFeatures("1", "");
		List<Feature> listaFeaturesBefore2 = allFeaturesBefore2.getBody();
		
		isCorrect = false;
		for (int i = 0; i < listaFeaturesBefore2.size(); ++i) {
			Feature f = listaFeaturesBefore2.get(i);
			List<Feature> llista2 = f.getDependsOn();
			for (int j = 0; j < llista2.size(); ++j) {
				Feature dependencia = llista2.get(j);
				if (dependencia.getId() == 2) {
					isCorrect = true;
					System.out.println("BIEEEEEN");
				}
			}
		}
		
		BigDecimal b = new BigDecimal("2");
		List<BigDecimal> llista2 = new ArrayList<BigDecimal>();
		llista2.add(b);
		
		apiController.deleteDependenciesFromFeature("1", new BigDecimal("5"), llista2);
		
		ResponseEntity<List<Feature>> allFeaturesAfter  = apiController.getFeatures("1", "");
		List<Feature> listaFeaturesAfter = allFeaturesAfter.getBody();

		for (int i = 0; i < listaFeaturesAfter.size(); ++i) {
			Feature f = listaFeaturesAfter.get(i);
			List<Feature> llistaAfter = f.getDependsOn();
			for (int j = 0; j < llistaAfter.size(); ++j) {
				Feature dependencia = llistaAfter.get(j);
				if (dependencia.getId() == 2) {
					isCorrect = false;
					System.out.println("MAAAAL");
				}
			}
		}*/
		
		
		assertEquals(true, isCorrect);
	}

}
