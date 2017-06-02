package testing;

import static org.junit.Assert.*;

import java.io.IOException;
import java.math.BigDecimal;

import org.apache.http.client.ClientProtocolException;
import org.json.simple.parser.ParseException;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import io.swagger.api.ProjectsApiController;
import io.swagger.model.Plan;

public class PlanTest {

	ProjectsApiController apiController = new ProjectsApiController();
	
	@Test
	public void test() throws ParseException, ClientProtocolException, IOException {
		ResponseEntity<Plan> responsePlan = apiController.getReleasePlan("1", new BigDecimal("1"));
		Plan plan = responsePlan.getBody();
		System.out.println(plan);
		assertEquals(true, true);
	}

}
