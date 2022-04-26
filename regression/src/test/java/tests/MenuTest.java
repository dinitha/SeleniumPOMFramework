package tests;

import com.framework.core.BaseTest;
import com.framework.core.PropertyReader;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.InventoryPage;
import pages.LoginPage;
import pages.MenuPage;
import utils.ExtentTestManager;

public class MenuTest extends BaseTest {

    @Test
    public void selectAllItemsTest(){
        ExtentTestManager.startTest("select All Items test","select All Items");

        LoginPage loginPage = new LoginPage(getDriver());
        MenuPage menuPage = new MenuPage(getDriver());
        InventoryPage inventoryPage = new InventoryPage(getDriver());
        loginPage
                .goToLoginPage()
                .loginAsStandardUser();

       Assert.assertEquals(inventoryPage.getProductPageLink(), PropertyReader.getPropertyFromFile("inventory_link"));
        menuPage
                .getMenu()
                .selectAllItems();
        Assert.assertEquals(inventoryPage.getProductPageLink(), PropertyReader.getPropertyFromFile("inventory_link"));
    }
    @Test
    public void selectAboutTest(){
        ExtentTestManager.startTest("select about test","select about");

        LoginPage loginPage = new LoginPage(getDriver());
        MenuPage menuPage = new MenuPage(getDriver());
        InventoryPage inventoryPage = new InventoryPage(getDriver());
        loginPage
                .goToLoginPage()
                .loginAsStandardUser();

        Assert.assertEquals(inventoryPage.getProductPageLink(), PropertyReader.getPropertyFromFile("inventory_link"));
        menuPage
                .getMenu()
                .selectAbout();
        Assert.assertEquals(menuPage.getCurrentLink(), PropertyReader.getPropertyFromFile("about"));
    }
    @Test
    public void selectLogoutTest(){
        ExtentTestManager.startTest("select logout test","select logout");

        LoginPage loginPage = new LoginPage(getDriver());
        MenuPage menuPage = new MenuPage(getDriver());
        InventoryPage inventoryPage = new InventoryPage(getDriver());
        loginPage
                .goToLoginPage()
                .loginAsStandardUser();

        Assert.assertEquals(inventoryPage.getProductPageLink(), PropertyReader.getPropertyFromFile("inventory_link"));
        menuPage
                .getMenu()
                .selectLogout();
        Assert.assertEquals(menuPage.getCurrentLink(), PropertyReader.getPropertyFromFile("url").concat("/"));
    }

    @Test
    public void resetAppTest(){
        ExtentTestManager.startTest("select reset app test","reset app");

        LoginPage loginPage = new LoginPage(getDriver());
        MenuPage menuPage = new MenuPage(getDriver());
        InventoryPage inventoryPage = new InventoryPage(getDriver());
        CartPage cartPage = new CartPage(getDriver());
        loginPage
                .goToLoginPage()
                .loginAsStandardUser();

        Assert.assertEquals(inventoryPage.getProductPageLink(), PropertyReader.getPropertyFromFile("inventory_link"));
        inventoryPage
                .selectProductByApperingOrder("1");
        Assert.assertTrue(cartPage.getNoOfItems()==1);
        menuPage
                .getMenu()
                .selectResetApp()
                .closeMenu();
       Assert.assertTrue(cartPage.getEmptyShoppingCart().equals(""));

    }

}
