package tests;

import com.framework.core.BaseTest;
import com.framework.core.PropertyReader;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.InventoryPage;
import pages.LoginPage;
import utils.ExtentTestManager;

import java.util.List;

public class SortingTest extends BaseTest {

    @Test
    public void sortingAToZTest(){
        ExtentTestManager.startTest("sorting A-Z Test","verify list items are in alphabetical order");

        LoginPage loginPage = new LoginPage(getDriver());
        InventoryPage inventoryPage = new InventoryPage(getDriver());
        loginPage
                .goToLoginPage()
                .loginAsStandardUser();
        inventoryPage.sortProductNameByAToZ();
       Assert.assertEquals(inventoryPage.getProductPageLink(), PropertyReader.getPropertyFromFile("inventory_link"));
        List<String> items = inventoryPage.getAvailableProductsInPage();
       Assert.assertTrue(inventoryPage.isSortedByNameAToZ(items));


    }
    @Test
    public void sortingZToATest(){
        ExtentTestManager.startTest("sorting Z-A Test","verify list items are in alphabetical reverse order");

        LoginPage loginPage = new LoginPage(getDriver());
        InventoryPage inventoryPage = new InventoryPage(getDriver());
        loginPage
                .goToLoginPage()
                .loginAsStandardUser();

        inventoryPage.sortProductNameByZToA();
        Assert.assertEquals(inventoryPage.getProductPageLink(), PropertyReader.getPropertyFromFile("inventory_link"));
        List<String> items = inventoryPage.getAvailableProductsInPage();
        Assert.assertTrue(inventoryPage.isSortedByNameZtoA(items));

    }
    @Test
    public void sortingPriceLowToHighTest(){
        ExtentTestManager.startTest("sorting price low to high Test","verify list items are in low to high order");

        LoginPage loginPage = new LoginPage(getDriver());
        InventoryPage inventoryPage = new InventoryPage(getDriver());
        loginPage
                .goToLoginPage()
                .loginAsStandardUser();

        inventoryPage.sortProductPriceLow();
        Assert.assertEquals(inventoryPage.getProductPageLink(), PropertyReader.getPropertyFromFile("inventory_link"));
        List<String> items = inventoryPage.getAvailablePrisesInPage();
        Assert.assertTrue(inventoryPage.isSortedByPricesLowToHigh(items));

    }
    @Test
    public void sortingHighToLowTest(){
        ExtentTestManager.startTest("sorting price high to Test","verify list items are in high to low order");
        LoginPage loginPage = new LoginPage(getDriver());
        InventoryPage inventoryPage = new InventoryPage(getDriver());
        loginPage
                .goToLoginPage()
                .loginAsStandardUser();

        inventoryPage.sortProductPriceHigh();
        Assert.assertEquals(inventoryPage.getProductPageLink(), PropertyReader.getPropertyFromFile("inventory_link"));
        List<String> items = inventoryPage.getAvailablePrisesInPage();
        Assert.assertTrue(inventoryPage.isSortedByPricesHighToLow(items));

    }


}
