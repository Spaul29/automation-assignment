package com.ui.listeners;

import com.constants.Env;
import com.utility.PropertiesUtil;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class MyRetryAnalyzer implements IRetryAnalyzer {
    public static final int MAX_RETRY_COUNT = Integer.parseInt(PropertiesUtil.getPropertyValue(Env.QA, "MAX_RETRY_COUNT"));
    public static int retryCount = 1;

    @Override
    public boolean retry(ITestResult iTestResult) {
        if (retryCount <= MAX_RETRY_COUNT) {
            retryCount++;
            return true;
        }
        return false;
    }
}
