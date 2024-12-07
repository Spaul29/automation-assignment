package com.ui.listeners;

import com.aventstack.extentreports.Status;
import com.ui.tests.TestBase;
import com.utility.BrowserUtility;
import com.utility.ExtentReportUtility;
import com.utility.LoggerUtility;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.Arrays;

public class TestListener implements ITestListener {

    Logger logger = LoggerUtility.getLogger(this.getClass());

    @Override
    public void onTestStart(ITestResult result) {
        logger.info(result.getMethod().getMethodName());
        logger.info(result.getMethod().getDescription());
        logger.info(Arrays.toString(result.getMethod().getGroups()));
        ExtentReportUtility.setExtentTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info("{} PASSED",result.getMethod().getMethodName());
        ExtentReportUtility.getExtentTest().log(Status.PASS,result.getMethod().getMethodName()+" PASSED");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        logger.info("{} FAILED", result.getMethod().getMethodName());
        logger.info(result.getThrowable().getMessage());
        ExtentReportUtility.getExtentTest().log(Status.FAIL,result.getMethod().getMethodName()+" FAILED");
        ExtentReportUtility.getExtentTest().log(Status.FAIL,result.getThrowable().getMessage());
        Object testClass = result.getInstance();
        BrowserUtility browserUtility = ((TestBase)testClass).getInstance();
        logger.info("Capturing screenshot for failed tests");
        String screenshotPath = browserUtility.takeScreenshot(result.getMethod().getMethodName());
        logger.info("Attaching screenshot to HTML file");
        ExtentReportUtility.getExtentTest().addScreenCaptureFromPath(screenshotPath);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        logger.warn("{} SKIPPED", result.getMethod().getMethodName());
        ExtentReportUtility.getExtentTest().log(Status.SKIP,result.getMethod().getMethodName()+" SKIPPED");
    }

    @Override
    public void onStart(ITestContext context) {
        logger.info("Test Suite Started");
        ExtentReportUtility.setUpExtentReporter("report.html");
    }

    @Override
    public void onFinish(ITestContext context) {
        logger.info("Test Suite Finished");
        ExtentReportUtility.flushExtentReport();
    }
}
