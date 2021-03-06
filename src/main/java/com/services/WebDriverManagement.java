package com.services;

import org.openqa.selenium.WebDriver;

public interface WebDriverManagement {
    String failureScreenshots(String methodName);

     WebDriver setupWebDriverInstance(String headlessCheck) ;

    void quitWebDriverInstance();

}
