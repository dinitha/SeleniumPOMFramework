package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutCompletePage extends BasePage {

By backHomeBtn = By.id("back-to-products");

    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
    }

    public String getCheckoutCompletePageLink(){
        return driver.getCurrentUrl();
    }

    public CheckoutCompletePage NavigateToHome(){
        click(backHomeBtn);
        return this;
    }
}
