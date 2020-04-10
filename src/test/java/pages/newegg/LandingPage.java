package pages.newegg;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.PageObject;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

public class LandingPage extends PageObject {

    private static final Logger logger = LogManager.getLogger();

    @FindBy(id = "SearchBox2020")
    private WebElement searchBox;

    @FindBy(xpath = "//div[starts-with(@id,'centerPopup')]//div[@class='centerPopup-body']")
    private WebElement selectCountryPopUp;

    public LandingPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getSelectCountryPopUp() {
        return selectCountryPopUp;
    }

    public PageObject searchFor(String searchTerm) {
        searchBox.sendKeys(searchTerm);
        logger.debug("Search term used: {}", searchTerm);
        searchBox.sendKeys(Keys.ENTER);
        // Matches if the search term received is an item number, and returns the Product Page directly instead of Search Results Page.
        if (searchTerm.matches("^[A-Z0-9]{14,15}$")) {
            logger.debug("Search term {} identified as an item number. Returning Product Page", searchTerm);
            return new ProductPage(driver);
        }
        return new SearchResultsPage(driver);
    }

    public void selectCountryOnPopUp() {
        if (selectCountryPopUp.isDisplayed()) {
            logger.debug("Select country popup is displayed");
            WebElement stayAtButton = selectCountryPopUp.findElement(By.xpath("//button[@class='btn forchangecounrtypopup'][contains(text(),'Stay at United States')]"));
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", stayAtButton);
            logger.debug("Clicked on Stay at button on popup");
            return;
        }
        logger.debug("Select country popup is not displayed");
    }
}
