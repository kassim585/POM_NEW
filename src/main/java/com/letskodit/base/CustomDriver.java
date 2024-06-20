package com.letskodit.base;

import com.letskodit.utilities.Constants;
import com.letskodit.utilities.Util;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;


public class CustomDriver {
    private static final Logger log = LogManager.getLogger(CustomDriver.class.getName());
    public WebDriver driver;
    private JavascriptExecutor js;

    public CustomDriver(WebDriver driver) {
        this.driver = driver;
        this.js = (JavascriptExecutor) driver;
    }

    public void refersh() {
        driver.navigate().refresh();
        System.out.println("The current browser location was refreshed");
    }

    public String getTitle() {
        String title = driver.getTitle();
        System.out.println("Title of the page is : " + title);
        return title;
    }

    public String getURL() {
        String url = driver.getCurrentUrl();
        log.info("Current URL is : +" + url);
        return url;
    }

    public void navigateBrowserBack() {
        driver.navigate().back();
        log.info("Navigate back");
    }

    public void navigateBrowserForward() {
        driver.navigate().forward();
        log.info("Navigate forward");
    }

    public WebElement getElement(String locator, String info) {
        WebElement element = null;
        By byType = getByType(locator);
        try {
            element = driver.findElement(byType);
            log.info("Element found with: " + locator);
        } catch (Exception e) {
            log.error("Element Not found with: " + locator);
        }
        return element;
    }

    private By getByType(String locator) {
        By by = null;
        String locatorType = locator.split("=>")[0];
        locator = locator.split("=>")[1];
        try {
            if (locatorType.contains("id")) {
                by = By.id(locator);
            } else if (locatorType.contains("xpath")) {
                by = by.xpath(locator);
            } else if (locatorType.contains("name")) {
                by = By.name(locator);
            } else if (locatorType.contains("css")) {
                by = By.cssSelector(locator);
            } else if (locatorType.contains("class")) {
                by = By.className(locator);
            } else if (locatorType.contains("tag")) {
                by = By.tagName(locator);
            } else if (locatorType.contains("link")) {
                by = By.linkText(locator);
            } else if (locatorType.contains("partiallink")) {
                by = By.partialLinkText(locator);
            } else {
                log.info("Locator type not supported");
            }
        } catch (Exception e) {
            log.error("By type not found with : " + locatorType);
        }
        return by;
    }

    private WebElement getByElement(String locator, String info) {
        WebElement element = null;
        By byType = getByType(locator);
        try {
            element = driver.findElement(byType);
            log.info("Element " + info + " Found with locator : " + locator);
        } catch (Exception e) {
            log.error("Element Not Found with: " + locator);
            e.printStackTrace();
        }
        return element;
    }

    public List<WebElement> getElementList(String locator, String info) {
        List<WebElement> elementList = new ArrayList<>();
        By byType = getByType(locator);
        try {
            elementList = driver.findElements(byType);
            log.info("Element List found with: " + locator);
        } catch (Exception e) {
            log.error("Element List not found with: " + locator);
            e.printStackTrace();
        }
        return elementList;
    }


    public boolean isElementPresent(String locator, String info) {
        List<WebElement> elementList = getElementList(locator, info);
        int size = elementList.size();
        if (size > 0) {
            return true;
        } else {
            return false;
        }

    }

    public void elementClick(WebElement element, String info, long timeToWait) {
        try {
            element.click();
            if (timeToWait == 0) {
                log.info("Clicked on :: " + info);
            } else {
                Util.sleep(timeToWait, "Clicked on :: " + info);
            }
        } catch (Exception e) {
            log.error("Cannot click on :: " + info);
            takeScreenshot("Click error", "");
        }
    }


//    public String takeScreenshot(String methodName, String browserName) {
//        String fileName = Util.getScreenshotName(methodName, browserName);
//        String screenshotDir = System.getProperty("user.dir") + "/screenshots/";
//        new File(screenshotDir).mkdirs();
//        String path = screenshotDir + fileName;
//
//        try {
//            File screenshot = ((TakesScreenshot)driver).
//                    getScreenshotAs(OutputType.FILE);
//            FileUtils.copyFile(screenshot, new File(path));
//            log.info("Screen Shot Was Stored at: "+ path);
//        } catch(Exception e) {
//            e.printStackTrace();
//        }
//        return path;
//    }


