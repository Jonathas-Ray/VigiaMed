import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
        EntityTests.class,
        ModelTests.class,
        ConversionTests.class,
        LocalRepositoryTests.class,
        ApplicationTests.class,
        FacadeTests.class,
        ControllerTests.class
})
public class VigiaMedTestSuite {
}