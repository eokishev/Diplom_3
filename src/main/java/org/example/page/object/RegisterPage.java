package org.example.page.object;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterPage {
    private final WebDriver driver;
    private final By textRegistration = By.xpath(".//h2[text() = 'Регистрация']");
    private final By inputName = By.xpath(".//label[text() = 'Имя']/../input");
    private final By inputEmail = By.xpath(".//label[text() = 'Email']/../input");
    private final By inputPassword = By.xpath(".//input[@type = 'password']");
    private final By buttonRegister = By.xpath(".//button[text()  = 'Зарегистрироваться']");
    private final By buttonEnter = By.className("Auth_link__1fOlj");
    private final By invalidPassword = By.xpath(".//p[text() = 'Некорректный пароль']");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Ввести name, email, пароль и зарегистрироваться")
    public void register(String name, String email, String password) {
        driver.findElement(inputName).sendKeys(name);
        driver.findElement(inputEmail).sendKeys(email);
        driver.findElement(inputPassword).sendKeys(password);
        driver.findElement(buttonRegister).click();
    }

    @Step("Перейти на страницу Вход через кнопку Войти")
    public void openLoginPage() {
        LoginPage loginPage = new LoginPage(driver);
        driver.findElement(buttonEnter).click();
        loginPage.checkPageLoad();
    }

    @Step("Проверить, что отображается текст Некорректный пароль")
    public boolean checkMessageInvalidPassword() {
        return driver.findElement(invalidPassword).isDisplayed();
    }

    @Step("Проверить, что загрузилась страница регистрации")
    public void checkPageLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(textRegistration));
    }
}
