package com.tests;

import com.pages.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseTests {
    WebDriver driver;

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

    }

    // Close up the test
    @AfterTest
    public void closeDriver() {
        driver.quit();
    }
}
