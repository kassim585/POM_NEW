package com.letskodit.pageclasses;

import com.letskodit.base.BasePage;
import com.letskodit.utilities.Util;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class NavigationPage extends BasePage {

    public WebDriver driver;
    private String LOGIN_LINK = "xpath=>//a[contains(@href,'login')]";
    private String LOGOUT_LINK = "xpath=>//a[@href='/logout']";
    private final String URL = "https://www.letskodeit.com/";
    private String ALL_COURSES_LINK = "xpath=>//*[contains(text(),'ALL COURSES')]";
    private String ACCOUNT_ICON = "xpath=>//img[@class='zl-navbar-rhs-img ']";

    public NavigationPage(WebDriver driver) {
        super(driver);
        this.driver=driver;
    }


    public SearchBarPage allCourses(){
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .ignoring(StaleElementReferenceException.class)
                        .until((WebDriver d)-> {
                            elementClick(ALL_COURSES_LINK, "All Courses Link");
                            return true;
                        });
        return new SearchBarPage(driver);

    }

    public boolean isOpen(){
      return URL.equalsIgnoreCase(driver.getCurrentUrl()) ;
    }

    public boolean isUserLoggedIn() {
        try {
            List<WebElement> accountImage = getElementList(ACCOUNT_ICON, "Account Icon");
            if (accountImage.size() > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public LoginPage login(){
        elementClick(LOGIN_LINK, "Login Link");
         return new LoginPage(driver);
    }

    public void logOut(){


        //driver.findElement(By.cssSelector(ACCOUNT_ICON)).click();
        //WebDriverWait wait =new WebDriverWait(driver,Duration.ofSeconds(3));
        //WebElement logoutLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(LOGOUT_LINK)));
        //logoutLink.click();
        elementClick(ACCOUNT_ICON, "User Account Icon");
        WebElement logoutLink = waitForElement(LOGOUT_LINK, 10);
        javascriptClick(logoutLink, "Logout Link");

    }

//    public boolean verifyHeader(){
//        WebElement link = driver.findElement(By.xpath(ALL_COURSES_LINK));
//        String text = link.getText();
//        return  Util.verifyTextContains(text,"ALL COURSES");
//    }


    public boolean verifyHeader() {
        WebElement link = getElement(ALL_COURSES_LINK, "All Courses Link");
        return Util.verifyTextContains(link.getText(), "All Courses");
    }
}
