package testing;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;
import org.springframework.http.ResponseEntity;

import io.swagger.api.ProjectsApiController;
import io.swagger.model.Plan;

public class PlanTest {

	ProjectsApiController apiController = new ProjectsApiController();
	
	@Test
	public void test() {
		ResponseEntity<Plan> responsePlan = apiController.getReleasePlan("1", new BigDecimal("1"));
		Plan plan = responsePlan.getBody();
		System.out.println(plan);
	}

}
