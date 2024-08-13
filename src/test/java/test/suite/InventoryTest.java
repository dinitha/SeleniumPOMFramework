package test.suite;

import org.framework.base.BaseTest;
import org.framework.pages.*;
import org.framework.utils.helpers.RandomGenerators;
import org.framework.utils.propertyreader.PropertyReader;
import org.testng.Assert;
import org.testng.annotations.Test;


import java.util.*;

import static org.framework.utils.propertyreader.PropertyReader.getProperty;

public class InventoryTest extends BaseTest {

    @Test
    public void standardUserCheckoutTest(){


        LoginPage loginPage = new LoginPage(getDriver());
        InventoryPage inventoryPage = new InventoryPage(getDriver());
        CartPage cartPage = new CartPage(getDriver());
        CheckoutPage checkoutPage = new CheckoutPage(getDriver());
        CheckoutOverviewPage checkoutOverviewPage = new CheckoutOverviewPage(getDriver());
        CheckoutCompletePage checkoutCompletePage = new CheckoutCompletePage(getDriver());
            loginPage
                .goToLoginPage()
                .loginAsStandardUser();

        Assert.assertEquals(inventoryPage.getProductPageLink(), PropertyReader.getInstance().getProperty("inventory_link"));
        Map<String, String> itemsInventory = new HashMap<String, String>();


        List<Integer> itemIndex = new ArrayList<>(RandomGenerators.getRandomUniqueIntegers(6,1,3));
      //  itemsInventory.put(inventoryPage.getSpecificProductNameByAppearingOrder(itemIndex.get(0)),inventoryPage.getSpecificProductPriceByAppearingOrder("1"));
       // itemsInventory.put(inventoryPage.getSpecificProductNameByAppearingOrder("2"),inventoryPage.getSpecificProductPriceByAppearingOrder("2"));
/*        inventoryPage
               .selectProductByApperingOrder(itemIndex.get(0).toString())
               .selectProductByApperingOrder(itemIndex.get(1).toString())
                .getSpecificProductNameByAppearingOrder(itemIndex.get(2).toString())*/



    /*   Assert.assertEquals(cartPage.getCartPageLink(), getProperty("cart_link"));
        cartPage
                .continueShopping();*/
        itemsInventory.put(inventoryPage.getSpecificProductNameByAppearingOrder(String.valueOf(itemIndex.get(0))),inventoryPage.getSpecificProductPriceByAppearingOrder(String.valueOf(itemIndex.get(0))));
        itemsInventory.put(inventoryPage.getSpecificProductNameByAppearingOrder(String.valueOf(itemIndex.get(1))),inventoryPage.getSpecificProductPriceByAppearingOrder(String.valueOf(itemIndex.get(1))));
        itemsInventory.put(inventoryPage.getSpecificProductNameByAppearingOrder(String.valueOf(itemIndex.get(2))),inventoryPage.getSpecificProductPriceByAppearingOrder(String.valueOf(itemIndex.get(2))));

        inventoryPage
                .selectProductByApperingOrder(itemIndex.get(0).toString())
                .selectProductByApperingOrder(itemIndex.get(1).toString())
                .selectProductByApperingOrder(itemIndex.get(2).toString())
                .navigateToCart()
                .checkout();
        Assert.assertEquals(checkoutPage.getCheckoutPageLink(), getProperty("checkout_link"));
        checkoutPage
                .enterCheckoutDetails(RandomGenerators.generateRandomString(5),RandomGenerators.generateRandomString(7), String.valueOf(RandomGenerators.getRandomInteger(20000,1000)))
                .continueCheckout();
        Assert.assertEquals(checkoutOverviewPage.getCheckoutOverviewPageLink(), getProperty("checkout_overview_link"));

        Assert.assertTrue(itemsInventory.equals(checkoutOverviewPage.getSelectedItems()));
        Assert.assertEquals(checkoutOverviewPage.getTotalPriceOfSelectedItems(),checkoutOverviewPage.getTotalPrice());

        checkoutOverviewPage
                            .finish();
        Assert.assertEquals(checkoutCompletePage.getThankYouMessage(),getProperty("thank_you_message"));
        Assert.assertEquals(checkoutCompletePage.getCheckoutCompletePageLink(), getProperty("checkout_complete"));
        checkoutCompletePage.NavigateToHome();
        Assert.assertEquals(inventoryPage.getProductPageLink(), getProperty("inventory_link"));
    }


}
