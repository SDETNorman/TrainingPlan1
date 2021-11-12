package com.demo.testcases;

import com.demo.base.TestBase;
import com.demo.pages.HomePage;
import com.demo.pages.SearchPage;
import com.demo.pages.WindowsPage;
import com.demo.util.TestUtil;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.Assert;

public class TC02ShoppingCart extends TestBase {

    HomePage homePage;
    WindowsPage windowsPage;
    SearchPage searchPage;
    TestUtil testUtil;

    @BeforeMethod
    public void setUp() {
        initialization();
        testUtil = new TestUtil();
        homePage = new HomePage();
        searchPage = new SearchPage();
        windowsPage = new WindowsPage();
    }

    @Test(priority = 1)
    public void shoppingCart() throws InterruptedException {
        homePage.clickOnWindowsTab();
        windowsPage.searchOnWindowsPage("Halo");
        Assert.assertEquals(searchPage.addFirstItemShoppingCart(),"1");
        Assert.assertEquals(searchPage.addSecondItemShoppingCart(),"2");
        Assert.assertEquals(searchPage.removeOneItemFromShoppingCart(),"1");
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

}
