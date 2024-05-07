package org.example;

import io.qameta.allure.junit4.DisplayName;
import org.example.page.object.HomePage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

public class ConstructorTests extends BaseTest {

    @Test
    @DisplayName("Переход к разделу по клику на «Булки»")
    public void openSectionBuns() {
        HomePage homePage = new HomePage(driver);
        homePage.openHomePage();
        homePage.openSectionSauces();
        homePage.openSectionBuns();
        Assert.assertTrue(homePage.checkCurrentClassBuns());
    }

    @Test
    @DisplayName("Переход к разделу по клику на «Соусы»")
    public void openSectionSauces() {
        HomePage homePage = new HomePage(driver);
        homePage.openHomePage();
        homePage.openSectionSauces();
        Assert.assertTrue(homePage.checkCurrentClassSauces());
    }

    @Test
    @DisplayName("Переход к разделу по клику на «Начинки»")
    public void openSectionFillings() {
        HomePage homePage = new HomePage(driver);
        homePage.openHomePage();
        homePage.openSectionFillings();
        Assert.assertTrue(homePage.checkCurrentClassFillings());
    }

    @After
    public void deleteUserAndCloseBrowser() {
        driver.quit();
    }
}
