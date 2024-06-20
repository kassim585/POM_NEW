package com.letkodit.base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.letskodit.base.CustomDriver;
import com.letskodit.utilities.ExtentManager;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;

import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;

public class TestListeners extends BaseTest implements ITestListener {
    private static ExtentReports extentReports = ExtentManager.getInstance();
    private static ThreadLocal<ExtentTest> extenttest = new ThreadLocal<ExtentTest>();
    private static final Logger log = LogManager.getLogger(TestListeners.class.getName());


    public void onStart(ITestContext context) {
        log.info("On start -> Test Tag Name: " + context.getName());
        ITestNGMethod methods[] = context.getAllTestMethods();
        log.info("These methods will be executed in this <test> tag");
        for (ITestNGMethod method : methods) {
            log.info(method.getMethodName());
        }
    }

    public void onFinish(ITestContext context) {
        log.info("onFinish -> Test Tag Name: " + context.getName());
        extentReports.flush();
    }

    public void onTestStart(ITestResult result) {
        ExtentTest test = extentReports.createTest(result.getInstanceName()
                + " :: " + result.getMethod().getMethodName());
        extenttest.set(test);
    }

    public void onTestSuccess(ITestResult result) {
        log.info("OnTestSuccess -> Test Method Name: " + result.getName());
        String methodName = result.getMethod().getMethodName();
        String logText = "<b>" + "Test Method " + methodName + " Successful" + "</b";
        Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
        extenttest.get().log(Status.PASS, m);
    }

    public void onTestFailure(ITestResult result) {
        log.info("OnTestFailure -> Test Method Name: " + result.getName());
        String methodName = result.getMethod().getMethodName();
        String exceptionMessage = Arrays.toString(result.getThrowable().getStackTrace());
        extenttest.get().fail("<details>" + "<summary>" + "<b>" + "<font color=red>" +
                "Exception Occurred: Click to see details: " + "</font>" + "</b>" + "</summary>" +
                exceptionMessage.replaceAll(",", "<br>") + "</details>" + " \n");

        String browser = WebDriverFactory.getInstance().getBrowser();
        WebDriver driver = WebDriverFactory.getInstance().getDriver(browser);
        CustomDriver cd = new CustomDriver(driver);
        String path = cd.takeScreenshot(result.getName(), browser);
        try {
            extenttest.get().fail("<b>" + "<font color=red>" +
                            "Screenshot of failure" + "</font>" + "</b>",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(path).build());

        } catch (IOException e) {
            extenttest.get().fail("Test Method Failed, cannot attach screenshot");
        }


        String logText = "<b>" + "Test Method " + methodName + " Failed" + "</b";
        Markup m = MarkupHelper.createLabel(logText, ExtentColor.RED);
        extenttest.get().log(Status.FAIL, m);
    }

    public void onTestSkipped(ITestResult result) {
        log.info("onTestSkipped -> Test Method Name: " + result.getName());
        String methodName = result.getMethod().getMethodName();
        String logText = "<b>" + "Test Method " + methodName + " Skipped" + "</b>";
        Markup m = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
        extenttest.get().log(Status.PASS, m);

    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    public void onTestFailedWithTimeout(ITestResult result) {

    }


}
