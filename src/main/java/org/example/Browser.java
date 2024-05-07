package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Browser {
    public static WebDriver getWebDriver(String browserName) {
        switch (browserName) {
            case "chrome":
                return new ChromeDriver();
            case "yandex":
                System.setProperty("webdriver.chrome.driver", "C:\\WebDriver\\bin\\yandexdriver.exe");
                return new ChromeDriver();
            default:
                throw new RuntimeException("invalid browser name");
        }
    }
}
