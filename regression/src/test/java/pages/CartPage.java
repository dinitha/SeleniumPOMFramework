package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {
    By continueShoppingBtn = By.id("continue-shopping");
    By cartItemCountBtn = By.xpath("//span[@class='shopping_cart_badge']");
    By emptyCartBtn = By.cssSelector("a.shopping_cart_link");

    public CartPage(WebDriver driver) {
        super(driver);

    }

    public String getCartPageLink(){
        return driver.getCurrentUrl();
    }

    public CartPage continueShopping(){
        click(continueShoppingBtn);
        return this;
    }


    public int getNoOfItems() {
        return Integer.parseInt(readText(cartItemCountBtn));
    }

    public String getEmptyShoppingCart() {
        return readText(emptyCartBtn);
    }
}
