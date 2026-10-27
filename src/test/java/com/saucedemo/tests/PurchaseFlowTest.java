package com.saucedemo.tests;

import com.saucedemo.pages.*;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Feature("Оформление заказа")
public class PurchaseFlowTest extends BaseTest {

    @Test
    @DisplayName("Сквозной сценарий покупки товара")
    @Story("Успешное оформление заказа")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Проверяет полный путь: логин -> добавление товара -> корзина -> оформление -> подтверждение.")
    public void testSuccessfulPurchase() {
        LoginPage loginPage = new LoginPage(driver);
        InventoryPage inventoryPage = loginPage.login("standard_user", "secret_sauce");

        inventoryPage.addBackpackToCart();

        CartPage cartPage = inventoryPage.goToCart();
        CheckoutStepOnePage checkoutStepOne = cartPage.clickCheckout();

        checkoutStepOne.fillCheckoutInfo("John", "Doe", "12345");
        CheckoutStepTwoPage checkoutStepTwo = checkoutStepOne.clickContinue();

        CheckoutCompletePage checkoutComplete = checkoutStepTwo.clickFinish();

        String confirmationMessage = checkoutComplete.getConfirmationMessage();
        assertTrue(confirmationMessage.contains("Thank you for your order!"),
                "Сообщение об успешном заказе не найдено. Получено: " + confirmationMessage);
    }
}