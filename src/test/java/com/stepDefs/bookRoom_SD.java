package com.stepDefs;

import com.pages.bookingPage;
import com.pages.infoPage;
import com.pages.moreDetailsPage;
import com.pages.productsPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class bookRoom_SD {
    bookingPage bPage = new bookingPage();
    productsPage pPage = new productsPage();
    infoPage iPage = new infoPage();
    moreDetailsPage mDPage = new moreDetailsPage();


    @Given("I search with {string}")
    public void iSearchWith(String searchWord) throws InterruptedException {
        bPage.enterText(searchWord);
    }

    @When("I click on More Info on {string}")
    public void iClickOnMoreInfoOn(String productName) {
        pPage.clickOnProduct(productName);
    }

    @Then("I should see number of days is {string} days")
    public void iShouldSeeNumberOfDaysIsDays(String expDays) {
        iPage.verifyDays(expDays);
    }

    @And("I should see price {string}")
    public void iShouldSeePrice(String expPrice) {
        iPage.verifyPrice(expPrice);
    }

    @And("I should see contact number is displayed")
    public void iShouldSeeContactNumber() {
        iPage.verifyContactNoElement();
    }

    @When("I click on Book Online button")
    public void iClickOnBookOnlineButton() {
        iPage.clickBookOnlineBtn();
    }

    @And("I selected Double room")
    public void iSelectedDoubleRoom() {
        mDPage.selectDoubleRoom();
    }

    @And("I entered below passenger details for {string}")
    public void iEnteredBelowPassengerDetailsFor(String adult, DataTable passenger1) {
        mDPage.enterPassenger1Details(adult, passenger1);
    }

    @And("I ented below Lead contact details")
    public void iEntedBelowLeadContactDetails(DataTable leadContactDetails) {
        mDPage.enterLeadContactDeatails(leadContactDetails);
    }

    @Then("I should see expected price for Total Tour Cost for two passengers")
    public void iShouldSeeExpectedPriceForTotalTourCostForTwoPassengers() {
        mDPage.verifyTotalPrice();
    }
}

