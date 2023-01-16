package com.tests;

import com.data.LoginData;
import com.pages.LoginPage;
import com.pages.ProductsScreen;
import io.github.bonigarcia.wdm.WebDriverManager;
import junit.framework.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import static org.apache.commons.lang3.StringUtils.substring;

@Test
public class LoginTests {
    WebDriver driver;

    @BeforeMethod
    public void setupTest() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
    }


    // Using a set of data run login tests
    // One good login - Log into the site and go directly to the Products Screen
    @Test(dataProvider="login-provider", dataProviderClass = LoginData.class)
    public void loginTests(String userName, String passWord, Boolean expectedPassed ) {
        LoginPage lpage = new LoginPage(driver);
        lpage.logIntoSite(userName, passWord);
        ProductsScreen ppage = new ProductsScreen(driver);

        if (expectedPassed){
            Assert.assertEquals("PRODUCTS", ppage.getHeaderText());
            ppage.logout();
        } else { // if expected to fail
            Assert.assertEquals("Epic sadface",
                    substring(lpage.getErrorMsg(),0,12));

        }
    }

    @AfterMethod
    public void closeDriver() {
        driver.quit();
    }


}
