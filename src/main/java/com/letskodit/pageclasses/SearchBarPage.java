package com.letskodit.pageclasses;

import com.letskodit.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SearchBarPage extends BasePage {
    public SearchBarPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public WebDriver driver;
    //private String SEARCH_COURSE_FIELD = "/html[1]/body[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/form[1]/div[1]/input[1]";
    private String SEARCH_COURSE_FIELD = "id=>course";
    private String SEARCH_COURSE_BUTTON = "id=>search > div > button";

    public ResultsPage course(String courseName) {
        sendData(SEARCH_COURSE_FIELD, courseName, "Search Course Field", true);
        elementClick(SEARCH_COURSE_BUTTON, "Search Course Button");
        return new ResultsPage(driver);
    }
//        new WebDriverWait(driver, Duration.ofSeconds(10))
//                .ignoring(StaleElementReferenceException.class)
//                .until((WebDriver d)-> {
//                    new WebDriverWait(driver, Duration.ofSeconds(10))
//                     .until(ExpectedConditions.elementToBeClickable(By.name("search"))).click();
//                        WebElement searchField = driver.findElement(By.name(SEARCH_COURSE_FIELD));
//
//                    searchField.click();
//                    searchField.sendKeys(courseName);
//
//                    WebElement searchButton = driver.findElement(By.cssSelector(SEARCH_COURSE_BUTTON));
//                    searchButton.click();
//
//                    return true;
//                });



}
