package com.demo.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.demo.base.TestBase;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends TestBase {
    private WebDriverWait wait;

    @FindBy(xpath = "//a[@id='shellmenu_2']")
    WebElement windowsTab;

    public HomePage() {
        wait = new WebDriverWait(driver, 15, 50);
        PageFactory.initElements(driver, this);
    }

    public String VerifyHomePageTitle() {
        return driver.getTitle();
    }

    public void clickOnWindowsTab(){
        wait.until(ExpectedConditions.visibilityOf(windowsTab)).click();
    }


}
