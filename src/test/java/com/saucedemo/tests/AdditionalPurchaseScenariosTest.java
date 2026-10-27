package com.saucedemo.tests;

import com.saucedemo.pages.CartPage;
import com.saucedemo.pages.CheckoutStepOnePage;
import com.saucedemo.pages.InventoryPage;
import com.saucedemo.pages.LoginPage;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Feature("Работа с каталогом и оформлением заказа")
public class AdditionalPurchaseScenariosTest extends BaseTest {

    @Test
    @DisplayName("Пользователь видит ошибку при неверных учётных данных")
    @Story("Авторизация")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Проверяет сообщение об ошибке после входа с неверным паролем.")
    void shouldShowErrorForInvalidCredentials() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.login("standard_user", "wrong_password");

        assertTrue(loginPage.getErrorMessage().contains("Username and password do not match"));
    }

    @Test
    @DisplayName("Заблокированный пользователь не может войти")
    @Story("Авторизация")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Проверяет ограничение доступа для заблокированной учётной записи.")
    void shouldPreventLockedOutUserFromLoggingIn() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.login("locked_out_user", "secret_sauce");

        assertTrue(loginPage.getErrorMessage().contains("Sorry, this user has been locked out"));
    }

    @Test
    @DisplayName("Товары сортируются по возрастанию цены")
    @Story("Каталог товаров")
    @Severity(SeverityLevel.NORMAL)
    @Description("Проверяет сортировку товаров от минимальной цены к максимальной.")
    void shouldSortProductsByPriceLowToHigh() {
        InventoryPage inventoryPage = new LoginPage(driver).login("standard_user", "secret_sauce");

        inventoryPage.sortByPriceLowToHigh();
        List<BigDecimal> actualPrices = inventoryPage.getDisplayedPrices();
        List<BigDecimal> expectedPrices = new ArrayList<>(actualPrices);
        expectedPrices.sort(BigDecimal::compareTo);

        assertEquals(expectedPrices, actualPrices);
    }

    @Test
    @DisplayName("Пользователь может удалить товар из корзины")
    @Story("Корзина")
    @Severity(SeverityLevel.NORMAL)
    @Description("Проверяет удаление добавленного товара и обновление корзины.")
    void shouldRemoveProductFromCart() {
        InventoryPage inventoryPage = new LoginPage(driver).login("standard_user", "secret_sauce");
        inventoryPage.addBackpackToCart();

        CartPage cartPage = inventoryPage.goToCart();
        cartPage.removeBackpack();

        assertEquals(0, cartPage.getItemsCount());
    }

    @Test
    @DisplayName("Оформление заказа требует обязательные данные")
    @Story("Оформление заказа")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Проверяет сообщение об ошибке при попытке продолжить без имени.")
    void shouldRequireFirstNameAtCheckout() {
        InventoryPage inventoryPage = new LoginPage(driver).login("standard_user", "secret_sauce");
        inventoryPage.addBackpackToCart();
        CheckoutStepOnePage checkoutStepOnePage = inventoryPage.goToCart().clickCheckout();

        checkoutStepOnePage.submitEmptyForm();

        assertTrue(checkoutStepOnePage.getErrorMessage().contains("First Name is required"));
    }

    @Test
    @DisplayName("Пользователь может оформить заказ из двух товаров")
    @Story("Оформление заказа")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Проверяет полный путь покупки двух товаров и итоговое подтверждение.")
    void shouldCompletePurchaseWithMultipleProducts() {
        InventoryPage inventoryPage = new LoginPage(driver).login("standard_user", "secret_sauce");
        inventoryPage.addBackpackToCart();
        inventoryPage.addBikeLightToCart();

        CartPage cartPage = inventoryPage.goToCart();
        assertEquals(2, cartPage.getItemsCount());

        CheckoutStepOnePage checkoutStepOnePage = cartPage.clickCheckout();
        checkoutStepOnePage.fillCheckoutInfo("John", "Doe", "12345");

        assertTrue(checkoutStepOnePage.clickContinue().clickFinish()
                .getConfirmationMessage().contains("Thank you for your order!"));
    }
}
