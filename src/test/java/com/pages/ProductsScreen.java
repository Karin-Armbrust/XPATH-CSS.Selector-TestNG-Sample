package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ProductsScreen {
    public final static By heading =
            By.xpath("//div[contains(@Class, 'header_secondary_container')]/span[contains(@Class, 'title')]");

    public final static By productPrices =
            By.xpath("//div[@class='inventory_list']//div[@class='pricebar']//div[@class='inventory_item_price']");

    public final static By sortDropdown =
            By.xpath("//select[@class='product_sort_container']");

    private final WebDriver driver;

    public ProductsScreen(WebDriver driver) {
        this.driver = driver;
    }

    public String getHeaderText() {
        return new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(heading)).getText();
    }

    public boolean testPricesLowToHigh() {
        List<WebElement> priceList= new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(productPrices));
        boolean listSorted = true;
        float currentPrice = new Float(0.0);
        for (WebElement price:priceList) {
            //String currentPrice = new String(price.getText());
            //int lengthPrice = Integer.parseInt(currentPrice)
            float convertedPrice = Float.parseFloat(price.getText().substring(1));
            if (convertedPrice < currentPrice) {
                listSorted = false;
            }
            System.out.println(convertedPrice);
        }
        return listSorted;
    }

    public boolean testPricesHighToLow() {
        List<WebElement> priceList= new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(productPrices));
        boolean listSorted = true;
        float currentPrice = new Float(0.0);
        for (WebElement price:priceList) {
            //String currentPrice = new String(price.getText());
            //int lengthPrice = Integer.parseInt(currentPrice)
            float convertedPrice = Float.parseFloat(price.getText().substring(1));
            if (currentPrice == 0.0) {
                currentPrice = convertedPrice;
            }
            if (convertedPrice > currentPrice) {
                listSorted = false;
            }
            System.out.println(convertedPrice);
            currentPrice = convertedPrice;
        }
        return listSorted;
    }

    public void selectSortLowToHigh() {
        WebElement dropdownList = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(sortDropdown));
        final Select selectOption = new Select(dropdownList);
        selectOption.selectByValue("lohi");
    }

    public void selectSortHighToLow() {
        WebElement dropdownList = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(sortDropdown));
        final Select selectOption = new Select(dropdownList);
        selectOption.selectByValue("hilo");
    }

}
