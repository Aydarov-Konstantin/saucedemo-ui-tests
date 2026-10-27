package com.saucedemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.math.BigDecimal;
import java.util.List;

public class InventoryPage {
    private final WebDriver driver;

    @FindBy(id = "add-to-cart-sauce-labs-backpack")
    private WebElement addBackpackToCartButton;

    @FindBy(id = "add-to-cart-sauce-labs-bike-light")
    private WebElement addBikeLightToCartButton;

    @FindBy(className = "shopping_cart_link")
    private WebElement cartLink;

    @FindBy(className = "product_sort_container")
    private WebElement sortDropdown;

    @FindBy(className = "inventory_item_price")
    private List<WebElement> productPrices;

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void addBackpackToCart() {
        addBackpackToCartButton.click();
    }

    public void addBikeLightToCart() {
        addBikeLightToCartButton.click();
    }

    public void sortByPriceLowToHigh() {
        new Select(sortDropdown).selectByValue("lohi");
    }

    public List<BigDecimal> getDisplayedPrices() {
        return productPrices.stream()
                .map(WebElement::getText)
                .map(price -> price.replace("$", ""))
                .map(BigDecimal::new)
                .toList();
    }

    public CartPage goToCart() {
        cartLink.click();
        return new CartPage(driver);
    }
}
