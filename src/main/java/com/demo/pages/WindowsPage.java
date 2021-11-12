package com.demo.pages;

import com.demo.base.TestBase;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class WindowsPage extends TestBase {
    @FindBy(id = "search")
    WebElement searchBtn;

    @FindBy(id = "cli_shellHeaderSearchInput")
    WebElement searchTextBox;

    public WindowsPage() {
        PageFactory.initElements(driver, this);
    }

    public void searchOnWindowsPage (String keyword) {
        searchBtn.click();
        searchTextBox.sendKeys(keyword);
        searchTextBox.sendKeys((Keys.ENTER));
    }

}
