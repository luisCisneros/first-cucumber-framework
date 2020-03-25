package pages.expedia;

import org.example.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SearchResultsPage extends PageObject {

    @FindBy(css = "li[data-test-id='offer-listing']")
    private List<WebElement> flightOffers;

    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }

    public int getFlightOffersCount() {
        return flightOffers.size();
    }
}
