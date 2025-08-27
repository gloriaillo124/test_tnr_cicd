package com.logwire;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class LoginTest {

    private WebDriver driver;
    private By usernameBy = By.id("user-name");
    private By passwordBy = By.id("password");
    private By loginButtonBy = By.id("login-button");
    private By errorMessageBy = By.cssSelector("[data-test='error']");

    @BeforeEach
    public void setup() throws MalformedURLException {

        URL hubUrl = new URL("http://192.168.1.31/:4444/wd/hub");

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setBrowserName("chrome"); 

        driver = new RemoteWebDriver(hubUrl, caps);
        driver.get("http://192.168.1.31/:4444/wd/hub");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    @Test
    @Tag("tc-001")
    public void testLoginCorrect() {
        driver.findElement(usernameBy).sendKeys("standard_user");
        driver.findElement(passwordBy).sendKeys("secret_sauce");
        driver.findElement(loginButtonBy).click();

        String currentURL = driver.getCurrentUrl();
        assertEquals("https://www.saucedemo.com/inventory.html", currentURL);
    }

    @Test
    @Tag("tc-002")
    public void testUsernameIncorrect() {
        driver.findElement(usernameBy).sendKeys("incorrect_username");
        driver.findElement(passwordBy).sendKeys("secret_sauce");
        driver.findElement(loginButtonBy).click();

        WebElement erreur = driver.findElement(errorMessageBy);
        assertEquals("Epic sadface: Username and password do not match any user in this service",
                     erreur.getText());
    }

    @Test
    @Tag("tc-003")
    public void testPasswordIncorrect() {
        driver.findElement(usernameBy).sendKeys("standard_user");
        driver.findElement(passwordBy).sendKeys("incorrect_password");
        driver.findElement(loginButtonBy).click();

        WebElement erreur = driver.findElement(errorMessageBy);
        assertEquals("Epic sadface: Username and password do not match any user in this service",
                     erreur.getText());
    }
}
