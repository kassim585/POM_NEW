package com.letkodit.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.time.Duration;

public class WebDriverFactory {


    //Singleton
    // Only one instance of the class can exist at a time
    private static final WebDriverFactory instance = new WebDriverFactory();

    private WebDriverFactory() {
    }

    public static WebDriverFactory getInstance(){
        return instance;
    }

    private static ThreadLocal<WebDriver> threadedDriver = new ThreadLocal<WebDriver>();
    private static ThreadLocal<String> threadedBrowser = new ThreadLocal<>();

    public WebDriver getDriver(String browser){
        WebDriver driver = null;
        threadedBrowser.set(browser);
        if(threadedDriver.get() == null) {
            try {
                if (browser.equalsIgnoreCase("firefox")) {
                    driver = new FirefoxDriver();
                    threadedDriver.set(driver);
                }
                if (browser.equalsIgnoreCase("chrome")) {
                    ChromeOptions chromeOptions =setChromeOptions();
                    driver = new ChromeDriver(chromeOptions);
                    threadedDriver.set(driver);
                }
                if (browser.equalsIgnoreCase("ie")) {
                    driver = new InternetExplorerDriver();
                    threadedDriver.set(driver);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            threadedDriver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
            threadedDriver.get().manage().window().maximize();

        }
        return threadedDriver.get();
    }

    public String getBrowser(){
        return threadedBrowser.get();
    }

    public void quitDriver(){
        threadedDriver.get().quit();
        threadedDriver.set(null);
    }

    private ChromeOptions setChromeOptions(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("disable-infobars");
        options.addArguments("--disable-popup-blocking");
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        return options;
    }

}
