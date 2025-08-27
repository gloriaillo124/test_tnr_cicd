package com.logwire;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class LoginTest {

    private WebDriver driver;
    private By usernameBy = By.id("user-name");
    private By passwordBy = By.id("password");
    private By loginButtonBy = By.id("login-button");
    private By errorMessageBy = By.cssSelector("[data-test='error']");

    @BeforeEach
    public void setup() {
        driver = new FirefoxDriver();
        driver.get("https://www.saucedemo.com/");
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
    public void test3() {
        WebElement usernameElement = driver.findElement(usernameBy);
        usernameElement.sendKeys("standard_user");

        WebElement passwordElement = driver.findElement(passwordBy);
        passwordElement.sendKeys("secret_sauce");

        driver.findElement(loginButtonBy).click();

        String currentURL = driver.getCurrentUrl();
        assertEquals("https://www.saucedemo.com/inventory.html", currentURL);
    }

    @Test
    @Tag("tc-002")
    public void test1() {
        WebElement usernameElement = driver.findElement(usernameBy);
        usernameElement.sendKeys("incorrect_username");

        WebElement passwordElement = driver.findElement(passwordBy);
        passwordElement.sendKeys("secret_sauce");

        driver.findElement(loginButtonBy).click();

        WebElement erreur = driver.findElement(errorMessageBy);
        assertEquals("Epic sadface: Username and password do not match any user in this service",
                     erreur.getText());
    }

    @Test
    @Tag("tc-003")
    public void test2() {
        WebElement usernameElement = driver.findElement(usernameBy);
        usernameElement.sendKeys("standard_user");

        WebElement passwordElement = driver.findElement(passwordBy);
        passwordElement.sendKeys("incorrect_password");

        driver.findElement(loginButtonBy).click();

        WebElement erreur = driver.findElement(errorMessageBy);
        assertEquals("Epic sadface: Username and password do not match any user in this service",
                     erreur.getText());
    }

    
}
