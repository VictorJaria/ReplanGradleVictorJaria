package testing;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ FeatureTest.class, ProjectTest.class, ReleaseTest.class, ResourceTest.class, SkillTest.class })
public class AllTests {

}
