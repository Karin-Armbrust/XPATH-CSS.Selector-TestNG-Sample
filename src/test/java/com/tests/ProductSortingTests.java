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

    String userName = new String("standard_user");
    String passWord = new String ("secret_sauce");

    ProductsScreen productsPage;
    LoginPage lpage;

    // Set up the test suite
    @BeforeTest
    public void setupTest() {
        // Set up the Chrome driver
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        // Go to website login and test we hit the Product Page
        driver.get("https://www.saucedemo.com/");
        lpage = new LoginPage(driver);
        lpage.logIntoSite(userName, passWord);
        productsPage = new ProductsScreen(driver);
        Assert.assertEquals("PRODUCTS", productsPage.getHeaderText());
    }

    // This method tests if the low to high alphabetical sorting is correct
    @Test
    public void sortNamesLowToHighTest() {
        productsPage.selectSort("az");
        Assert.assertTrue(productsPage.testNames("az"));
    }

    // This method tests if the high to low alphabetical sorting is correct
    @Test
    public void sortNamesHighToLowTest() {
        productsPage.selectSort("za");
        Assert.assertTrue(productsPage.testNames("za"));
    }

    // This method tests if the low to high price sorting is correct
    @Test
    public void sortPriceLowToHighTest() {
        productsPage.selectSort("lohi");
        Assert.assertTrue(productsPage.testPrices("lohi"));
    }

    // This method tests if the high to low price sorting is correct
    @Test
    public void sortPriceHighToLowTest() {
        productsPage.selectSort("hilo");
        Assert.assertTrue(productsPage.testPrices("hilo"));
    }

    // Close up the test
    @AfterTest
    public void closeDriver() {
        driver.quit();
    }
}
