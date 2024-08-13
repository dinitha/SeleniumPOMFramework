package org.framework.pages;

import org.framework.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MenuPage extends BasePage {

    public MenuPage(WebDriver driver) {
        super(driver);
    }

    By menuBtn = By.id("react-burger-menu-btn");
    By allItemsBtn = By.id("inventory_sidebar_link");
    By aboutBtn = By.id("about_sidebar_link");
    By logoutBtn = By.id("logout_sidebar_link");
    By resetAppStateBtn = By.id("reset_sidebar_link");
    By closeBtn = By.id("react-burger-cross-btn");

    public MenuPage getMenu(){
        click(menuBtn);
        return this;
    }
    public MenuPage selectAllItems(){
        click(allItemsBtn);
        return this;
    }
    public MenuPage selectAbout(){
        click(aboutBtn);
        return this;
    }
    public MenuPage selectLogout(){
        click(logoutBtn);
        return this;
    }
    public MenuPage selectResetApp(){
        click(resetAppStateBtn);
        return this;
    }
    public String getCurrentLink(){
        return driver.getCurrentUrl();
    }
    public MenuPage closeMenu(){
        WebElement element = driver.findElement(closeBtn);
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", element);

        return this;
    }
}
