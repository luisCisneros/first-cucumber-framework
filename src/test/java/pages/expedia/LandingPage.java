package pages.expedia;

import org.example.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LandingPage extends PageObject {

    @FindBy(id = "tab-flight-tab-hp")
    private WebElement flights;

    @FindBy(id = "flight-type-one-way-hp-flight")
    private WebElement singleFlight;

    @FindBy(id = "flight-origin-hp-flight")
    private WebElement origin;

    @FindBy(id = "flight-destination-hp-flight")
    private WebElement destination;

    @FindBy(id = "flight-departing-single-hp-flight")
    private WebElement departure;

    @FindBy(xpath = "//form[@id='gcw-flights-form-hp-flight']//button[@class='btn-primary btn-action gcw-submit']")
    private WebElement search;

    public LandingPage(WebDriver driver) {
        super(driver);
    }

}
