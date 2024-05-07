package org.example.page.object;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import static org.example.Constants.BASE_URL;
import static org.example.Constants.INSPECTED_CLASS;

public class HomePage {
    private final WebDriver driver;
    private final By buttonsBuns = By.xpath(".//span[text() = 'Булки']");
    private final By divBuns = By.xpath(".//span[text() = 'Булки']/..");
    private final By buttonsSauces = By.xpath(".//span[text() = 'Соусы']");
    private final By divSauces = By.xpath(".//span[text() = 'Соусы']/..");
    private final By buttonsFillings = By.xpath(".//span[text() = 'Начинки']");
    private final By divFillings = By.xpath(".//span[text() = 'Начинки']/..");
    private final By buttonPersonalAccount = By.xpath(".//p[text() = 'Личный Кабинет']");
    private final By headlineAssembleBurger = By.xpath(".//h1[text() = 'Соберите бургер']");
    private final By buttonEnterToAccount = By.xpath(".//button[text()='Войти в аккаунт']");
    private final By buttonPlaceOrder = By.xpath(".//button[text()= 'Оформить заказ']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Перейти на главную страницу https://stellarburgers.nomoreparties.site/")
    public void openHomePage() {
        driver.get(BASE_URL);
        checkPageLoad();
    }

    @Step("Кликнуть на главной странице на Личный кабинет")
    public void openPersonalAccount() {
        driver.findElement(buttonPersonalAccount).click();
        if (driver.getCurrentUrl().equals(BASE_URL + "login")) {
            LoginPage loginPage = new LoginPage(driver);
            loginPage.checkPageLoad();
        } else {
            ProfilePage profilePage = new ProfilePage(driver);
            profilePage.checkPageLoad();
        }
    }

    @Step("Открыть страницу Вход, через кнопку Войти в аккаунт")
    public void openLoginPageViaEnterToAccount() {
        LoginPage loginPage = new LoginPage(driver);
        driver.findElement(buttonEnterToAccount).click();
        loginPage.checkPageLoad();
    }

    @Step("Выбрать раздел Булки в конструкторе")
    public void openSectionBuns() {
        driver.findElement(buttonsBuns).click();
    }

    @Step("Проверить класс выбранного раздела Булки")
    public boolean checkCurrentClassBuns() {
        List<String> classList = Arrays.asList(driver.findElement(divBuns).getAttribute("class").split(" "));
        return classList.contains(INSPECTED_CLASS);
    }

    @Step("Выбрать раздел Соусы в конструкторе")
    public void openSectionSauces() {
        driver.findElement(buttonsSauces).click();
    }

    @Step("Проверить класс выбранного раздела Соусы")
    public boolean checkCurrentClassSauces() {
        List<String> classList = Arrays.asList(driver.findElement(divSauces).getAttribute("class").split(" "));
        return classList.contains(INSPECTED_CLASS);
    }

    @Step("Выбрать раздел Начинки в конструкторе")
    public void openSectionFillings() {
        driver.findElement(buttonsFillings).click();
    }

    @Step("Проверить класс выбранного раздела Начинки")
    public boolean checkCurrentClassFillings() {
        List<String> classList = Arrays.asList(driver.findElement(divFillings).getAttribute("class").split(" "));
        return classList.contains(INSPECTED_CLASS);
    }

    @Step("Проверить, что главная страница загружена")
    public void checkPageLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(headlineAssembleBurger));
    }

    @Step("Проверить, что есть кнопка Оформить заказ")
    public boolean checkButtonPlaceOrder() {
        return driver.findElement(buttonPlaceOrder).isDisplayed();
    }
}