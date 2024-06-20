package com.letkodit.testclasses;

import com.letkodit.base.BaseTest;
import com.letskodit.pageclasses.CategoryFilterPage;
import com.letskodit.pageclasses.NavigationPage;
import com.letskodit.utilities.Constants;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AllCoursesTest extends BaseTest {


    @BeforeClass
    public void setUp() {
        navigationpage = login.signInWith(Constants.DEFAULT_USERNAME, Constants.DEFAULT_PASSWORD);
    }

    @Test
    public void verifySearchCourse() {

        search = navigationpage.allCourses();
        results = search.course("rest api");
        boolean searchResult = results.verifySearchResult();
        Assert.assertTrue(searchResult);

    }

    @Test
    public void filterByCategory() {
        navigationpage = new NavigationPage(driver);
        navigationpage.allCourses();
        category = new CategoryFilterPage(driver);
        results = category.select("Test Automation");
    }


    @AfterClass
    public void driverClose() {
        driver.close();
        driver.quit();
    }

}
