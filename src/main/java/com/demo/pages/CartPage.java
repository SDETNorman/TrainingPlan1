package com.demo.pages;

import com.demo.base.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage extends TestBase {
    private WebDriverWait wait;

    @FindBy(xpath = "(//button[contains(text(), 'Quitar')])[1]")
    WebElement removeItem;

    public CartPage() {
        wait = new WebDriverWait(driver, 15, 50);
        PageFactory.initElements(driver, this);
    }



}
