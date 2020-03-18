package stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.PageObject;
import org.example.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.LandingPage;
import pages.ProductPage;
import pages.SearchResultsPage;

public class StepDefinitions {

    private WebDriver driver;
    private WebDriverManager webDriverManager;
    private LandingPage landingPage;
    private PageObject searchResultsOrProductPage;

    private String searchTerm;

    @Given("User is on landing page")
    public void userIsOnLandingPage() {
        webDriverManager = new WebDriverManager();
        driver = webDriverManager.setUpDriver();
        driver.get(webDriverManager.getUrl());
    }

    @When("User enters {string} on the global search bar")
    public void userEntersOnTheGlobalSearchBar(String searchTerm) {
        this.searchTerm = searchTerm;
        landingPage = new LandingPage(driver);
        searchResultsOrProductPage = landingPage.searchFor(searchTerm);
    }

    @Then("At least one result should be shown on Results Page")
    public void resultsShouldBeShownOnResultsPage() {
        SearchResultsPage searchResultsPage = (SearchResultsPage) searchResultsOrProductPage;
        Assert.assertTrue(searchResultsPage.numberOfSearchResultsOnPage() > 1);
    }

    @Then("Product Page should be displayed")
    public void productPageShouldBeDisplayed() {
        ProductPage productPage = (ProductPage) searchResultsOrProductPage;
        Assert.assertEquals(searchTerm, productPage.getItemNumber());
    }
}
