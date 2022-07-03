package com.pages;

import com.utils.basePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class productsPage extends basePage {

    @FindBy(id = "products-found")
    private WebElement numberProducts;

    private WebDriverWait wait;

    public productsPage() {
        PageFactory.initElements(driver, this);
    }

    public void clickOnProduct(String productName) {
        waitForElement(numberProducts);
        int iNoofProds = Integer.parseInt(numberProducts.getText().replace("Products found", "").trim());

        for (int i = 1; i < iNoofProds; i++) {
            String product = "iterator_" + i + "_product_custom_product-text";
            String moreInfo = "iterator_" + i + "_product_custom_more-info-button";

            if (driver.findElement(By.id(product)).getText().equals(productName)) {
                driver.findElement(By.id(moreInfo)).click();
                break;
            }
        }
    }
}
