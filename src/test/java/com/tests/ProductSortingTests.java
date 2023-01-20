package com.tests;

import com.pages.ProductsScreen;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductSortingTests extends BaseTests {
    String userName = new String("standard_user");
    String passWord = new String ("secret_sauce");

    ProductsScreen productsPage;

    // Login
    @Test
    public void goodLoginTest() {
        lpage.setUserName(userName);
        lpage.setPassword(passWord);
        productsPage = lpage.clickLoginButton();
        Assert.assertEquals("PRODUCTS", productsPage.getHeaderText());
    }
    // This method tests if the low to high alphabetical sorting is correct
    @Test(dependsOnMethods = "goodLoginTest")
    public void sortNamesLowToHighTest() {
        productsPage.selectSort("az");
        Assert.assertTrue(productsPage.testNames("az"));
    }

    // This method tests if the high to low alphabetical sorting is correct
    @Test(dependsOnMethods = "goodLoginTest")
    public void sortNamesHighToLowTest() {
        productsPage.selectSort("za");
        Assert.assertTrue(productsPage.testNames("za"));
    }

    // This method tests if the low to high price sorting is correct
    @Test(dependsOnMethods = "goodLoginTest")
    public void sortPriceLowToHighTest() {
        productsPage.selectSort("lohi");
        Assert.assertTrue(productsPage.testPrices("lohi"));
    }

    // This method tests if the high to low price sorting is correct
    @Test(dependsOnMethods = "goodLoginTest")
    public void sortPriceHighToLowTest() {
        productsPage.selectSort("hilo");
        Assert.assertTrue(productsPage.testPrices("hilo"));
    }

}
