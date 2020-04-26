package com.extentreporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.drivers.TestBase;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener extends TestBase implements ITestListener {
    private static Logger logger = LogManager.getLogger(TestListener.class.getName());
    //Extent Report Declarations
    private static ExtentReports extent = ExtentManager.createInstance();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public synchronized void onStart(ITestContext context) {
        logger.info("We started compiling the Extent Reports!");
        logger.info("We started compiling the Extent Reports!");

    }

    @Override
    public synchronized void onFinish(ITestContext context) {
//        logger.info("Compiling the report");
        logger.info("we are now compiling the report; Will be flushed soon!!! :) ");
        extent.flush();
    }

    @Override
    public synchronized void onTestStart(ITestResult result) {
//        logger.info(result.getMethod().getMethodName() + " started!");
        logger.info(result.getMethod().getMethodName() + " started!");
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName(), result.getMethod().getDescription());
        test.set(extentTest);
    }

    @Override
    public synchronized void onTestSuccess(ITestResult result) {
//        logger.info((result.getMethod().getMethodName() + " passed!"));
        logger.info(result.getMethod().getMethodName() + "Hurray your test passed!! ");
        test.get().pass("Test passed");
    }

    @Override
    public synchronized void onTestFailure(ITestResult result) {
        logger.info(result.getMethod().getMethodName() + " failed!");
        logger.error(result.getMethod().getMethodName() + " failed!");
        logger.throwing(Level.FATAL,result.getThrowable());
        test.get().fail(result.getThrowable());
    }

    @Override
    public synchronized void onTestSkipped(ITestResult result) {
//        logger.info((result.getMethod().getMethodName() + "there must be some failures... Your test is skipped!"));
        logger.warn(result.getMethod().getMethodName() + "there must be some failures... Your test is skipped!");
        logger.throwing(Level.ERROR,result.getThrowable());
        test.get().skip(result.getThrowable());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
//        logger.info("onTestFailedButWithinSuccessPercentage for " + result.getMethod().getMethodName());
        logger.warn("onTestFailedButWithinSuccessPercentage for " + result.getMethod().getMethodName());
        logger.throwing(Level.ERROR,result.getThrowable());
    }
}