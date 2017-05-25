package testing;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import io.swagger.api.ProjectsApiController;
import io.swagger.model.NewRelease;
import io.swagger.model.Release;
import io.swagger.model.ReleaseData;

public class ReleaseTest {

ProjectsApiController apiController = new ProjectsApiController();
	
	@Test
	public void test() {
		
		/*Totes les releases per testejar getReleases que ho farem al final*/
		ResponseEntity<List<Release>> allReleasesBefore  = apiController.getReleases("1");
		List<Release> listaReleasesBefore = allReleasesBefore.getBody();
		
		
		/*CREATE*/
		
		NewRelease releaseData = new NewRelease();
		releaseData.setName("Release Test");
		releaseData.setDescription("Release per provar el test");
		
		DateTime startsAt = new DateTime(2017, 3, 26, 12, 0, 0, 0);
		releaseData.setStartsAt(startsAt);
		
		DateTime deadline = new DateTime(2018, 3, 26, 12, 0, 0, 0);
		releaseData.setDeadline(deadline);
		
		ResponseEntity<Release> response2  = apiController.addNewReleaseToProject("1", releaseData);
		
		/*READ*/
		Integer idRelease = response2.getBody().getId();
		BigDecimal bigRelease = new BigDecimal(idRelease.toString());
		ResponseEntity<Release> response = apiController.getRelease("1", bigRelease);   

		Release release = response.getBody();
		System.out.println(release);
		Boolean isCorrect = true;

		if (! release.getName().equals("Release Test")) isCorrect = false;		
		if (! release.getDescription().equals("Release per provar el test")) isCorrect = false;
		
		String startsResposta = new DateTime(release.getStartsAt()).toString("MMMM dd, yyyy");
		String startsHardcode = startsAt.toString("MMMM dd, yyyy");

		String deadlineResposta = new DateTime(release.getDeadline()).toString("MMMM dd, yyyy");
		String deadlineHardcode = deadline.toString("MMMM dd, yyyy");
		
		if (! startsResposta.equals(startsHardcode)) isCorrect = false;
		if (! deadlineResposta.equals(deadlineHardcode)) isCorrect = false;
		
		/*UPDATE*/
		ReleaseData rd = new ReleaseData();

		rd.setName("Release Test Modificat");
		rd.setDescription("Release per provar el test Modificat");
		
		DateTime newStartsAt = new DateTime(2017, 4, 26, 12, 0, 0, 0);
		DateTime newDeadline = new DateTime(2018, 4, 26, 12, 0, 0, 0);
		
		rd.setStartsAt(newStartsAt);
		rd.setDeadline(newDeadline);

		apiController.modifyRelease("1", bigRelease, rd);
		ResponseEntity<Release> response3  = apiController.getRelease("1", bigRelease);
		
		release = response3.getBody();
		
		if (! release.getName().equals("Release Test Modificat")) isCorrect = false;
		if (! release.getDescription().equals("Release per provar el test Modificat")) isCorrect = false;
		
		startsResposta = new DateTime(release.getStartsAt()).toString("MMMM dd, yyyy");
		startsHardcode = newStartsAt.toString("MMMM dd, yyyy");

		deadlineResposta = new DateTime(release.getDeadline()).toString("MMMM dd, yyyy");
		deadlineHardcode = newDeadline.toString("MMMM dd, yyyy");
		
		if (! startsResposta.equals(startsHardcode)) isCorrect = false;
		if (! deadlineResposta.equals(deadlineHardcode)) isCorrect = false;

		assertEquals(true, isCorrect);
		
		
		/*Per testejar getReleases abans de borrar*/
		
		for (int i = 0; i < listaReleasesBefore.size(); ++i) {
			Release s = listaReleasesBefore.get(i);
			if (s.getId() == idRelease) {
				isCorrect = false;
			}
		}
		ResponseEntity<List<Release>> allReleasesAfter  = apiController.getReleases("1");
		List<Release> listaReleasesAfter = allReleasesAfter.getBody();
		Boolean existeix = false;
		for (int i = 0; i < listaReleasesAfter.size(); ++i) {
			Release s = listaReleasesAfter.get(i);
			if (s.getId() == idRelease) {
				existeix = true;
			}
		}
		if (!existeix) isCorrect = false;
		
		/*DELETE*/
		apiController.deleteRelease("1", bigRelease);
		ResponseEntity<Release> response4  = apiController.getRelease("1", bigRelease); //mirar

		System.out.println(response4.getBody());
		assertEquals(true, isCorrect);
	}

}
