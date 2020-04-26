package com.cogmento.testcases;

import com.cogmento.pages.HomePO;
import com.cogmento.pages.LoginPO;
import com.drivers.TestBase;
import com.implementation.BrowserInteractionServiceImplementation;
import com.implementation.WaitImp;
import com.services.BrowserInteractionService;
import com.services.WaitforInterface;
import com.testdatareader.PropertiesReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LoginWorkflow extends TestBase {
    private PropertiesReader propertiesReader;
    private LoginPO loginPO;
    private HomePO homePO;
    private static Logger logger = LogManager.getLogger(LoginWorkflow.class.getName());

    private WaitforInterface waitforInterface;
    private BrowserInteractionService browserInteractionService;

    public LoginWorkflow() {
        super();
        this.propertiesReader = new PropertiesReader();
        this.loginPO = new LoginPO();
        this.homePO=new HomePO();
    }

    @DataProvider(name = "credetials")
    private Object[][] datafortest() {
        Object[][] test = {
                {propertiesReader.getAut(), propertiesReader.getUserName(), propertiesReader.getPassword(), true}
                , {propertiesReader.getAut(), propertiesReader.getUserName(), (propertiesReader.getPassword()) + "2", false}
        };
        return test;
    }

    @Test(alwaysRun = true,dataProvider = "credetials")
    void launchAutCogmeto(String auTestURL,String username,String password,boolean flg) {
        webDriverInstance.get(auTestURL);
        assertThat(webDriverInstance.getCurrentUrl().compareToIgnoreCase(auTestURL));
    }

    @Test(dependsOnMethods = "launchAutCogmeto", groups = "fields")
    void testLoginFieldVisibility() {
        this.waitforInterface = new WaitImp(webDriverInstance);
        assertThat(waitforInterface.waitTillWebElementToBeClickable(loginPO.getUserIdfield()).isDisplayed());
    }


    @Test(dependsOnMethods = "testLoginFieldVisibility", groups = "fields")
    void testPasswordFieldVisibility() {
        this.browserInteractionService = new BrowserInteractionServiceImplementation(webDriverInstance);
        assertThat(waitforInterface.waitTillWebElementToBeClickable(loginPO.getPasswordField()).isDisplayed());
    }

    @Test(dependsOnMethods = "testPasswordFieldVisibility", groups = "fields")
    void testCRMProUserButtonVisibility() {
        this.browserInteractionService = new BrowserInteractionServiceImplementation(webDriverInstance);
        assertThat(waitforInterface.waitTillWebElementToBeClickable(loginPO.getCheckFreeCRMorCRMProUserButton()).isDisplayed());
    }

    @Test(dependsOnMethods = "testCRMProUserButtonVisibility", groups = "fields")
    void testSignUpVisibility() {
        this.browserInteractionService = new BrowserInteractionServiceImplementation(webDriverInstance);
        assertThat(waitforInterface.waitTillWebElementToBeClickable(loginPO.getResgisterOrSignUpButton()).isDisplayed());
    }

    @Test(dataProvider = "credetials",groups = "fields")
    void testLogin(String userName,String password,Boolean flg) {
        this.browserInteractionService = new BrowserInteractionServiceImplementation(webDriverInstance);
        this.browserInteractionService.insertIntoField(loginPO.getUserIdfield(), userName);
        this.browserInteractionService.insertIntoField(loginPO.getPasswordField(), password);
        this.browserInteractionService.clickTheButton(loginPO.getLoginButton());
        assertThat(waitforInterface.webDriverWaitTillVisibilityOfBy(homePO.getUserNamedisplay()).getText().equalsIgnoreCase("Veer Neti")).isEqualTo(flg);
    }

}
