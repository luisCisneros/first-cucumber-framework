package stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.PageObject;
import org.example.WebDriverManager;
import org.junit.Assert;
import pages.newegg.LandingPage;
import pages.newegg.ProductPage;
import pages.newegg.SearchResultsPage;

public class NeweggSteps {

    private WebDriverManager webDriverManager;
    private PageObject searchResultsOrProductPage;

    public NeweggSteps(WebDriverManager webDriverManager) {
        this.webDriverManager = webDriverManager;
    }

    @Given("User is on landing page")
    public void userIsOnLandingPage() {
        webDriverManager.loadWebPage();
    }

    @When("User enters {string} on the global search bar")
    public void userEntersOnTheGlobalSearchBar(String searchTerm) {
        LandingPage landingPage = new LandingPage(webDriverManager.getDriver());
        landingPage.selectCountryOnPopUp();
        searchResultsOrProductPage = landingPage.searchFor(searchTerm);
    }

    @Then("At least one result should be shown on Results Page")
    public void resultsShouldBeShownOnResultsPage() {
        SearchResultsPage searchResultsPage = (SearchResultsPage) searchResultsOrProductPage;
        Assert.assertTrue(searchResultsPage.numberOfSearchResultsOnPage() > 1);
    }

//    @Then("Product Page should be displayed with the following details present:")
//    public void productPageShouldBeDisplayedWithTheFollowingDetailsPresent(List<String> scenarioDetails) {
//        ProductPage productPage = (ProductPage) searchResultsOrProductPage;
//        Assert.assertEquals(scenarioDetails.get(0), productPage.getItemNumber());
//        Assert.assertTrue(productPage.getProductName().contains(scenarioDetails.get(1)));
//    }

    @Then("Product Page should be displayed and {string}, {string} must be present")
    public void productPageShouldBeDisplayedAndMustBePresent(String itemNumber, String productName) {
        ProductPage productPage = (ProductPage) searchResultsOrProductPage;
        Assert.assertEquals(itemNumber, productPage.getItemNumber());
        Assert.assertTrue(productPage.getProductName().contains(productName));
    }

    @Then("No search results message should be displayed")
    public void noSearchResultsMessageShouldBeDisplayed() {
        SearchResultsPage searchResultsPage = (SearchResultsPage) searchResultsOrProductPage;
        Assert.assertTrue(searchResultsPage.getNoSearchResultsErrorMessage().isDisplayed());
    }
}
