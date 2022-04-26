package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage extends BasePage {

    By fNameTxt = By.id("first-name");
    By lNameTxt = By.id("last-name");
    By zipTxt = By.id("postal-code");
    By continueBtn = By.id("continue");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public String getCheckoutPageLink(){
        return driver.getCurrentUrl();
    }

    public CheckoutPage enterCheckoutDetails(String fName, String lName, String zip) {
        writeText(fNameTxt,fName);
        writeText(lNameTxt,lName);
        writeText(zipTxt,zip);
        return this;
    }

    public CheckoutPage continueCheckout(){
        click(continueBtn);
        return this;
    }
}
