package com.demo.testcases;

import com.demo.base.TestBase;
import com.demo.pages.HomePage;
import com.demo.pages.SearchPage;
import com.demo.pages.WindowsPage;
import com.demo.util.TestUtil;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class TC1SearchValidation extends TestBase {

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

    @Test(priority = 1, dataProvider = "data")
    public void searchValidation(String[] text) throws InterruptedException {
        homePage.clickOnWindowsTab();
        windowsPage.searchOnWindowsPage(text[0]);
        Assert.assertEquals(searchPage.TotalAppNumber(),searchPage.AppAmountOfResults());
    }

    @DataProvider(name= "data")
    String[] readJson() throws IOException, ParseException {
        String filePath = new File("").getAbsolutePath();
        JSONParser jsonParser = new JSONParser();
        FileReader reader = new FileReader(".\\jsonfiles\\testdata.json");


        Object obj = jsonParser.parse(reader);

        JSONObject user_data = (JSONObject) obj;
        JSONArray userArray = (JSONArray) user_data.get("search_variables");

        String arr[] = new String[userArray.size()];

        for(int i = 0; i < userArray.size(); i++)
        {
            JSONObject variables=(JSONObject) userArray.get(i);
            String v1 = (String) variables.get("v1");
            arr[i]= v1;
        }
        return arr;
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

}
