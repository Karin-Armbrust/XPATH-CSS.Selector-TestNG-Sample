package com.tests;

import com.data.LoginData;
import com.pages.LoginPage;
import com.pages.ProductsScreen;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import static org.apache.commons.lang3.StringUtils.substring;

// NOTE: This test Suite needs to be run from testngLogin.xml
@Test
public class LoginTests extends BaseTests{
    //WebDriver driver;

    @Test
    @Parameters({"URL", "BrowserType"})
    public void setBrowserURL(String url, String browserType) {
        // Run each test on the different browsers
        if (browserType.equalsIgnoreCase("Chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }
        else if (browserType.equalsIgnoreCase("Internet Explorer"))
        {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }
        else if (browserType.equalsIgnoreCase("Firefox"))
        {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }

        driver.get(url);
    }


    // Using a set of data run login tests
    // One good login - Log into the site and go directly to the Products Screen
    @Test(dataProvider="login-provider", dataProviderClass = LoginData.class, dependsOnMethods = "setBrowserURL")
    public void loginTests(String userName, String passWord, Boolean expectedPassed ) {
        LoginPage lpage = new LoginPage(driver);
        lpage.setUserName(userName);
        lpage.setPassword(passWord);
        ProductsScreen ppage = lpage.clickLoginButton();

        if (expectedPassed){
            Assert.assertEquals("PRODUCTS", ppage.getHeaderText());
            ppage.logout();
        } else { // if expected to fail
            Assert.assertEquals("Epic sadface",
                    substring(lpage.getErrorMsg(),0,12));

        }
    }
}
