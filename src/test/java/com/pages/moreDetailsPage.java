package com.pages;

import com.utils.basePage;
import io.cucumber.datatable.DataTable;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;
import java.util.Map;

public class moreDetailsPage extends basePage {

    @FindBy(css = ".nbf_fancy_nbf_tpl_pms_continue")
    private WebElement continueBtn;

    @FindBy(css = ".nbf_button .nbf_tpl_pms_button")
    private WebElement continueBtn1;

    @FindBy(id = "room-1673581-numselect")
    private WebElement doubleRoomSelectCh;

    @FindBy(id = "room-1673762-numselect")
    private WebElement doubleRoomSelectSa;

    @FindBy(css = ".nbf_fancy_nbf_tpl_pms_book_room")
    private WebElement selectYourRoomandContBtn;

    @FindBy(id = "pax-a-title-1")
    private WebElement title1;

    @FindBy(id = "pax-a-first-1")
    private WebElement first1;

    @FindBy(id = "pax-a-last-1")
    private WebElement last1;

    @FindBy(id = "pax-a-dobd-1")
    private WebElement dobDate1;

    @FindBy(id = "pax-a-dobm-1")
    private WebElement dobMonth1;

    @FindBy(id = "pax-a-doby-1")
    private WebElement dobYear1;

    @FindBy(id = "pax-a-title-2")
    private WebElement title2;

    @FindBy(id = "pax-a-first-2")
    private WebElement first2;

    @FindBy(id = "pax-a-last-2")
    private WebElement last2;

    @FindBy(id = "pax-a-dobd-2")
    private WebElement dobDate2;

    @FindBy(id = "pax-a-dobm-2")
    private WebElement dobMonth2;

    @FindBy(id = "pax-a-doby-2")
    private WebElement dobYear2;

    @FindBy(id = "contact-mobile")
    private WebElement contactMobile;

    @FindBy(id = "contact-email")
    private WebElement contactEmail;

    @FindBy(id = "contact-address1")
    private WebElement address1;

    @FindBy(id = "contact-address2")
    private WebElement address2;

    @FindBy(id = "contact-city")
    private WebElement city;

    @FindBy(id = "contact-postcode")
    private WebElement postCode;

    @FindBy(id = "totalprice-ctr")
    private WebElement totalPrice;

    @FindBy(css = ".nbf_total > .ibecurr")
    private WebElement totalPriceAct2;

    private WebDriverWait wait;

    public String totalPriceValueExp = "";

    public moreDetailsPage() {
        PageFactory.initElements(driver, this);
    }


    public void selectDoubleRoom() {
        continueBtn.click();

        Capabilities caps = ((RemoteWebDriver) driver).getCapabilities();

        Select dropDownNumSelect = null;
        if (caps.getBrowserName().contains("chrome")) {
            waitForElement(doubleRoomSelectCh);
            dropDownNumSelect = new Select(doubleRoomSelectCh);
        }
        if (caps.getBrowserName().contains("Safari")) {
            waitForElement(doubleRoomSelectSa);
            dropDownNumSelect = new Select(doubleRoomSelectSa);
        }

        dropDownNumSelect.selectByValue("1");
        selectYourRoomandContBtn.click();
    }

    public void enterPassenger1Details(String adult, DataTable passenger1) {
        waitForElement(title1);
        if (adult.equals("Adult 1")) {
            List<Map<String, String>> passengerDetails1 = passenger1.asMaps(String.class, String.class);

            Select dropDownTitle1 = new Select(title1);
            dropDownTitle1.selectByValue(passengerDetails1.get(0).get("Title"));
            first1.sendKeys(passengerDetails1.get(0).get("FirstName"));
            last1.sendKeys(passengerDetails1.get(0).get("LastName"));
            Select dob_d_1 = new Select(dobDate1);
            Select dob_m_1 = new Select(dobMonth1);
            Select dob_y_1 = new Select(dobYear1);
            String[] dob1 = passengerDetails1.get(0).get("DOB").split("/");

            dob_d_1.selectByValue(dob1[0]);
            dob_m_1.selectByValue(dob1[1]);
            dob_y_1.selectByValue(dob1[2]);
        } else {
            List<Map<String, String>> passengerDetails2 = passenger1.asMaps(String.class, String.class);

            Select dropDownTitle2 = new Select(title2);
            dropDownTitle2.selectByValue(passengerDetails2.get(0).get("Title"));
            first2.sendKeys(passengerDetails2.get(0).get("FirstName"));
            last2.sendKeys(passengerDetails2.get(0).get("LastName"));
            Select dob_d_2 = new Select(dobDate2);
            Select dob_m_2 = new Select(dobMonth2);
            Select dob_y_2 = new Select(dobYear2);
            String[] dob2 = passengerDetails2.get(0).get("DOB").split("/");

            dob_d_2.selectByValue(dob2[0]);
            dob_m_2.selectByValue(dob2[1]);
            dob_y_2.selectByValue(dob2[2]);
        }

    }

    public void enterLeadContactDeatails(DataTable leadContactDetails) {
        List<Map<String, String>> leadContactDtails = leadContactDetails.asMaps(String.class, String.class);
        contactMobile.sendKeys(leadContactDtails.get(0).get("MobinePhoneNumber"));
        contactEmail.sendKeys(leadContactDtails.get(0).get("EmailAddress"));
        address1.sendKeys(leadContactDtails.get(0).get("AddressLine1"));
        address2.sendKeys(leadContactDtails.get(0).get("AddressLine2"));
        city.sendKeys(leadContactDtails.get(0).get("City"));
        postCode.sendKeys(leadContactDtails.get(0).get("PostCode"));
        totalPriceValueExp = totalPrice.getText().replace("SUBTOTAL", "").trim();
        System.out.println(totalPriceValueExp);
        continueBtn1.submit();

    }

    public void verifyTotalPrice() {
        waitForElement(totalPriceAct2);
        System.out.println(totalPriceAct2.getText());
        Assert.assertEquals(totalPriceValueExp, totalPriceAct2.getText());
    }
}
