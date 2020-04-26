package com.cogmento.testcases;

import com.cogmento.pages.HomePO;
import com.cogmento.pages.LoginPO;
import com.drivers.TestBase;
import com.implementation.BrowserInteractionServiceImplementation;
import com.implementation.WaitImp;
import com.services.BrowserInteractionService;
import com.services.WaitforInterface;
import com.testdatareader.ExcelReader;
import com.utilities.GetHelp;
import com.utilities.TestHelper;
import io.qameta.allure.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class HomePageNavBarUsingExcelSpreadSheet extends TestBase {
    private ExcelReader excelReader;
    private HomePO homePO;
    private LoginPO loginPO;
    private WaitforInterface waitforInterface;
    private BrowserInteractionService browserInteractionService;
    private TestHelper testHelper;
    private static Logger logger = LogManager.getLogger(HomePageNavBarUsingExcelSpreadSheet.class.getName());
    public HomePageNavBarUsingExcelSpreadSheet() {
        super();
        this.testHelper=new GetHelp();
        this.excelReader = new ExcelReader();
        this.homePO = new HomePO();
        this.loginPO = new LoginPO();
        logger.info("Constructing all local variables. This will not instantiate Wait");
    }

    @DataProvider(name = "Authentication")
    public Object[][] credentials() throws Exception {
        Object[][] objects = excelReader.extractDataFromExcel( );
        return objects;
    }


    @Link("https://ui.cogmento.com/ ")
    @Test(alwaysRun = true, dataProvider = "Authentication", description = "verify the url and login credentials")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Test Case Description: Verify the application under test and  login credentials")
    @Story("Story Name: Validate the homePage")
    void launchAutCogmeto(Map<Object, Object> map) {
        webDriverInstance.get(map.get("aut").toString());
        assertThat(webDriverInstance.getCurrentUrl().compareToIgnoreCase(map.get("aut").toString()));
        this.browserInteractionService = new BrowserInteractionServiceImplementation(webDriverInstance);
        this.browserInteractionService.insertIntoField(loginPO.getUserIdfield(), map.get("username").toString());
        this.browserInteractionService.insertIntoField(loginPO.getPasswordField(), map.get("password").toString());
        this.browserInteractionService.clickByLocator(loginPO.getLoginButton());
    }


    @Test(dependsOnMethods = "launchAutCogmeto",dataProvider = "Authentication", groups = "navbar", description = "verify UserName from the profile field is visible")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Case Description: verify UserName from the profile field is visible")
    @Story("Story Name: Validate the homePage")
    void testHomeNavBarUserNameVisibility(Map<Object, Object> map) {
        this.waitforInterface = new WaitImp(webDriverInstance);
        assertThat(waitforInterface.webDriverWaitTillVisibilityOfBy(homePO.getUserNamedisplay()).isDisplayed());
    }


    @Test(dependsOnMethods = "testHomeNavBarUserNameVisibility",dataProvider = "Authentication", groups = "navbar", description = "verify UserName from the profile field is visible")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Case Description: verify UserName from the profile field is visible")
    @Story("Story Name: Validate the homePage")
    void testNavBarUserNameTextVisibility(Map<Object, Object> map) {
        this.waitforInterface = new WaitImp(webDriverInstance);
        assertThat(waitforInterface.waitFluentForTexttoBeBy(homePO.getUserNamedisplay()).compareToIgnoreCase("Veer Neti"));
    }

    @Test(dependsOnMethods = "testNavBarUserNameTextVisibility",dataProvider = "Authentication", groups = "navbar",description = "Verify Nav Bar Trail Indicator Visibility")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Case Description: Verify Nav Bar Trail Indicator Visibility")
    @Story("Story Name: Validate the homePage")
    void testNavBarTrailIndicatorVisibility(Map<Object, Object> map) {
        this.waitforInterface = new WaitImp(webDriverInstance);
        assertThat(waitforInterface.waitTillWebElementToBeClickable(homePO.getTrialIndicator()).isDisplayed());
    }

    @Test(dependsOnGroups = "navbar", groups = "sidebar",dataProvider = "Authentication",description = "Verify SideBaMenu Visibility")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Case Description: Verify SideBaMenu Visibility")
    @Story("Story Name: Validate the homePage")
    void testSideBaMenuVisibility(Map<Object, Object> map) {
        this.waitforInterface = new WaitImp(webDriverInstance);
        assertThat(waitforInterface.waitTillWebElementToBeClickable(homePO.getLeftSideBarVerticalMenu()).isDisplayed());
    }

    @Test(dependsOnMethods = "testSideBaMenuVisibility", groups = "sidebar",dataProvider = "Authentication",description = "Verify SideBaMenu Options Visibility")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Case Description: Verify SideBaMenu Options Visibility")
    @Story("Story Name: Validate the homePage")
    void testSideBarOptionsVisibility(Map<Object, Object> map) {
        this.waitforInterface = new WaitImp(webDriverInstance);
        assertThat(waitforInterface.waitTillWebElementToBeClickable(homePO.getLeftSideBarVerticalMenuItems()).isDisplayed());

    }


}
