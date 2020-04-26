package com.drivers;

import com.implementation.RestServiceUtilImplimentation;
import com.implementation.WaitImp;
import com.implementation.WebDriverManagementImp;
import com.services.RestServiceUtil;
import com.services.WaitforInterface;
import com.services.WebDriverManagement;
import com.testdatareader.PropertiesReader;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.Objects;
/*

WebDriverManagement--> is the interface to instantiate/serve the driver
 */

public abstract class TestBase {
    private static Logger logger = LogManager.getLogger(TestBase.class.getName());
    protected WebDriver webDriverInstance;
    protected RestServiceUtil restServiceUtil;
    protected WebDriverManagement webDriverManagement;
    protected WaitforInterface waitforInterface;
    private PropertiesReader propertiesReader;


    public TestBase() {
        super();
        this.propertiesReader=new PropertiesReader();
        this.restServiceUtil = new RestServiceUtilImplimentation();
        this.webDriverManagement = new WebDriverManagementImp(webDriverInstance);

    }


    @BeforeClass (description = "Configure Logger and Webdriver before class")
    public void beforeTest() {
        logger.info("starting Web driver!!");
        this.webDriverInstance = webDriverManagement.setupWebDriverInstance(propertiesReader.getHeadlessflg());
        this.waitforInterface = new WaitImp(webDriverInstance);
        if (Objects.isNull(this.webDriverInstance ) ) {
            logger.error("WebDriver Crashed", new NullPointerException());
            logger.throwing(Level.FATAL, new NullPointerException());
        }
    }

    @AfterClass (description = "Configure Logger and Webdriver after class")
    public void tearDown() {
        logger.info("closing the browser");
        logger.info("Webdriver instance is throwing:"+this.waitforInterface );
        webDriverManagement.quitWebDriverInstance();
    }


}
