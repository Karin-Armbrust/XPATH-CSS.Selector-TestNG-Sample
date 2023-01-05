package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    public final static By userName = By.xpath("//input[contains(@data-test, 'username')]");
    public final static By passWord = By.xpath("//input[contains(@data-test, 'password')]");
    public final static By loginButton = By.xpath("//input[contains(@class, 'submit-button')]");

    private final WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setUserName(String uname) {
        WebElement username = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(userName));
        username.sendKeys(uname);
    }

    public void setPassword(String pword) {
        WebElement password = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(passWord));
        password.sendKeys(pword);
    }

    public void clickLoginButton() {
        WebElement loginbutton = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(loginButton));
        loginbutton.click();
    }

    public void logIntoSite(String uname, String pword) {
        setUserName(uname);
        setPassword(pword);
        clickLoginButton();
    }
}
