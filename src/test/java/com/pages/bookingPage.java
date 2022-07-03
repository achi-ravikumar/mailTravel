package com.pages;

import com.utils.basePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class bookingPage extends basePage {
    @FindBy(how = How.NAME, name = "searchtext")
    private WebElement searchBox;

    @FindBy(css = "#freetext_search_form .nbf_button > .nbf_button")
    private WebElement searchSubmitBtn;

    @FindBy(id = "onetrust-accept-btn-handler")
    private WebElement acceptCookies;

    private WebDriverWait wait;
    public static Duration WAIT_FOR_SECONDS = Duration.ofSeconds(15);

    public bookingPage() {
        PageFactory.initElements(driver, this);
    }

    public void acceptCookies() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, WAIT_FOR_SECONDS);

        driver.getWindowHandles();
        waitForElement(acceptCookies);
        Thread.sleep(1000);//Can use Implicit wait here also. Below line.
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait.until(ExpectedConditions.elementToBeClickable(acceptCookies)).click();
    }

    public void enterText(String searchWord) throws InterruptedException {
        acceptCookies();
        waitForElement(searchBox);
        Thread.sleep(2000);
        searchBox.clear();
        searchBox.sendKeys(searchWord);
        searchSubmitBtn.click();
    }

}
