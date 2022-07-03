package com.pages;

import com.utils.basePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

public class infoPage extends basePage {

    @FindBy(id = "price-pin_riviera-days-num")
    private WebElement days;

    @FindBy(id = "price-pin_price_non_newmarket")
    private WebElement price;

    @FindBy(id = "supplier-phone")
    private WebElement phone;

    @FindBy(css = ".nbf_button .nbf_tpl_pms_book_button")
    private List<WebElement> bookOnlineBtn;

    private WebDriverWait wait;

    public infoPage() {
        PageFactory.initElements(driver, this);
    }

    public void verifyDays(String expDays) {
        waitForElement(days);
        Assert.assertEquals(expDays, days.getText());
    }

    public void verifyPrice(String expPrice) {
        waitForElement(price);


        String iPrice = price.getText().replace("*\n" +
                "per person", "");
// In Safari, above code is failing. to make it fix, below code I added.
        iPrice = iPrice.replace("*per person", "");
        Assert.assertEquals(iPrice, expPrice);
    }

    public void verifyContactNoElement() {
        waitForElement(phone);
        Assert.assertTrue(phone.isDisplayed());
    }

    public void clickBookOnlineBtn() {
        bookOnlineBtn.get(0).click();
    }
}

