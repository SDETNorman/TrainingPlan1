package com.demo.pages;

import com.demo.base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SearchPage extends TestBase {
    private WebDriverWait wait;

    @FindBy(xpath = "//div[contains(@id,'f2jtswf')]//div[contains(@class,'c-channel')]//h2")
    WebElement appHeaderText;

    @FindBy(xpath = "//div[contains(@id,'f2jtswf')]//div[contains(@class,'c-channel')]//a")
    WebElement appMoreLink;

    @FindBy(xpath = "//img[contains(@alt,'Infinite')]")
    WebElement haloInfinite;

    @FindBy(xpath = "(//button[contains(@class, 'CommonButton')])[2]")
    WebElement cartBtn;

    @FindBy(xpath = "(//a[contains(@class, 'CommonButton')])[2]")
    WebElement openCartBtn;

    @FindBy(xpath = "//span[contains(@class, 'shopping')]")
    WebElement shoppingIcon;

    @FindBy(xpath = " (//div[contains(@class, 'ProductCard')])[9]")
    WebElement secondItem;

    @FindBy(xpath = "(//button[contains(text(), 'Quitar')])[1]")
    WebElement removeItem;


    public SearchPage() {
        wait = new WebDriverWait(driver, 15, 50);
        PageFactory.initElements(driver, this);
    }

    public String numberStringExtractor(String text){
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(text);
        while(m.find()){
            return m.group();
        }
        return m.group();
    }

    public void scrollWindows() throws InterruptedException {
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("window.scrollBy(0,350)", "");
        Thread.sleep(1000);
        jse.executeScript("window.scrollTo(0, -document.body.scrollHeight);");
        Thread.sleep(1000);
        jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        Thread.sleep(1000);
    }

    public int TotalAppNumber (){
        String result = numberStringExtractor(appHeaderText.getText());
        int total = Integer.parseInt(result);
        return total;
    }

    public int AppAmountOfResults () throws InterruptedException {
        int totalApps = 0;

        wait.until(ExpectedConditions.visibilityOf(appMoreLink)).click();
        scrollWindows();
        int Count = driver.findElements(By.className("lazyloaded")).size();
        totalApps = Integer.sum(totalApps,Count);

        if (driver.findElements(By.xpath("//a[@aria-label='página 2']")).size() > 0)
        {
            for (int i = 2; i < 9; i++){
                driver.findElement(By.xpath("//a[@aria-label='página " + i +"']")).click();
                scrollWindows();
                int elementCount = driver.findElements(By.className("lazyloaded")).size();
                totalApps = Integer.sum(totalApps,elementCount);
            }
        }
        //System.out.println("this is te number \n" + totalApps);
        return totalApps;
    }

    public String addFirstItemShoppingCart() throws InterruptedException {
        Thread.sleep(3000);
        wait.until(ExpectedConditions.visibilityOf(haloInfinite)).click();
        wait.until(ExpectedConditions.visibilityOf(cartBtn)).click();
        wait.until(ExpectedConditions.visibilityOf(shoppingIcon));
        return shoppingIcon.getText();
    }

    public String addSecondItemShoppingCart() throws InterruptedException {
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("window.scrollTo(0, -document.body.scrollHeight);");
        wait.until(ExpectedConditions.visibilityOf(secondItem)).click();
        wait.until(ExpectedConditions.visibilityOf(cartBtn)).click();
        Thread.sleep(4000);
        wait.until(ExpectedConditions.visibilityOf(shoppingIcon));
        return shoppingIcon.getText();
    }

    public String removeOneItemFromShoppingCart() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(openCartBtn)).click();
        wait.until(ExpectedConditions.visibilityOf(shoppingIcon)).click();
        wait.until(ExpectedConditions.visibilityOf(removeItem)).click();
        return shoppingIcon.getText();
    }



}
