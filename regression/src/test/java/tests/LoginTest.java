package tests;

import com.framework.core.BaseTest;
import com.framework.core.PropertyReader;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.InventoryPage;
import utils.ExtentTestManager;

public class LoginTest extends BaseTest {

    @Test
    public void standardUserTest(){
        ExtentTestManager.startTest("Login as standard User test","login as standard user");

        LoginPage loginPage = new LoginPage(getDriver());
        InventoryPage inventoryPage = new InventoryPage(getDriver());
        loginPage
                .goToLoginPage()
                .loginAsStandardUser();

       Assert.assertEquals(inventoryPage.getProductPageLink(), PropertyReader.getPropertyFromFile("inventory_link"));

    }

    @Test
    public void lockedOutUserTest(){
        ExtentTestManager.startTest("Login as lockedout user test","login as locked out user");

        LoginPage loginPage = new LoginPage(getDriver());
        loginPage
                .goToLoginPage()
                .loginAsLockedOutUser();
        Assert.assertEquals(loginPage.getErrorTxt(),"Epic sadface: Sorry, this user has been locked out.");
    }
    @Test
    public void problemUserTest(){
        ExtentTestManager.startTest("Login as problem user test","login as problem user");

        LoginPage loginPage = new LoginPage(getDriver());
        InventoryPage inventoryPage = new InventoryPage(getDriver());
        loginPage
                .goToLoginPage()
                .loginAsProblemUser();
        Assert.assertTrue(inventoryPage.verifyUniqueImg());

    }

    @Test
    public void performanceUserTest(){
        ExtentTestManager.startTest("Login as lockeout user test","login as performance user");

        LoginPage loginPage = new LoginPage(getDriver());
        loginPage
                .goToLoginPage();
        long start = System.currentTimeMillis();
        loginPage
                .loginAsPerformanceGlitchUser();
        long finish = System.currentTimeMillis();
        long totalTime = finish - start;
        Assert.assertTrue(totalTime<1000);
    }
    @Test
    public void emptyUsernameTest(){
        ExtentTestManager.startTest("Login test with empty username","login with no username");

        LoginPage loginPage = new LoginPage(getDriver());
        loginPage
                .goToLoginPage()
                .loginWithEmptyUsername();
        Assert.assertEquals(loginPage.getErrorTxt(),"Epic sadface: Username is required");

    }
    @Test
    public void emptyPasswordTest(){
        ExtentTestManager.startTest("Login test with empty password","login with no password");

        LoginPage loginPage = new LoginPage(getDriver());
        loginPage
                .goToLoginPage()
                .loginWithEmptyPassword();
        Assert.assertEquals(loginPage.getErrorTxt(),"Epic sadface: Password is required");

    }
    @Test
    public void invalidPasswordTest(){
        ExtentTestManager.startTest("Login test with invalid password","login with invalid password");

        LoginPage loginPage = new LoginPage(getDriver());
        loginPage
                .goToLoginPage()
                .loginWithInvalidPassword();
        Assert.assertEquals(loginPage.getErrorTxt(),"Epic sadface: Username and password do not match any user in this service");

    }
}
