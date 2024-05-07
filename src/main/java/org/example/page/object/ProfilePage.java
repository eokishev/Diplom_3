package org.example.page.object;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProfilePage {
    private final WebDriver driver;
    private final By textDescription = By.className("Account_text__fZAIn");
    private final By buttonExit = By.xpath(".//button[text() = 'Выход']");
    private final By buttonDesigner = By.xpath(".//p[text() = 'Конструктор']");
    private final By buttonLogo = By.className("AppHeader_header__logo__2D0X2");

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Перейти на главную странцу через кнопку Конструктор")
    public void openHomePageThroughConstructorButton() {
        HomePage homePage = new HomePage(driver);
        driver.findElement(buttonDesigner).click();
        homePage.checkPageLoad();
    }

    @Step("Перейти на главную страницу через логотип")
    public void openHomePageThroughLogoButton() {
        HomePage homePage = new HomePage(driver);
        driver.findElement(buttonLogo).click();
        homePage.checkPageLoad();
    }

    @Step("Нажать кноку Выход на странице профиля")
    public void exit() {
        LoginPage loginPage = new LoginPage(driver);
        driver.findElement(buttonExit).click();
        loginPage.checkPageLoad();
    }

    @Step("Проверить, что загрузилась страница профиля")
    public void checkPageLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(textDescription));
    }

    @Step("Проверить, что отображается текст на странице профиля")
    public boolean checkDescription() {
        return driver.findElement(textDescription).isDisplayed();
    }
}
