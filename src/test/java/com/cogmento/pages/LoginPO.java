package com.cogmento.pages;

import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

@Setter
@Getter
public class LoginPO {
    private static Logger logger = LogManager.getLogger(LoginPO.class.getName());
    By userIdfield = By.name("email");
    By passwordField = By.name("password");
    By loginButton = By.cssSelector("div.submit");
    By forgotPasswordbutton = By.cssSelector("div.message:nth-of-type(1)");
    By checkFreeCRMorCRMProUserButton = By.cssSelector(".column > div.message:nth-of-type(2)");
    By resgisterOrSignUpButton = By.cssSelector(".column > div.message:nth-of-type(3)");
    By negativeMessage = By.cssSelector("div.negative.message");


}

