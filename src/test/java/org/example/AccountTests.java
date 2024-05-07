package org.example;

import io.qameta.allure.junit4.DisplayName;
import org.example.page.object.HomePage;
import org.example.page.object.LoginPage;
import org.example.page.object.ProfilePage;
import org.junit.Assert;
import org.junit.Test;

import static org.example.Constants.*;
import static org.example.api.user.UserApi.createUser;

public class AccountTests extends BaseTest {
    @Test
    @DisplayName("Переход к разделу по клику на «Личный кабинет»")
    public void openPersonalAccount() {
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);
        createUser();
        homePage.openHomePage();
        homePage.openPersonalAccount();
        loginPage.login(USER_EMAIL, USER_PASSWORD);
        homePage.checkPageLoad();
        homePage.openPersonalAccount();
        Assert.assertEquals(BASE_URL + "account/profile", driver.getCurrentUrl());
        Assert.assertTrue(profilePage.checkDescription());
    }

    @Test
    @DisplayName("Переход к разделу по клику на «Конструктор»")
    public void clickConstructorButton() {
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);
        createUser();
        homePage.openHomePage();
        homePage.openPersonalAccount();
        loginPage.login(USER_EMAIL, USER_PASSWORD);
        homePage.checkPageLoad();
        homePage.openPersonalAccount();
        profilePage.openHomePageThroughConstructorButton();
        Assert.assertEquals(BASE_URL, driver.getCurrentUrl());
        Assert.assertTrue(homePage.checkButtonPlaceOrder());
    }

    @Test
    @DisplayName("Переход к разделу по клику на «Логотип»")
    public void clickLogoButton() {
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);
        createUser();
        homePage.openHomePage();
        homePage.openPersonalAccount();
        loginPage.login(USER_EMAIL, USER_PASSWORD);
        homePage.checkPageLoad();
        homePage.openPersonalAccount();
        profilePage.openHomePageThroughLogoButton();
        Assert.assertEquals(BASE_URL, driver.getCurrentUrl());
        Assert.assertTrue(homePage.checkButtonPlaceOrder());
    }

    @Test
    @DisplayName("Выход из аккаунта")
    public void exitUser() {
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);
        createUser();
        homePage.openHomePage();
        homePage.openPersonalAccount();
        loginPage.login(USER_EMAIL, USER_PASSWORD);
        homePage.checkPageLoad();
        homePage.openPersonalAccount();
        profilePage.exit();
        Assert.assertEquals(BASE_URL + "login", driver.getCurrentUrl());
        Assert.assertTrue(loginPage.checkTextEnter());
    }
}
