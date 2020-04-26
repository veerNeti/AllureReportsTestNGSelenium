package com.cogmento.testcases;

import com.cogmento.pages.HomePO;
import com.cogmento.pages.LoginPO;
import com.drivers.TestBase;
import com.implementation.BrowserInteractionServiceImplementation;
import com.implementation.WaitImp;
import com.services.BrowserInteractionService;
import com.services.WaitforInterface;
import com.testdatareader.PropertiesReader;
import io.qameta.allure.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class HomePageNavBar extends TestBase {
    private PropertiesReader propertiesReader;
    private HomePO homePO;
    private LoginPO loginPO;
    private WaitforInterface waitforInterface;
    private BrowserInteractionService browserInteractionService;
    private static Logger logger = LogManager.getLogger(HomePageNavBar.class.getName());
    public HomePageNavBar() {
        super();
        this.propertiesReader = new PropertiesReader();
        this.homePO = new HomePO();
        this.loginPO = new LoginPO();
        logger.info("Constructing all local variables. This will not instantiate Wait");
    }

    @DataProvider(name = "Authentication")
    public Object[][] credentials() {
        return new Object[][]{{propertiesReader.getAut(), propertiesReader.getUserName(), propertiesReader.decodePassword(propertiesReader.getPassword())}};
    }


    @Link("https://ui.cogmento.com/ ")
    @Test(alwaysRun = true, dataProvider = "Authentication", description = "verify the url and login credentials")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Test Case Description: Verify the application under test and  login credentials")
    @Story("Story Name: Validate the homePage")
    void launchAutCogmeto(String auTestURL, String userName, String password) {
        webDriverInstance.get(auTestURL);
        assertThat(webDriverInstance.getCurrentUrl().compareToIgnoreCase(auTestURL));
        this.browserInteractionService = new BrowserInteractionServiceImplementation(webDriverInstance);
        this.browserInteractionService.insertIntoField(loginPO.getUserIdfield(), userName);
        this.browserInteractionService.insertIntoField(loginPO.getPasswordField(), password);
        this.browserInteractionService.clickTheButton(loginPO.getLoginButton());
    }


    @Test(dependsOnMethods = "launchAutCogmeto",dataProvider = "Authentication", groups = "navbar", description = "verify UserName from the profile field is visible")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Case Description: verify UserName from the profile field is visible")
    @Story("Story Name: Validate the homePage")
    void testHomeNavBarUserNameVisibility(String auTestURL, String userName, String password) {
        this.waitforInterface = new WaitImp(webDriverInstance);
        assertThat(waitforInterface.webDriverWaitTillVisibilityOfBy(homePO.getUserNamedisplay()).isDisplayed());
    }


    @Test(dependsOnMethods = "testHomeNavBarUserNameVisibility",dataProvider = "Authentication", groups = "navbar", description = "verify UserName from the profile field is visible")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Case Description: verify UserName from the profile field is visible")
    @Story("Story Name: Validate the homePage")
    void testNavBarUserNameTextVisibility(String auTestURL, String userName, String password) {
        this.waitforInterface = new WaitImp(webDriverInstance);
        assertThat(waitforInterface.waitFluentForTexttoBeBy(homePO.getUserNamedisplay()).compareToIgnoreCase("Veer Neti"));
    }

    @Test(dependsOnMethods = "testNavBarUserNameTextVisibility",dataProvider = "Authentication", groups = "navbar",description = "Verify Nav Bar Trail Indicator Visibility")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Case Description: Verify Nav Bar Trail Indicator Visibility")
    @Story("Story Name: Validate the homePage")
    void testNavBarTrailIndicatorVisibility(String auTestURL, String userName, String password) {
        this.waitforInterface = new WaitImp(webDriverInstance);
        assertThat(waitforInterface.waitTillWebElementToBeClickable(homePO.getTrialIndicator()).isDisplayed());
    }

    @Test(dependsOnGroups = "navbar", groups = "sidebar",dataProvider = "Authentication",description = "Verify SideBaMenu Visibility")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Case Description: Verify SideBaMenu Visibility")
    @Story("Story Name: Validate the homePage")
    void testSideBaMenuVisibility(String auTestURL, String userName, String password) {
        this.waitforInterface = new WaitImp(webDriverInstance);
        assertThat(waitforInterface.waitTillWebElementToBeClickable(homePO.getLeftSideBarVerticalMenu()).isDisplayed());
    }

    @Test(dependsOnMethods = "testSideBaMenuVisibility", groups = "sidebar",dataProvider = "Authentication",description = "Verify SideBaMenu Options Visibility")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Case Description: Verify SideBaMenu Options Visibility")
    @Story("Story Name: Validate the homePage")
    void testSideBarOptionsVisibility(String auTestURL, String userName, String password) {
        this.waitforInterface = new WaitImp(webDriverInstance);
        assertThat(waitforInterface.waitTillWebElementToBeClickable(homePO.getLeftSideBarVerticalMenuItems()).isDisplayed());
        List x=new ArrayList();
        Iterator li=x.iterator();
    }


}
