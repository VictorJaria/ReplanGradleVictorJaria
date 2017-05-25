package testing;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.springframework.http.ResponseEntity;

import io.swagger.api.ProjectsApiController;
import io.swagger.model.Skill;
import io.swagger.model.SkillData;

public class SkillTest {

	ProjectsApiController apiController = new ProjectsApiController();
	
	@Test
	public void test() {
		/*CREATE*/
		
		/*creació dades Skill*/
		
		SkillData skillData = new SkillData();
		skillData.setName("Skill Test");
		skillData.setDescription("Skill per provar el test");

		ResponseEntity<Skill> response2  = apiController.addNewSkillToProject("1", skillData);

		/*READ*/
		Integer idSkill = response2.getBody().getId();
		BigDecimal bigSkill = new BigDecimal(idSkill.toString());
		ResponseEntity<List<Skill>> response = apiController.getProjectSkills("1");    

		Skill skill = new Skill();
		List<Skill> listaSkills = response.getBody();
		for (int i = 0; i < listaSkills.size(); ++i) {
			Skill s = listaSkills.get(i);
			if (s.getId() == idSkill) {
				skill = s;
			}
		}
		System.out.println(skill);

		Boolean isCorrect = true;

		if (! skill.getName().equals("Skill Test")) isCorrect = false;		
		if (! skill.getDescription().equals("Skill per provar el test")) isCorrect = false;

		//assertEquals(true, isCorrect);
		/*UPDATE*/
		SkillData sd = new SkillData();

		sd.setName("Skill Test Modificat");
		sd.setDescription("Skill per provar el test Modificat");

		apiController.modifySkill("1", bigSkill, sd);
		ResponseEntity<List<Skill>> response3  = apiController.getProjectSkills("1"); 
		
		listaSkills = response3.getBody();
		for (int i = 0; i < listaSkills.size(); ++i) {
			Skill s = listaSkills.get(i);
			if (s.getId() == idSkill) {
				skill = s;
			}
		}
		if (! skill.getName().equals("Skill Test Modificat")) isCorrect = false;
		if (! skill.getDescription().equals("Skill per provar el test Modificat")) isCorrect = false;
		
		//assertEquals(true, isCorrect);
		/*DELETE*/
		apiController.deleteSkill("1", bigSkill);
		ResponseEntity<List<Skill>> response4  = apiController.getProjectSkills("1"); 

		listaSkills = response4.getBody();
		for (int i = 0; i < listaSkills.size(); ++i) {
			Skill s = listaSkills.get(i);
			if (s.getId() == idSkill) {
				skill = s;
				isCorrect = false;
			}
		}
		assertEquals(true, isCorrect);
	}
}
