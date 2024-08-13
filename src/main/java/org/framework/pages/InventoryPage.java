package org.framework.pages;

import org.framework.base.BasePage;
import org.framework.utils.logs.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.*;

import static com.google.common.collect.Iterables.isEmpty;


public class InventoryPage extends BasePage {

    By cartLink = By.cssSelector("a.shopping_cart_link");
    By checkoutBtn = By.id("checkout");
    By img = By.cssSelector("img.inventory_item_img");
    By sortDropdown = By.xpath("//select[@data-test='product_sort_container']");

    public InventoryPage(WebDriver driver) {
        super(driver);

    }

    public String getProductPageLink(){
        return driver.getCurrentUrl();
    }
    public InventoryPage selectProductByApperingOrder(String itemNo){
        click(By.xpath("(//button[text()='Add to cart'])[ '"+itemNo+"']"));
        return this;
    }
    public InventoryPage navigateToCart(){
        click(cartLink);
        return this;
    }
    public String getSpecificProductNameByAppearingOrder(String itemNo){
        return readText(By.xpath("(//div[@class='inventory_item_name '])["+itemNo+"]"));

    }
    public String getSpecificProductPriceByAppearingOrder(String itemNo){
      return readText(By.xpath("(//div[@class='inventory_item_price'])["+itemNo+"]"));

    }

    public InventoryPage checkout() {
        click(checkoutBtn);
        return this;
    }

    public Boolean verifyUniqueImg() {
        List<WebElement> imagesList = driver.findElements(img);
        Set<String> set = new HashSet<String>();
        for (WebElement e:imagesList) {
            set.add(e.getAttribute("src"));
        }
        if(set.size()<imagesList.size()){
            Log.error("images are not unique");
            return false;
        }
        else {
            return true;
        }

    }

    public Boolean isSortedByNameAToZ(List<String> listOfStrings){
        if (isEmpty(listOfStrings) || listOfStrings.size() == 1) {
            return true;
        }

        Iterator<String> iter = listOfStrings.iterator();
        String current, previous = iter.next();
        while (iter.hasNext()) {
            current = iter.next();
            if (previous.compareTo(current) > 0) {
                return false;
            }
            previous = current;
        }
        return true;
    }

    public List<String> getAvailableProductsInPage() {
        List<String> items = new ArrayList<>();
       driver.findElements(By.xpath("//div[@class='inventory_item_name']")).stream().forEach(product -> items.add(product.getText()));
       return items;
    }
    public List<String> getAvailablePrisesInPage() {
        List<String> items = new ArrayList<>();
        driver.findElements(By.xpath("//div[@class='inventory_item_price']")).stream().forEach(product -> items.add(product.getText().replace("$","")));
        return items;
    }

    public boolean isSortedByNameZtoA(List<String> listOfStrings) {
        if (isEmpty(listOfStrings) || listOfStrings.size() == 1) {
            return true;
        }

        Iterator<String> iter = listOfStrings.iterator();
        String current, previous = iter.next();
        while (iter.hasNext()) {
            current = iter.next();
            if (previous.compareTo(current) < 0) {
                return false;
            }
            previous = current;
        }
        return true;
    }

    public boolean isSortedByPricesLowToHigh(List<String> list){
        list.stream().forEach(a->Double.parseDouble(a));
        for (int i = 0; i < list.size() - 1; ++i) {
            if (list.get(i).compareTo(list.get(i+1)) >= 0)
                return true;
        }
        return false;

    }

    public boolean isSortedByPricesHighToLow(List<String> list){
        list.stream().forEach(a->Double.parseDouble(a));
        for (int i = 0; i < list.size() - 1; ++i) {
            if (list.get(i).compareTo(list.get(i+1)) <= 0)
                return true;
        }
        return false;

    }

    public InventoryPage sortProductNameByAToZ() {
        Select dropdown = new Select(driver.findElement(sortDropdown));
        dropdown.selectByVisibleText("Name (A to Z)");
      return this;
    }
    public InventoryPage sortProductNameByZToA() {
        Select dropdown = new Select(driver.findElement(sortDropdown));
        dropdown.selectByVisibleText("Name (Z to A)");
        return this;
    }
    public InventoryPage sortProductPriceLow() {
        Select dropdown = new Select(driver.findElement(sortDropdown));
        dropdown.selectByVisibleText("Price (low to high)");
        return this;
    }
    public InventoryPage sortProductPriceHigh() {
        Select dropdown = new Select(driver.findElement(sortDropdown));
        dropdown.selectByVisibleText("Price (high to low)");
        return this;
    }

    public InventoryPage removeProductByAppeatingOrder(String itemNo) {
        click(By.xpath("(//button[text()='Remove'])[ '"+itemNo+"']"));
        return this;
    }
}
