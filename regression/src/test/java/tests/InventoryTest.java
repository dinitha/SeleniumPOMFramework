package tests;

import com.framework.core.BaseTest;
import com.framework.core.PropertyReader;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;
import utils.ExtentTestManager;

import java.util.HashMap;
import java.util.Map;

public class InventoryTest extends BaseTest {

    @Test
    public void standardUserCheckoutTest(){
        ExtentTestManager.startTest("standardUserCheckoutTest","verify checkout process");

        LoginPage loginPage = new LoginPage(getDriver());
        InventoryPage inventoryPage = new InventoryPage(getDriver());
        CartPage cartPage = new CartPage(getDriver());
        CheckoutPage checkoutPage = new CheckoutPage(getDriver());
        CheckoutOverviewPage checkoutOverviewPage = new CheckoutOverviewPage(getDriver());
        CheckoutCompletePage checkoutCompletePage = new CheckoutCompletePage(getDriver());
            loginPage
                .goToLoginPage()
                .loginAsStandardUser();

        Assert.assertEquals(inventoryPage.getProductPageLink(), PropertyReader.getPropertyFromFile("inventory_link"));
        Map<String, String> itemsInventory = new HashMap<String, String>();
        itemsInventory.put(inventoryPage.getSpecificProductNameByAppearingOrder("1"),inventoryPage.getSpecificProductPriceByAppearingOrder("1"));
        itemsInventory.put(inventoryPage.getSpecificProductNameByAppearingOrder("2"),inventoryPage.getSpecificProductPriceByAppearingOrder("2"));
        inventoryPage
               .selectProductByApperingOrder("1")
               .selectProductByApperingOrder("2")
               .navigateToCart();

       Assert.assertEquals(cartPage.getCartPageLink(),PropertyReader.getPropertyFromFile("cart_link"));
        cartPage
                .continueShopping();
        itemsInventory.put(inventoryPage.getSpecificProductNameByAppearingOrder("3"),inventoryPage.getSpecificProductPriceByAppearingOrder("3"));
        itemsInventory.put(inventoryPage.getSpecificProductNameByAppearingOrder("4"),inventoryPage.getSpecificProductPriceByAppearingOrder("4"));
        inventoryPage
                .selectProductByApperingOrder("3")
                .selectProductByApperingOrder("4")
                .navigateToCart()
                .checkout();
        Assert.assertEquals(checkoutPage.getCheckoutPageLink(),PropertyReader.getPropertyFromFile("checkout_link"));
        checkoutPage
                .enterCheckoutDetails("test","test","123")
                .continueCheckout();
        Assert.assertEquals(checkoutOverviewPage.getCheckoutOverviewPageLink(),PropertyReader.getPropertyFromFile("checkout_overview_link"));

        Assert.assertTrue(itemsInventory.equals(checkoutOverviewPage.getSelectedItems()));
        Assert.assertEquals(checkoutOverviewPage.getTotalPriceOfSelectedItems(),checkoutOverviewPage.getTotalPrice());

        checkoutOverviewPage
                            .finish();
        Assert.assertEquals(checkoutCompletePage.getCheckoutCompletePageLink(),PropertyReader.getPropertyFromFile("checkout_complete"));
        checkoutCompletePage.NavigateToHome();
        Assert.assertEquals(inventoryPage.getProductPageLink(), PropertyReader.getPropertyFromFile("inventory_link"));
    }
    @Test
    public void removeFromCartTest(){
        ExtentTestManager.startTest("Remove from cart test","login as standard user and try removing added cart item");

        LoginPage loginPage = new LoginPage(getDriver());
        InventoryPage inventoryPage = new InventoryPage(getDriver());
        CartPage cartPage = new CartPage(getDriver());
        loginPage
                .goToLoginPage()
                .loginAsStandardUser();

        Assert.assertEquals(inventoryPage.getProductPageLink(), PropertyReader.getPropertyFromFile("inventory_link"));
       inventoryPage
               .selectProductByApperingOrder("1");
       inventoryPage
               .removeProductByAppeatingOrder("1");
        Assert.assertTrue(cartPage.getEmptyShoppingCart().equals(""));
    }

}
