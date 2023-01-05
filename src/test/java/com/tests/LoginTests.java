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

public class LoginTests {
    WebDriver driver;

    String userName = new String("standard_user");
    String passWord = new String ("secret_sauce");

    @BeforeTest
    public void setupTest() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
    }

    @Test
    public void goodLogin() {
        LoginPage lpage = new LoginPage(driver);
        lpage.logIntoSite(userName, passWord);
        ProductsScreen ppage = new ProductsScreen(driver);
        Assert.assertEquals("PRODUCTS", ppage.getHeaderText());
    }

    @AfterTest
    public void closeDriver() {
        driver.quit();
    }

}
