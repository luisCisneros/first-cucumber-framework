package stepdefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.WebDriverManager;

public class Hooks {

    private static final Logger logger = LogManager.getLogger();
    private WebDriverManager webDriverManager;

    public Hooks(WebDriverManager webDriverManager) {
        this.webDriverManager = webDriverManager;
    }

    @Before("@expedia")
    public void setExpediaUrl() {
        logger.debug("Inside setExpediaUrl()");
        webDriverManager.setUrl("https://www.expedia.com/");
    }

    @Before("@newegg")
    public void setNeweggUrl() {
        logger.debug("Inside setNeweggUrl()");
        webDriverManager.setUrl("https://www.newegg.com/");
    }

    @Before
    public void setUp() {
        logger.debug("Inside setUp()");
        webDriverManager.setUpDriver();
    }

    @After
    public void after(Scenario scenario) {
        webDriverManager.teardown(scenario);
    }
}
