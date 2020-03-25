package pages.expedia;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LandingPage extends PageObject {

    private static final Logger logger = LogManager.getLogger();

    @FindBy(id = "tab-flight-tab-hp")
    private WebElement flights;

    @FindBy(id = "flight-type-one-way-label-hp-flight")
    private WebElement singleFlight;

    @FindBy(id = "flight-origin-hp-flight")
    private WebElement origin;

    @FindBy(id = "flight-destination-hp-flight")
    private WebElement destination;

    @FindBy(id = "flight-departing-single-hp-flight")
    private WebElement departure;

    @FindBy(xpath = "//div[@id='traveler-selector-hp-flight']//button[@class='trigger-utility menu-trigger btn-utility btn-secondary dropdown-toggle theme-standard pin-left menu-arrow gcw-component gcw-traveler-amount-select gcw-component-initialized']")
    private WebElement people;

    @FindBy(xpath = "//form[@id='gcw-flights-form-hp-flight']//button[@class='btn-primary btn-action gcw-submit']")
    private WebElement search;

    public LandingPage(WebDriver driver) {
        super(driver);
    }

    public void selectSingleFlight() {
        flights.click();
        singleFlight.click();
    }

    public SearchResultsPage enterFlightDetailsAndSearch(String from, String to, String departureDate) {
        origin.sendKeys(from);
        destination.sendKeys(to);
        departure.sendKeys(departureDate);
        people.click();
        search.click();

        return new SearchResultsPage(driver);
    }

}
