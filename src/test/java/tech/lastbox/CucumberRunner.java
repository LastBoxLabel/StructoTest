package tech.lastbox;
import com.github.javafaker.Faker;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"html:target/cucumber-reports/Cucumber.html"},
        features = "src/test/resources/features",
        glue = "tech.lastbox.stepdefinition"
)
public class CucumberRunner {
    private final Faker faker = new Faker();
    private String email;
    private String username;
    private String name;
    private String password;
}
