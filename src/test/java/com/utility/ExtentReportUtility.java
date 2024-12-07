package com.utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportUtility {

    private static ExtentReports extentReports;
    private static final ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    public static void setUpExtentReporter(String reportName) {
        ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") +"/"+reportName);
        extentReports = new ExtentReports();
        extentReports.attachReporter(extentSparkReporter);
    }

    public static void setExtentTest(String testName) {
        ExtentTest test = extentReports.createTest(testName);
        extentTest.set(test);
    }

    public static ExtentTest getExtentTest() {
        return extentTest.get();
    }

    public static void flushExtentReport() {
        extentReports.flush();
    }

}
