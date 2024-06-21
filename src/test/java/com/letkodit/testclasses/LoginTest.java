package com.letkodit.testclasses;

import com.letkodit.base.BaseTest;
import com.letkodit.base.CheckPoint;
import com.letskodit.utilities.Constants;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @BeforeClass
    public void setUp() {


    }

    @AfterMethod
    public void afterMethod() {
        if (navigationpage.isUserLoggedIn()) {
            navigationpage.logOut();
            navigationpage.login();
        }
    }
//
    @Test
    public void testLogin() {
        navigationpage = login.signInWith(Constants.DEFAULT_USERNAME, Constants.DEFAULT_PASSWORD);
        boolean headerResult = navigationpage.verifyHeader();
        CheckPoint.mark("test-01", headerResult,"header verification");
        boolean result = navigationpage.isUserLoggedIn();
        CheckPoint.markFinal("test-01", result,"Login verification");
       // Assert.assertTrue(result);
    }

    @Test
    public void testInvalidLogin() {
        navigationpage = login.signInWith(Constants.DEFAULT_USERNAME, Constants.DEFAULT_PASSWORD);
        boolean result = navigationpage.isUserLoggedIn();
        Assert.assertFalse(result);
    }

}
