package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CartPage {
    private static final By REMOVE_BACKPACK_BUTTON = By.id("remove-sauce-labs-backpack");
    private final WebDriver driver;

    @FindBy(id = "checkout")
    private WebElement checkoutButton;

    @FindBy(className = "cart_item")
    private List<WebElement> cartItems;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public CheckoutStepOnePage clickCheckout() {
        checkoutButton.click();
        return new CheckoutStepOnePage(driver);
    }

    public void removeBackpack() {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(webDriver -> {
            try {
                webDriver.findElement(REMOVE_BACKPACK_BUTTON).click();
                return true;
            } catch (StaleElementReferenceException exception) {
                return false;
            }
        });
    }

    public int getItemsCount() {
        return cartItems.size();
    }
}
