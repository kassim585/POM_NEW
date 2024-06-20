package com.letskodit.pageclasses;

import com.letskodit.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CategoryFilterPage extends BasePage {
    WebDriver driver;

    private String CATEGORY_DROPDOWN = "name=>categories";
    private String CATEGORY_OPTION = "xpath=>//select[@name='categories']|//option";
    private JavascriptExecutor js;

    public CategoryFilterPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        js = (JavascriptExecutor) driver;
    }

    public ResultsPage select(String categoryName) {
        WebElement categoryOption = findCategory(categoryName);
//        js.executeScript("arguments[0].click();",driver.findElement(By.xpath(String.format(CATEGORY_VALUE,categoryName))));
//        js.executeScript("var elem=arguments[0]; setTimeout(function() {elem.click();}, 100)", categoryName);
//        Select dropDown = new Select(driver.findElement(By.xpath(CATEGORY_OPTION)));
//        dropDown.selectByVisibleText(categoryName);
        javascriptClick(categoryOption, "Category Option");
        return new ResultsPage(driver);
    }

    public void clickCategoryDropdown() {

        elementClick(CATEGORY_DROPDOWN, "Category Dropdown");
    //    WebElement categoryDropDown = driver.findElement(By.name(CATEGORY_DROPDOWN));
    //    categoryDropDown.click();

    }

    public WebElement findCategory(String categoryName) {

        clickCategoryDropdown();
        WebElement categoryOption = waitForElementToBeClickable(String.format(CATEGORY_OPTION,categoryName), 15);
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//        WebElement categoryOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(String.format(CATEGORY_OPTION, categoryName))));
        return categoryOption;
    }
}
