package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductsScreen {
    public final static By heading = By.xpath("//div[contains(@Class, 'header_secondary_container')]/span[contains(@Class, 'title')]");

    private final WebDriver driver;

    public ProductsScreen(WebDriver driver) {
        this.driver = driver;
    }

    public String getHeaderText() {
        return new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(heading)).getText();
    }

}
