package org.framework.pages;

import org.framework.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckoutOverviewPage extends BasePage {

    By total = By.xpath("//div[@class='summary_subtotal_label']");
    By finishBtn = By.id("finish");


    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
    }

    public String getCheckoutOverviewPageLink(){
        return driver.getCurrentUrl();
    }

    public Double getTotalPriceOfSelectedItems() {
        int size = driver.findElements(By.xpath("//div[@class='inventory_item_price']")).size();
        List<Double> tot = new ArrayList<>();
      for(int i=1;i<=size;i++) {
           tot.add(Double.parseDouble(readText(By.xpath("(//div[@class='inventory_item_price'])[" + i + "]")).replace("$","")));
      }
       return tot.stream()
               .mapToDouble(Double::doubleValue)
               .sum();
    }
    public Double getTotalPrice(){
        String tot = readText(total).replace("Item total: $","");
       return Double.parseDouble(tot);
    }

    public Map<String, String> getSelectedItems() {
        int size = driver.findElements(By.xpath("//div[@class='inventory_item_name']")).size();
        Map<String,String> totItems =  new HashMap<>();
        for(int i=1;i<=size;i++) {
            totItems.put(readText(By.xpath("(//div[@class='inventory_item_name'])[" + i + "]")),readText(By.xpath("(//div[@class='inventory_item_price'])[" + i + "]")));
        }
        return totItems;
    }

    public CheckoutOverviewPage finish() {
        click(finishBtn);
        return this;
    }
}
