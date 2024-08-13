package org.framework.pages;

import org.framework.base.BasePage;
import org.framework.utils.propertyreader.PropertyReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    WebDriver webDriver;
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    By usernameTxt = By.id("user-name");
    By passwordTxt = By.id("password");
    By loginBtn = By.id("login-button");
    By ErrorLbl = By.xpath("//h3[@data-test='error']");

    public LoginPage goToLoginPage(){
        driver.get(PropertyReader.getProperty("ApplicationUrl"));
        return this;
    }

    public LoginPage loginAsStandardUser(){
        typeText(usernameTxt,PropertyReader.getProperty("standard_user"));
        typeText(passwordTxt,PropertyReader.getProperty("standard_user_password"));
        click(loginBtn);
        return this;
    }
    public LoginPage loginAsLockedOutUser(){
        typeText(usernameTxt,PropertyReader.getProperty("locked_out_user"));
        typeText(passwordTxt,PropertyReader.getProperty("locked_out_user_password"));
        click(loginBtn);
        return this;
    }
    public LoginPage loginAsProblemUser(){
        typeText(usernameTxt,PropertyReader.getProperty("problem_user"));
        typeText(passwordTxt,PropertyReader.getProperty("problem_user_password"));
        click(loginBtn);
        return this;
    }
    public LoginPage loginAsPerformanceGlitchUser(){
        typeText(usernameTxt,PropertyReader.getProperty("performance_glitch_user"));
        typeText(passwordTxt,PropertyReader.getProperty("performance_glitch_user_password"));
        click(loginBtn);
        return this;
    }


}
