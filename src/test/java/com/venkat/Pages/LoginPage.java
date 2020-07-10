package com.venkat.Pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    private WebDriver driver;

    private WebElement username() {
        return driver.findElement(By.id("input_username"));
    }

    private WebElement password() {
        return driver.findElement(By.id("input_password"));
    }

    private WebElement loginButton() {
        return driver.findElement(By.id("login_btn"));
    }

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Enter username")
    public LoginPage enterUsername(String username) {
        elementExists(username());
        username().sendKeys(username);
        return this;
    }

    @Step("Enter user password")
    public LoginPage enterPassword(String password) {
        elementExists(password());
        password().sendKeys(password);
        return this;
    }

    @Step("Click on login button")
    public HomePage clickOnLoginButton() {
        elementExists(loginButton());
        loginButton().click();
        return new HomePage(driver);
    }

    @Step("Validate element presence")
    private boolean elementExists(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}