package com.venkat.Pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    private WebDriver driver;

    private WebElement loginLink() {
        return driver.findElement(By.xpath("//a[@href='/login']"));
    }

    private WebElement nclLogo() {
        return driver.findElement(By.xpath("//img[contains(@alt,'Norwegian Cruise Line')]"));
    }

    private WebElement exploreNav() {
        return driver.findElement(By.xpath("//*[@class='linkNav' and contains(text(),'Explore')]"));
    }

    private WebElement portsPage() {
        return driver.findElement(By.xpath("//*[@class='linkItem' and contains(text(),'Ports')]"));
    }

    private WebElement shoreExcursionsPage() {
        return driver.findElement(By.xpath("//*[@class='linkItem' and contains(text(),'Shore Excursions')]"));
    }

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Verify the ncl logo is displayed")
    public boolean verifyNCLLogo() {
        return elementExists(nclLogo());
    }

    @Step("Click on the login link page")
    public LoginPage clickOnLoginPageLink() {
        loginLink().click();
        return new LoginPage(driver);
    }

    @Step("Click on Explore")
    public HomePage hoverToExploreNavigation() {
        Actions action = new Actions(driver);
        action.moveToElement(exploreNav()).build().perform();
        return this;
    }

    @Step("Click on the ports page")
    public PortsPage clickOnPorts() {
        portsPage().click();
        return new PortsPage(driver);
    }

    @Step("Click on the Shore Excursions page")
    public PortsPage clickOnShoreExcursions() {
        shoreExcursionsPage().click();
        return new PortsPage(driver);
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