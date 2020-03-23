package pages.newegg;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchResultsPage extends PageObject {

    private static final Logger logger = LogManager.getLogger();

    @FindBy(xpath = "//span[@id='checkbox_compare']/following-sibling::div[1]")
    private WebElement resultsGrid;

    @FindBy(className = "result-message-error")
    private WebElement noSearchResultsErrorMessage;

    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }

    public int numberOfSearchResultsOnPage() {
        int searchResults = resultsGrid.findElements(By.tagName("div")).size();
        logger.debug("Number of search results: {}", searchResults);
        return searchResults;
    }

    public WebElement getNoSearchResultsErrorMessage() {
        return noSearchResultsErrorMessage;
    }
}
