import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features="cases", glue="",plugin = { "pretty", "html:target/cucumber-reports.html" })
public class TestClass
{

}