package com.letskodit.pageclasses;

import com.letskodit.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ResultsPage extends BasePage {

    public WebDriver driver;
    private String URL = "query=:";
    private String COURSE_LIST = "xpath=>//*[contains(@class,'-course-list')]";

    public ResultsPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public void isOpen(){

    }

    public int coursesCount(){
          List<WebElement> coursesList = getElementList(COURSE_LIST, "Courses List");
        return coursesList.size();
    }

    public boolean verifySearchResult(){
         boolean result = false;
         if(coursesCount()>0){
             result = true;
         }
         return result;
    }

    public boolean verifyFilterCoursecount(int expectedcount){
        return  coursesCount() == expectedcount;
    }

}
