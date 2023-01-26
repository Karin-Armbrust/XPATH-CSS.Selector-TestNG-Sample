package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class LoginPage {
    public final static By userName = By.xpath("//input[contains(@data-test, 'username')]");
    public final static By passWord = By.xpath("//input[contains(@data-test, 'password')]");
    public final static By loginButton = By.xpath("//input[contains(@class, 'submit-button')]");
    public final static By errorMsg = By.xpath("//div[@class='error-message-container error']//h3[@data-test='error']");

    private final WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Enter the Username
    public void setUserName(String uname) {
        WebElement username = getElement(userName);
        username.sendKeys(uname);
    }

    // Enter the Password
    public void setPassword(String pword) {
        WebElement password = getElement(passWord);
        password.sendKeys(pword);
    }

    // Click the login button
    public ProductsScreen clickLoginButton() {
        WebElement loginbutton = getElement(loginButton);
        loginbutton.click();
        return new ProductsScreen(driver);
    }

    // This method gets the title/header of the Products page
    public String getErrorMsg() {
        return getElement(errorMsg).getText();
    }

    // Method to get the WebElement usina a Wait
    public WebElement getElement(By locator) {
        WebElement newElement = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(locator));
        return newElement;
    }
}
