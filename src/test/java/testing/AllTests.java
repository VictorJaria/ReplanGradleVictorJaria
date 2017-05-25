package testing;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ DependenciesTest.class, FeaturesToRelaseTest.class, FeatureTest.class, ProjectTest.class,
		ReleaseTest.class, ResourcesToReleaseTest.class, ResourceTest.class, SkillsToFeatureTest.class,
		SkillsToResourceTest.class, SkillTest.class })
public class AllTests {

}
