package com.tests;

import com.pages.LoginPage;
import com.pages.ProductsScreen;
import io.github.bonigarcia.wdm.WebDriverManager;
import junit.framework.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ProductSortingTests {
    WebDriver driver;
    ProductsScreen productsPage = new ProductsScreen(driver);

    String userName = new String("standard_user");
    String passWord = new String ("secret_sauce");

    @BeforeTest
    public void setupTest() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        LoginPage lpage = new LoginPage(driver);
        lpage.logIntoSite(userName, passWord);
        Assert.assertEquals("PRODUCTS", productsPage.getHeaderText());

    }

    @Test
    public void sortPriceLowToHigh() {
        productsPage.selectSortLowToHigh();
        Assert.assertTrue(productsPage.testPricesLowToHigh());
    }

    @Test
    public void sortPriceHighToLow() {
        productsPage.selectSortHighToLow();
        Assert.assertTrue(productsPage.testPricesHighToLow());
    }

    @AfterTest
    public void closeDriver() {
        driver.quit();
    }
}
