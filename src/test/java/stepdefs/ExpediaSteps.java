package stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.WebDriverManager;
import org.junit.Assert;
import pages.expedia.LandingPage;
import pages.expedia.SearchResultsPage;

import java.util.Map;

public class ExpediaSteps {

    private WebDriverManager webDriverManager;
    private LandingPage landingPage;
    private SearchResultsPage searchResultsPage;

    public ExpediaSteps(WebDriverManager webDriverManager) {
        this.webDriverManager = webDriverManager;
    }

    @When("user selects single flight")
    public void userSelectsSingleFlight() {
        landingPage = new LandingPage(webDriverManager.getDriver());
        landingPage.selectSingleFlight();
    }

    @And("user enters the following details:")
    public void userEntersTheFollowingDetails(Map<String, String> stepDetails) {
        searchResultsPage = landingPage.enterFlightDetailsAndSearch(
                stepDetails.get("origin"),
                stepDetails.get("destination"),
                stepDetails.get("date")
        );
    }

    @Then("search results should be displayed")
    public void searchResultsShouldBeDisplayed() {
        Assert.assertTrue(searchResultsPage.getFlightOffersCount() > 0);
    }
}
