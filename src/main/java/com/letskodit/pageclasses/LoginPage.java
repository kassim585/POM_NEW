package com.letskodit.pageclasses;

import com.letskodit.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {

    /**...*/

    public WebDriver driver;

    private String EMAIL_FIELD = "id=>email";
    private String PASSWORD_FIELD = "id=>login-password";
    private String LOG_IN_BUTTON = "id=>login";

    public LoginPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public NavigationPage signInWith(String email, String password){
//        WebElement emailId = driver.findElement(By.id(EMAIL_FIELD));
//        emailId.clear();
//        emailId.sendKeys(email);
//
//        WebElement passwordTxt = driver.findElement(By.id(PASSWORD_FIELD));
//        passwordTxt.clear();
//        passwordTxt.sendKeys(password);
//
//
//        WebElement loginButton = driver.findElement(By.id(LOG_IN_BUTTON));
//        loginButton.click();
        sendData(EMAIL_FIELD, email, "Email Field");
        sendData(PASSWORD_FIELD, password, "Password Field");
        elementClick(LOG_IN_BUTTON, "Login Button");
        return new NavigationPage(driver);

    }



}
