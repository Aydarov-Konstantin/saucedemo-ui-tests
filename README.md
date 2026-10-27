<div align="center">

# Автоматизация UI-тестирования SauceDemo

Проект для портфолио с автоматизированным сквозным тестированием интернет-магазина [SauceDemo](https://www.saucedemo.com/)

[![Java](https://img.shields.io/badge/Java-17-ED8B00?logo=openjdk&logoColor=white)](https://www.oracle.com/java/)
[![Selenium](https://img.shields.io/badge/Selenium-4.18.1-43B02A?logo=selenium&logoColor=white)](https://www.selenium.dev/)
[![JUnit](https://img.shields.io/badge/JUnit-5.11.3-25A162?logo=junit5&logoColor=white)](https://junit.org/junit5/)
[![Maven](https://img.shields.io/badge/Maven-3.9%2B-C71A36?logo=apachemaven&logoColor=white)](https://maven.apache.org/)
[![Allure](https://img.shields.io/badge/Allure_Report-3.17.0-FFB400?logo=allure&logoColor=white)](https://allurereport.org/)

</div>

> Проект проверяет успешное оформление покупки в Google Chrome: авторизацию, добавление товара в корзину, оформление и подтверждение заказа.

## Стек технологий

- Java 17
- Selenium WebDriver
- JUnit 5
- Maven
- WebDriverManager
- Allure Report
- Page Object Model

## Проверяемые сценарии

1. `Успешное оформление покупки`

   Открытие SauceDemo, авторизация, добавление Sauce Labs Backpack в корзину, оформление и проверка сообщения `Thank you for your order!`.

2. `Покупка нескольких товаров`

   Добавление Sauce Labs Backpack и Sauce Labs Bike Light, оформление заказа и подтверждение покупки.

3. `Неверные учётные данные`

   Проверка сообщения об ошибке при неверном пароле.

4. `Заблокированный пользователь`

   Проверка запрета входа для учётной записи `locked_out_user`.

5. `Сортировка товаров по цене`

   Проверка порядка цен после выбора сортировки от меньшей цены к большей.

6. `Удаление товара из корзины`

   Проверка удаления Sauce Labs Backpack и обновления количества товаров в корзине.

7. `Валидация оформления заказа`

   Проверка сообщения об ошибке при попытке продолжить без имени.

## Структура проекта

```text
src/test/java/com/saucedemo
├── pages/        # Page Object-классы для экранов сценария
├── tests/        # Базовая настройка и сквозной тест
└── utils/        # Конфигурация и управление жизненным циклом WebDriver

src/test/resources
└── allure.properties
```

## Требования

- JDK 17 или новее
- Maven 3.9 или новее
- Google Chrome

WebDriverManager автоматически загружает и настраивает ChromeDriver.

## Запуск тестов

После загрузки репозитория выполните:

```bash
mvn clean test
```

Браузер запускается в режиме инкогнито, открывается на весь экран и закрывается после выполнения теста.

## Allure-отчёт

После запуска теста откройте локальный сервер с Allure-отчётом:

```bash
mvn allure:serve
```

Для генерации статических файлов отчёта выполните:

```bash
mvn allure:report
```

## Непрерывная интеграция

GitHub Actions запускает весь набор UI-тестов при каждом обновлении репозитория, запросе на объединение и ручном запуске workflow. Результаты Allure сохраняются в артефактах запуска и доступны для скачивания из раздела Actions.

## Детали реализации

- Тест построен по паттерну Page Object Model: локаторы и действия со страницами отделены от тестовой логики.
- `BaseTest` управляет настройкой и закрытием браузера для каждого теста.
- `DriverFactory` настраивает Chrome, таймауты и жизненный цикл WebDriver.
- Аннотации Allure добавляют в отчёт информацию о функциональности, пользовательской истории, критичности и сценарии.

## Результат тестирования

<div align="center">

| Тесты | Результат | Ошибки | Падения |
| :---: | :-------: | :----: | :------: |
| 7 | ✅ Пройдено | 0 | 0 |

</div>

Последний запуск завершён успешно.

## Автор

Konstantin
