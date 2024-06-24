package com.letskodit.utilities;

import com.aventstack.extentreports.ExtentReports;

import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.io.File;

public class ExtentManager {
    private static final Logger log = LogManager.getLogger(ExtentManager.class.getName());
    private static ExtentReports extent;

    public static ExtentReports getInstance(){
        if(extent==null){
            createInstance();
        }
            return extent;
    }

    public static synchronized  ExtentReports createInstance(){
        String fileName = Util.getReportName();
        String reportsDirectory = Constants.REPORTS_DIRECTORY;
        new File(reportsDirectory).mkdirs();
        String path = reportsDirectory + fileName;
        log.info("************Report Path************");
        log.info(path);
        log.info("************Report Path************");
        ExtentSparkReporter spark = new ExtentSparkReporter(path);
        spark.config().setTheme(Theme.STANDARD);
        spark.config().setDocumentTitle("Automation Run");
        spark.config().setEncoding("utf-8");
        spark.config().setReportName(fileName);
        extent = new ExtentReports();
        extent.setSystemInfo("Organization", "Kassim Mastering Automation");
        extent.setSystemInfo("Automation Framework", "Selenium WebDriver");
        extent.attachReporter(spark);
        return extent;
    }
}
