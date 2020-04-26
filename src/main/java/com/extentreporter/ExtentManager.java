package com.extentreporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.drivers.TestBase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sun.rmi.runtime.Log;

import java.io.File;

//**********************************************************************************************************
//Description: ExtentReports related operations are done by this class. I added extra functionality such as
//"getCurrentPlatform". In this way, framework can create a report folder and file based on OS.
//Reference: http://extentreports.com/docs/versions/3/java/
//**********************************************************************************************************
public class ExtentManager {
    private static Logger logger = LogManager.getLogger(ExtentManager.class.getName());
    static String projectName = "SeleniumUIAutomation";
    private static ExtentReports extent;
    private static String reportFileName = "ExtentReports.html";
    private static String extentReportPath = System.getProperty("user.dir") + "\\target\\TestReport\\";

    public static ExtentReports getInstance() {
        if (extent == null)
            createInstance();

        return extent;
    }

    //Create an extent report instance
    public static ExtentReports createInstance() {
        createReportPath(extentReportPath);
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(extentReportPath + reportFileName);
        htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setDocumentTitle(projectName);
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setReportName(projectName);
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        logger.info("Building Extent reports uner path: "+extentReportPath);
        return extent;
    }


    //Create the report path if it does not exist
    private static void createReportPath(String path) {
        File testDirectory = new File(path);
        if (!testDirectory.exists()) {
            if (testDirectory.mkdir()) {
logger.info("Directory: " + path + " is created!");
                logger.info("Directory: " + path + " is created!");
            } else {
logger.info("Failed to create directory: " + path);
                logger.error("Failed to create directory: " + path);
            }
        } else {
//            logger.info("Directory already exists: " + path);
            logger.info("Directory already exists: " + path);

        }
    }


}