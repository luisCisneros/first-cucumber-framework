package pages;

import org.example.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductPage extends PageObject {

    @FindBy(xpath = "//ol[@id='baBreadcrumbTop']/li/em")
    private WebElement itemNumber;

    @FindBy(css = "span[id*='grpDescrip']")
    private WebElement productName;

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public String getItemNumber() {
        return itemNumber.getText();
    }

    public String getProductName() {
        return productName.getText();
    }
}
