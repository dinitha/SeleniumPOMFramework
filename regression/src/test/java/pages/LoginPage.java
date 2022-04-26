package pages;

import com.framework.core.PropertyReader;
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
        driver.get(PropertyReader.getPropertyFromFile("url"));
        return this;
    }

    public LoginPage loginAsStandardUser(){
        writeText(usernameTxt,PropertyReader.getPropertyFromFile("standard_user"));
        writeText(passwordTxt,PropertyReader.getPropertyFromFile("standard_user_password"));
        click(loginBtn);
        return this;
    }
    public LoginPage loginAsLockedOutUser(){
        writeText(usernameTxt,PropertyReader.getPropertyFromFile("locked_out_user"));
        writeText(passwordTxt,PropertyReader.getPropertyFromFile("locked_out_user_password"));
        click(loginBtn);
        return this;
    }
    public LoginPage loginAsProblemUser(){
        writeText(usernameTxt,PropertyReader.getPropertyFromFile("problem_user"));
        writeText(passwordTxt,PropertyReader.getPropertyFromFile("problem_user_password"));
        click(loginBtn);
        return this;
    }
    public LoginPage loginAsPerformanceGlitchUser(){
        writeText(usernameTxt,PropertyReader.getPropertyFromFile("performance_glitch_user"));
        writeText(passwordTxt,PropertyReader.getPropertyFromFile("performance_glitch_user_password"));
        click(loginBtn);
        return this;
    }

    public String getErrorTxt() {
        return readText(ErrorLbl);
    }
    public LoginPage loginWithEmptyUsername(){
        writeText(usernameTxt,"");
        writeText(passwordTxt,PropertyReader.getPropertyFromFile("standard_user_password"));
        click(loginBtn);
        return this;
    }

    public LoginPage loginWithEmptyPassword() {
        writeText(usernameTxt,PropertyReader.getPropertyFromFile("standard_user"));
        writeText(passwordTxt,"");
        click(loginBtn);
        return this;
    }

    public LoginPage loginWithInvalidPassword() {
        writeText(usernameTxt,PropertyReader.getPropertyFromFile("standard_user"));
        writeText(passwordTxt,"invalid");
        click(loginBtn);
        return this;
    }
}
