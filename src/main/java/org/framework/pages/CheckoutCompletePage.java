package org.framework.pages;

import org.framework.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutCompletePage extends BasePage {

By backHomeBtn = By.id("back-to-products");
    By thankYouLabel = By.xpath("//h2[@data-test='complete-header']");


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

    public String getThankYouMessage(){
     return readText(thankYouLabel);

    }
}
