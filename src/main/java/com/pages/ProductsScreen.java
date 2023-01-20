package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ProductsScreen {
    public final static By heading =
            By.xpath("//div[contains(@Class, 'header_secondary_container')]/span[contains(@Class, 'title')]");
    public final static By productPrices =
            By.xpath("//div[@class='inventory_list']//div[@class='inventory_item_price']");

    public final static By productAddToCartButtons =
            By.xpath("//div[@class='pricebar']//button[contains(@class,'btn_inventory')]");
    public final static By productNames =
            By.xpath("//div[@class='inventory_list']//div[@class='inventory_item_name']");
    public final static By sortDropdown =
            By.xpath("//select[@class='product_sort_container']");

    public final static By shoppingCartButton =
            By.xpath("//a[@class='shopping_cart_link']//span[@class='shopping_cart_badge']");
    private final WebDriver driver;

    public ProductsScreen(WebDriver driver) {
        this.driver = driver;
    }

    // This method gets the title/header of the Products page
    public String getHeaderText() {
        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(heading)).getText();
    }

    // this method takes in the sort and verifies that it's sorted correctly
    public boolean testPrices(String sortBy) {
        // Get the list of Prices
        List<WebElement> priceList= new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(productPrices));
        // Set up variables
        boolean listSorted = true;
        float currentPrice = new Float(0.0);
        // for each Price
        for (WebElement price:priceList) {
            // Get the price from the string
            float convertedPrice = Float.parseFloat(price.getText().substring(1));
            if (sortBy.equals("lohi")) {   // if sorted low to high
                if (convertedPrice < currentPrice) {
                    listSorted = false;
                }
            } else {  // if sorted high to low
                // if currentPrice is 0 then go onto the next price after
                // setting currentPrice to the convertedPrice
                if (currentPrice != 0.0) {
                    if (convertedPrice > currentPrice) {
                        listSorted = false;
                    }
                }
             }
            currentPrice = convertedPrice;
            System.out.println(convertedPrice);
        }
        return listSorted;

    }

    // This method tests that the Products are sorted alphabetically
    public boolean testNames(String sortBy) {
        // Get all the Product Names
        List<WebElement> nameList= new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(productNames));
        // Set up variables for test
        boolean listSorted = true;
        String currentName = new String("initial");
        // For each Name
        for (WebElement name:nameList) {
            // If it's the first name, go onto the next
            if (currentName.compareTo("initial") == 0 ) {
                System.out.println("Inside initial");

            } else if (sortBy.equals("az")) {  // If sorted low to high
                if (name.getText().compareTo(currentName) < 0) {
                    System.out.println(name.getText() + " - " + currentName + " - " + (name.getText().compareTo(currentName) > 0) );
                    listSorted = false;
                }
            } else {  // if sorted high to low
                if (name.getText().compareTo(currentName) > 0) {
                    System.out.println(name.getText() + " - " + currentName + " - " + (name.getText().compareTo(currentName) > 0) );
                    listSorted = false;
                }
            }
            // set the currentName so I can check the next name
            currentName = name.getText();
            System.out.println(name.getText());
        }
        return listSorted;

    }
    // This method sorts the Products
    public void selectSort(String sortBy) {
        WebElement dropdownList = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(sortDropdown));
        final Select selectOption = new Select(dropdownList);
        selectOption.selectByValue(sortBy);
    }

    // Add all Products to the cart using the Add To Cart Button
    public void addProductsToCart(int numInCart) {
        List<WebElement> addToCartButtons = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(productAddToCartButtons));
        int nextNum = 0;
        for (WebElement button:addToCartButtons) {
            nextNum++;
            if (nextNum <= numInCart){
                button.click();
            }
        }

    }


    public int getItemsInCart() {
        WebElement cart = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(shoppingCartButton));
        return Integer.parseInt(cart.getText());

    }

    public void logout() {
        // TODO
    }
}
