package com.cogmento.pages;

import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

@Setter
@Getter
public class HomePO {
    private static Logger logger = LogManager.getLogger(HomePO.class.getName());
    By topHeaderMenuLogo = By.cssSelector("div#top-header-menu > .header.item");
    By versionOfApp = By.cssSelector("div#top-header-menu > .item:nth-of-type(2)");
    By userNamedisplay = By.cssSelector("#top-header-menu .user-display");
    By trialIndicator = By.cssSelector(".right .trial-indicator");
    By searchField = By.cssSelector("[placeholder='Search']");
    By pinButton = By.cssSelector(".button .icon.pin");
    By trashCanIcon = By.cssSelector(".buttons .button.item:nth-of-type(2)");
    By settingsIcon = By.cssSelector("div[role='listbox'] > .icon.settings");
    By leftSideBarVerticalMenu = By.cssSelector("#main-nav.left.vertical");
    By leftSideBarVerticalMenuItems = By.cssSelector("#main-nav.left.vertical .item");
    By passwordField = By.name("password");
    By loginButton = By.cssSelector("div.submit");
    By forgotPasswordbutton = By.cssSelector("div.message:nth-of-type(1)");
    By checkFreeCRMorCRMProUserButton = By.cssSelector(".column > div.message:nth-of-type(2)");
    By resgisterOrSignUpButton = By.cssSelector(".column > div.message:nth-of-type(3)");




}

