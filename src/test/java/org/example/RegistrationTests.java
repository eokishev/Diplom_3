package org.example;

import io.qameta.allure.junit4.DisplayName;
import org.example.page.object.HomePage;
import org.example.page.object.LoginPage;
import org.example.page.object.RegisterPage;
import org.junit.Assert;
import org.junit.Test;

import static org.example.Constants.*;

public class RegistrationTests extends BaseTest {

    @Test
    @DisplayName("Успешная регистрация нового пользователя")
    public void registerNewUser() {
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        RegisterPage registerPage = new RegisterPage(driver);
        homePage.openHomePage();
        homePage.openLoginPageViaEnterToAccount();
        loginPage.openRegisterPage();
        registerPage.register(USER_NAME, USER_EMAIL, USER_PASSWORD);
        loginPage.checkPageLoad();
        Assert.assertEquals(BASE_URL + "login", driver.getCurrentUrl());
        Assert.assertTrue(loginPage.checkTextEnter());
    }

    @Test
    @DisplayName("Ошибка при вводе некорректного пароля")
    public void registerNewUserWithWrongPassword() {
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        RegisterPage registerPage = new RegisterPage(driver);
        homePage.openHomePage();
        homePage.openLoginPageViaEnterToAccount();
        loginPage.openRegisterPage();
        registerPage.register(USER_NAME, USER_EMAIL, WRONG_USER_PASSWORD);
        Assert.assertTrue(registerPage.checkMessageInvalidPassword());
    }
}
