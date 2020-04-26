package com.cogmento.testcases;

import com.cogmento.pages.HomePO;
import com.cogmento.pages.LoginPO;
import com.drivers.TestBase;
import com.implementation.BrowserInteractionServiceImplementation;
import com.implementation.WaitImp;
import com.services.BrowserInteractionService;
import com.services.WaitforInterface;
import com.testdatareader.CommaSeperatedValueReader;
import com.utilities.GetHelp;
import com.utilities.TestHelper;
import io.qameta.allure.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Iterator;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class HomePageNavBarUsingCSV extends TestBase {
    private static Logger logger = LogManager.getLogger(HomePageNavBarUsingExcelSpreadSheet.class.getName( ));
    private HomePO homePO;
    private LoginPO loginPO;
    private WaitforInterface waitforInterface;
    private BrowserInteractionService browserInteractionService;
    private TestHelper testHelper;
    private CommaSeperatedValueReader commaSeperatedValueReader;

    public HomePageNavBarUsingCSV() {
        super( );
        this.testHelper = new GetHelp( );
        this.commaSeperatedValueReader = new CommaSeperatedValueReader( );
        this.homePO = new HomePO( );
        this.loginPO = new LoginPO( );
        logger.info("Constructing all local variables. This will not instantiate Wait");
    }

    @DataProvider(name = "Authentication")
    public Iterator<Object[]> credentials() throws IOException {
        Iterator iterator = this.commaSeperatedValueReader.getCommaSeperatedValueReader( ).iterator( );
        return iterator;
    }


    @Link("https://ui.cogmento.com/ ")
    @Test(alwaysRun = true, dataProvider = "Authentication", description = "verify the url and login credentials")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Test Case Description: Verify the application under test and  login credentials")
    @Story("Story Name: Validate the homePage")
    void launchAutCogmeto(Object[] objects) {
        webDriverInstance.get(String.valueOf(objects[0]));
        assertThat(webDriverInstance.getCurrentUrl( ).compareToIgnoreCase(String.valueOf(objects[0])));

    }


    @Test(dependsOnMethods = "launchAutCogmeto", dataProvider = "Authentication", groups = "navbar", description = "verify UserName from the profile field is visible")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Case Description: verify UserName from the profile field is visible")
    @Story("Story Name: Validate the homePage")
    void testHomeNavBarUserNameVisibility(Object[] objects) {
        this.waitforInterface = new WaitImp(webDriverInstance);
        this.browserInteractionService = new BrowserInteractionServiceImplementation(webDriverInstance);
        this.browserInteractionService.insertIntoField(loginPO.getUserIdfield( ), String.valueOf(objects[1]));
        this.browserInteractionService.insertIntoField(loginPO.getPasswordField( ), String.valueOf(objects[2]));
        this.browserInteractionService.clickByLocator(loginPO.getLoginButton( ));
        if (Boolean.parseBoolean(String.valueOf(objects[5]))) {
            this.browserInteractionService.clickByLocator(loginPO.getLoginButton( ));
            assertThat(waitforInterface.webDriverWaitTillVisibilityOfBy(homePO.getUserNamedisplay( )).isDisplayed( ));
            WebElement dropDown = waitforInterface.webDriverWaitTillVisibilityOfBy(homePO.getNavbarDropDown( ));
            this.browserInteractionService.clickWebElement(dropDown);
            assertThat(waitforInterface.webDriverWaitTillVisibilityOfBy(homePO.getNavbarDropDownMenuItem( )).isDisplayed( ));
            this.browserInteractionService.clickWebElement(waitforInterface.webDriverWaitTillVisibilityOfBy(homePO.getNavbarDropDownMenuItemLogout( )));
        } else {
            assertThat(waitforInterface.webDriverWaitTillVisibilityOfBy(loginPO.getNegativeMessage( )).getText().equalsIgnoreCase( "Something went wrong...\n" +
                    "Invalid login"));

        }
    }



}
