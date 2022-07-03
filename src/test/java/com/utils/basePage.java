package com.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import runner.TestRunnerUI;

import java.time.Duration;


public class basePage extends TestRunnerUI {
    public static Duration WAIT_FOR_SECONDS = Duration.ofSeconds(15);



    public static WebElement waitForElement(WebElement elementToWaitFor){
        return waitForElement(elementToWaitFor,WAIT_FOR_SECONDS);
    }

    public static WebElement waitForElement(WebElement elementToWaitFor, Duration waitTimeInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, waitTimeInSeconds);
        return wait.until(ExpectedConditions.visibilityOf(elementToWaitFor));
    }

//    public WebDriverWait getWebDriverWait(Duration waitTime) {
//        return new WebDriverWait(driver, waitTime);
//    }





}
