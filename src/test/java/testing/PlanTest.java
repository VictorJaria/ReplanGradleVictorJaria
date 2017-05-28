package testing;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

import io.swagger.api.ProjectsApiController;

public class PlanTest {

	ProjectsApiController apiController = new ProjectsApiController();
	
	@Test
	public void test() {
		apiController.getReleasePlan("1", new BigDecimal("1"));
	}

}