    public String takeScreenshot(String methodName, String browserName) {
        File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String encodedBase64 = null;
        FileInputStream fileInputStreamReader = null;
        try {
            fileInputStreamReader = new FileInputStream(sourceFile);
            byte[] bytes = new byte[(int) sourceFile.length()];
            fileInputStreamReader.read(bytes);
            encodedBase64 = new String(Base64.encodeBase64(bytes));
            //String path = System.getProperty("user.dir") + "/screenshots/";
            String path = Constants.REPORTS_DIRECTORY + "/screenshots/";
            File destination = new File(path);
            FileUtils.copyFile(sourceFile, destination);
            bytes = IOUtils.toByteArray(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //return "data:image/png;base64,"+encodedBase64;
        return encodedBase64;
    }


//
//    public String takeScreenshot(String methodName, String browserName) {
//
//
//        String fileName = Util.getScreenshotName(methodName, browserName);
//        String screenshotDir = System.getProperty("user.dir") + "/screenshots/";
//        //new File(screenshotDir).mkdirs();
//        String path = screenshotDir + fileName;
//        log.info("Screen path: "+ screenshotDir + fileName);
//        String encodedBase64 = null;
//
//        File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
//        File destinationPath = new File(System.getProperty("user.dir") + "\\screenshots\\"+methodName+".png");
//        FileInputStream fileInputStream = null;
//
//        try{
//
//            FileUtils.copyFile(screenshot,destinationPath);
//            fileInputStream =new FileInputStream(destinationPath);
//            byte[] bytes =new byte[(int)destinationPath.length()];
//            fileInputStream.read(bytes);
//            encodedBase64 = new String(Base64.encodeBase64(bytes));
//            //FileUtils.copyFile(screenshot, new File(path));
//           // log.info("Screen shot was stored at: "+ path);
//        }catch(Exception e){
//        e.printStackTrace();
//        }
//            //return destinationPath.getAbsolutePath();
//        return "data:image/png;base64";
//    }

    public void elementClick(WebElement element, String info) {
        elementClick(element, info, 0);
    }

    public void elementClick(String locator, String info, long timeToWait) {
        WebElement element = this.getElement(locator, info);
        elementClick(element, info, timeToWait);
    }

    public void elementClick(String locator, String info) {
        WebElement element = getElement(locator, info);
        elementClick(element, info, 0);
    }


    public void javascriptClick(String locator, String info) {
        WebElement element = getElement(locator, info);
        try {
            js.executeScript("arguments[0].click();", element);
            log.info("Clicked on :: " + info);
        } catch (Exception e) {
            log.error("Cannot click on :: " + info);
        }
    }

    public void javascriptClick(WebElement element, String info) {
        try {
            js.executeScript("arguments[0].click();", element);
            log.info("Clicked on :: " + info);
        } catch (Exception e) {
            log.error("Cannot click on :: " + info);
        }
    }


    public void clickWhenReady(By locator, int timeout) {
        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
            WebElement element = null;
            log.info("Waiting for max:: " + timeout + " seconds for element to be clickable");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            element = wait.until(ExpectedConditions.elementToBeClickable(locator));
            element.click();
            log.info("Element clicked on the web page");
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        } catch (Exception e) {
            log.error("Element not appeared on the web page");
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        }
    }


    public void sendData(WebElement element, String data, String info, Boolean clear) {
        try {
            if (clear) {
                element.clear();
            }
            element.sendKeys(data);
            log.info("Send keys on element :: " + info + "with data :: " + data);
        } catch (Exception e) {
            log.error("cannot Send keys on element :: " + info + "with data :: " + data);
        }
    }

    public void sendData(String locator, String data, String info, Boolean clear) {
        WebElement element = getElement(locator, info);
        sendData(element, data, info, true);
    }

    public void sendData(String locator, String data, String info) {
        WebElement element = getElement(locator, info);
        sendData(element, data, info, true);
    }


    public String getText(WebElement element, String info) {
        System.out.println("Getting text on element :: " + info);
        String text = null;
        text = element.getText();
        if (text.length() == 0) {
            element.getAttribute("innerText");
        }
        if (!text.isEmpty()) {
            System.out.println(" The text is : " + text);

        } else {
            text = "NOT_FOUND";
        }
        return text.trim();
    }

    public Boolean isEnabled(WebElement element, String info) {
        Boolean enabled = false;
        if (element != null) {
            enabled = element.isEnabled();
            if (enabled)
                System.out.println("Element :: " + info + " is Enabled");
            else
                System.out.println("Element :: " + info + " is Disabled");
        }
        return enabled;
    }

    /***
     * Check if element is enabled with locator
     * @param locator
     * @param info
     * @return
     */
    public Boolean isEnabled(String locator, String info) {
        WebElement element = getElement(locator, info);
        return this.isEnabled(element, info);
    }

    public Boolean isDisplayed(WebElement element, String info) {
        Boolean displayed = false;
        if (element != null) {
            displayed = element.isDisplayed();
            if (displayed)
                System.out.println("Element :: " + info + " is displayed");
            else
                System.out.println("Element :: " + info + " is NOT displayed");
        }
        return displayed;
    }

    /***
     * Check if element is displayed with locator
     * @param locator
     * @param info
     * @return
     */
    public Boolean isDisplayed(String locator, String info) {
        WebElement element = getElement(locator, info);
        return this.isDisplayed(element, info);
    }

    public Boolean isSelected(WebElement element, String info) {
        Boolean selected = false;
        if (element != null) {
            selected = element.isSelected();
            if (selected)
                System.out.println("Element :: " + info + " is selected");
            else
                System.out.println("Element :: " + info + " is already selected");
        }
        return selected;
    }

    /**
     * @param locator
     * @param info
     * @return
     */
    public Boolean isSelected(String locator, String info) {
        WebElement element = getElement(locator, info);
        return isSelected(element, info);
    }

    public void Check(WebElement element, String info) {
        if (!isSelected(element, info)) {
            elementClick(element, info);
            System.out.println("Element :: " + info + " is checked");
        } else
            System.out.println("Element :: " + info + " is already checked");
    }

    /**
     * Selects a check box irrespective of its state, using locator
     *
     * @param locator
     * @param info
     * @return
     */
    public void Check(String locator, String info) {
        WebElement element = getElement(locator, info);
        Check(element, info);
    }

    /**
     * UnSelects a check box irrespective of its state
     *
     * @param element
     * @param info
     * @return
     */
    public void UnCheck(WebElement element, String info) {
        if (isSelected(element, info)) {
            elementClick(element, info);
            System.out.println("Element :: " + info + " is unchecked");
        } else
            System.out.println("Element :: " + info + " is already unchecked");
    }

    /**
     * UnSelects a check box irrespective of its state, using locator
     *
     * @param locator
     * @param info
     * @return
     */
    public void UnCheck(String locator, String info) {
        WebElement element = getElement(locator, info);
        UnCheck(element, info);
    }

    public Boolean Submit(WebElement element, String info) {
        if (element != null) {
            element.submit();
            System.out.println("Element :: " + info + " is submitted");
            return true;
        } else
            return false;
    }


    public void mouseHover(String locator, String info) {
        WebElement element = getElement(locator, info);
        Actions action = new Actions(driver);
        action.moveToElement(element).perform();
        //Util.sleep(5000);
    }

    /**
     * @param element
     * @param optionToSelect
     */
    public void selectOption(WebElement element, String optionToSelect) {
        Select sel = new Select(element);
        sel.selectByVisibleText(optionToSelect);
        System.out.println("Selected option : " + optionToSelect);
    }

    /**
     * Selects a given option in list box
     *
     * @param locator
     * @param optionToSelect
     */
    public void selectOption(String locator, String optionToSelect, String info) {
        WebElement element = getElement(locator, info);
        this.selectOption(element, optionToSelect);
    }

    /**
     * get Selected drop down value
     *
     * @param element
     * @return
     */
    public String getSelectDropDownValue(WebElement element) {
        Select sel = new Select(element);
        return sel.getFirstSelectedOption().getText();
    }


    public boolean isOptionExists(WebElement element, String optionToVerify) {
        Select sel = new Select(element);
        boolean exists = false;
        List<WebElement> optList = sel.getOptions();
        for (int i = 0; i < optList.size(); i++) {
            String text = getText(optList.get(i), "Option Text");
            if (text.matches(optionToVerify)) {
                exists = true;
                break;
            }
        }
        if (exists) {
            System.out.println("Selected Option : " + optionToVerify + " exist");
        } else {
            System.out.println("Selected Option : " + optionToVerify + " does not exist");
        }
        return exists;
    }

    public String getElementAttributeValue(String locator, String attribute) {
        WebElement element = getElement(locator, "info");
        return element.getAttribute(attribute);
    }

    /**
     * @param element
     * @param attribute
     * @return
     */
    public String getElementAttributeValue(WebElement element, String attribute) {
        return element.getAttribute(attribute);
    }

    public WebElement waitForElement(String locator, int timeout) {
        By byType = getByType(locator);
        WebElement element = null;
        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
            System.out.println("Waiting for max:: " + timeout + " seconds for element to be available");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
            element = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(byType));
            System.out.println("Element appeared on the web page");
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        } catch (Exception e) {
            System.out.println("Element not appeared on the web page");
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        }
        return element;
    }

    /***
     * Wait for element to be clickable
     * @param locator - locator strategy, id=>example, name=>example, css=>#example,
     *      *                tag=>example, xpath=>//example, link=>example
     * @param timeout - Duration to try before timeout
     */
    public WebElement waitForElementToBeClickable(String locator, int timeout) {
        By byType = getByType(locator);
        WebElement element = null;
        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
            System.out.println("Waiting for max:: " + timeout + " seconds for element to be clickable");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            element = wait.until(
                    ExpectedConditions.elementToBeClickable(byType));
            System.out.println("Element is clickable on the web page");
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        } catch (Exception e) {
            System.out.println("Element not appeared on the web page");
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        }
        return element;
    }

    public boolean waitForLoading(String locator, long timeout) {
        By byType = getByType(locator);
        boolean elementInvisible = false;
        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
            System.out.println("Waiting for max:: " + timeout + " seconds for element to be available");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
            elementInvisible = wait.until(
                    ExpectedConditions.invisibilityOfElementLocated(byType));
            System.out.println("Element appeared on the web page");
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        } catch (Exception e) {
            System.out.println("Element not appeared on the web page");
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        }
        return elementInvisible;
    }


    public void DoubleClick(WebElement element, String info) {
        Actions action = new Actions(driver);
        action.doubleClick(element);
        System.out.println("Double Clicked on :: " + info);
        action.perform();
    }

    /**
     * Right Click a WebElement
     *
     * @param locator
     */
    public void rightClick(String locator, String info) {
        WebElement element = getElement(locator, info);
        Actions action = new Actions(driver);
        action.contextClick(element).build().perform();
        System.out.println("Double Clicked on :: " + info);
    }


}
