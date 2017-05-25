package testing;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import io.swagger.api.ProjectsApiController;
import io.swagger.model.Feature;
import io.swagger.model.FeatureData;
import io.swagger.model.NewFeatureData;

public class FeatureTest {
	
	ProjectsApiController apiController = new ProjectsApiController();

	@Test
	public void test() {
		
		/*Totes les features per testejar getFeatures que ho farem al final*/
		ResponseEntity<List<Feature>> allFeaturesBefore  = apiController.getFeatures("1", "");
		List<Feature> listaFeaturesBefore = allFeaturesBefore.getBody();
		
		/*CREATE*/
		
		/*creació dades feature*/
		
		NewFeatureData featureData = new NewFeatureData();
		featureData.setCode(999);
		featureData.setName("Feature Test");
		featureData.setDescription("Feature per provar el test");
		featureData.setEffort(new BigDecimal("10"));
		featureData.setPriority(1);
		DateTime dt = new DateTime(2017, 3, 26, 12, 0, 0, 0);
		featureData.setDeadline(dt);
	
		ResponseEntity<Feature> response2  = apiController.createFeature("1", featureData);
		
		/*READ*/
		Integer idFeature = response2.getBody().getId();
		System.out.println("EI, SOY UN ID: " + idFeature);
		BigDecimal bigFeature = new BigDecimal(idFeature.toString());
		ResponseEntity<Feature> response  = apiController.getFeature("1", bigFeature);    

		Feature feature = response.getBody();		
		Boolean isCorrect = true;
		
		if (feature.getCode() != 999) isCorrect = false;
		if (! feature.getName().equals("Feature Test")) isCorrect = false;
		if (! feature.getDescription().equals("Feature per provar el test")) isCorrect = false;
		if ( feature.getEffort().compareTo(new BigDecimal("10")) != 0  ) isCorrect = false;
		
		String dataResposta = new DateTime(feature.getDeadline()).toString("MMMM dd, yyyy");
		String dataHardcode = dt.toString("MMMM dd, yyyy");
		
		if (! dataResposta.equals(dataHardcode)) isCorrect = false;
		if (feature.getPriority() != 1) isCorrect = false;
		
		//assertEquals(true, isCorrect);
		
		/*UPDATE*/
		FeatureData fd = new FeatureData();

		fd.setName("Feature Test Modificat");
		fd.setDescription("Feature per provar el test Modificat");
		fd.setEffort(new BigDecimal("101"));
		fd.setPriority(2);
		DateTime dt2 = new DateTime(2018, 3, 26, 12, 0, 0, 0);
		fd.setDeadline(dt2);
		
		apiController.modifyFeature("1", bigFeature, fd);
		ResponseEntity<Feature> response3  = apiController.getFeature("1", bigFeature); 
		
		feature = response3.getBody();

		if (! feature.getName().equals("Feature Test Modificat")) isCorrect = false;
		if (! feature.getDescription().equals("Feature per provar el test Modificat")) isCorrect = false;
		if ( feature.getEffort().compareTo(new BigDecimal("101")) != 0  ) isCorrect = false;
		
		dataResposta = new DateTime(feature.getDeadline()).toString("MMMM dd, yyyy");
		dataHardcode = dt2.toString("MMMM dd, yyyy");
		
		if (! dataResposta.equals(dataHardcode)) isCorrect = false;
		if (feature.getPriority() != 2) isCorrect = false;
		
		/*Per testejar getFeatures abans de borrar*/
		
		for (int i = 0; i < listaFeaturesBefore.size(); ++i) {
			Feature s = listaFeaturesBefore.get(i);
			if (s.getId() == idFeature) {
				isCorrect = false;
			}
		}
		ResponseEntity<List<Feature>> allFeaturesAfter  = apiController.getFeatures("1", "");
		List<Feature> listaFeaturesAfter = allFeaturesAfter.getBody();
		Boolean existeix = false;
		for (int i = 0; i < listaFeaturesAfter.size(); ++i) {
			Feature s = listaFeaturesAfter.get(i);
			if (s.getId() == idFeature) {
				existeix = true;
			}
		}
		if (!existeix) isCorrect = false;

		/*DELETE*/
		apiController.deleteFeature("1", bigFeature);
		apiController.getFeature("1", bigFeature);  //FALTA PORQUE DEVUELVE FEATURE con TOdo NULL y no debe ser asi
		//System.out.println(response4);
		
		assertEquals(true, isCorrect);
		
		
	}

}
