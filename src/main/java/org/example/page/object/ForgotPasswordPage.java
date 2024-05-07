package org.example.page.object;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ForgotPasswordPage {
    private final WebDriver driver;
    private final By textPasswordRecovery = By.xpath(".//h2[text() = 'Восстановление пароля']");
    private final By buttonEnter = By.className("Auth_link__1fOlj");

    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Перейти на страницу Вход через кнопку Войти")
    public void openLoginPage() {
        LoginPage loginPage = new LoginPage(driver);
        driver.findElement(buttonEnter).click();
        loginPage.checkPageLoad();
    }

    @Step("Проверить, что загрузилась страница восстановления пароля")
    public void checkPageLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(textPasswordRecovery));
    }
}
