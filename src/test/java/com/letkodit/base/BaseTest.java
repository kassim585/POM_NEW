package com.letkodit.base;

import com.letskodit.pageclasses.*;
import com.letskodit.utilities.Constants;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class BaseTest {

    String baseURL;
    public  WebDriver driver;
    protected NavigationPage navigationpage;
    protected LoginPage login;
    protected CategoryFilterPage category;
    protected ResultsPage results;
    protected SearchBarPage search;

    @BeforeClass
    public void commonSetUp() {
        driver = WebDriverFactory.getInstance().getDriver(Constants.CHROME);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        baseURL = Constants.BASE_URL;
        driver.get(baseURL);
        navigationpage = new NavigationPage(driver);
        login = navigationpage.login();

    }

    @AfterClass
    public void CommonTearDown() {
        WebDriverFactory.getInstance().quitDriver();
    }


    @BeforeMethod
    public void methodSet(){
        CheckPoint.clearHashMap();
    }


}
