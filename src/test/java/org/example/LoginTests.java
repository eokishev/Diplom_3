package org.example;

import io.qameta.allure.junit4.DisplayName;
import org.example.page.object.ForgotPasswordPage;
import org.example.page.object.HomePage;
import org.example.page.object.LoginPage;
import org.example.page.object.RegisterPage;
import org.junit.Assert;
import org.junit.Test;

import static org.example.Constants.*;
import static org.example.api.user.UserApi.createUser;

public class LoginTests extends BaseTest {
    @Test
    @DisplayName("Вход по кнопке Войти в аккаунт на главной")
    public void loginUserWithEnterToAccountButton() {
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        createUser();
        homePage.openHomePage();
        homePage.openLoginPageViaEnterToAccount();
        loginPage.login(USER_EMAIL, USER_PASSWORD);
        homePage.checkPageLoad();
        Assert.assertEquals(BASE_URL, driver.getCurrentUrl());
        Assert.assertTrue(homePage.checkButtonPlaceOrder());
    }

    @Test
    @DisplayName("Вход по кнопке Личный кабинет")
    public void loginUserWithPersonalAccountButton() {
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        createUser();
        homePage.openHomePage();
        homePage.openPersonalAccount();
        loginPage.login(USER_EMAIL, USER_PASSWORD);
        homePage.checkPageLoad();
        Assert.assertEquals(BASE_URL, driver.getCurrentUrl());
        Assert.assertTrue(homePage.checkButtonPlaceOrder());
    }

    @Test
    @DisplayName("Вход по кнопке на форме регистрации")
    public void loginUserFromRegistrationPage() {
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        RegisterPage registerPage = new RegisterPage(driver);
        createUser();
        homePage.openHomePage();
        homePage.openLoginPageViaEnterToAccount();
        loginPage.openRegisterPage();
        registerPage.openLoginPage();
        loginPage.login(USER_EMAIL, USER_PASSWORD);
        homePage.checkPageLoad();
        Assert.assertEquals(BASE_URL, driver.getCurrentUrl());
        Assert.assertTrue(homePage.checkButtonPlaceOrder());
    }

    @Test
    @DisplayName("Вход по кнопке на форме восстановления пароля")
    public void loginUserFromForgotPasswordPage() {
        createUser();
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
        homePage.openHomePage();
        homePage.openLoginPageViaEnterToAccount();
        loginPage.openRecoverPasswordPage();
        forgotPasswordPage.openLoginPage();
        loginPage.login(USER_EMAIL, USER_PASSWORD);
        homePage.checkPageLoad();
        Assert.assertEquals(BASE_URL, driver.getCurrentUrl());
        Assert.assertTrue(homePage.checkButtonPlaceOrder());
    }
}
