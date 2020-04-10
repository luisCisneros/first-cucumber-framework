package stepdefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.example.WebDriverManager;

public class Hooks {

    private WebDriverManager webDriverManager;

    public Hooks(WebDriverManager webDriverManager) {
        this.webDriverManager = webDriverManager;
    }

    @Before
    public void setUp() {
        webDriverManager.setUpDriver();
    }

    @After
    public void after(Scenario scenario) {
        webDriverManager.teardown(scenario);
    }
}
