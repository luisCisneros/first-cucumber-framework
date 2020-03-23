package stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.WebDriverManager;

public class ExpediaSteps {

    private WebDriverManager webDriverManager;

    public ExpediaSteps(WebDriverManager webDriverManager) {
        this.webDriverManager = webDriverManager;
    }

    @Given("User is on expedia landing page")
    public void userIsOnExpediaLandingPage() {
    }

    @When("User selects single flight")
    public void userSelectsSingleFlight() {
    }

    @And("User enters the following details:")
    public void userEntersTheFollowingDetails() {
    }

    @Then("Search results should be displayed")
    public void searchResultsShouldBeDisplayed() {
    }
}
