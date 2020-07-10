package com.venkat.Pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PortsPage {
    private WebDriver driver;

    private WebElement searchBar() {
        return driver.findElement(By.id("searchbar"));
    }

    private WebElement clickOnSearchResult() {
        return driver.findElement(By.xpath("//*[@class='list-find-port']/li[2]/a"));
    }

    public PortsPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Enter the port name to explore")
    public PortsPage searchPort(String portName) {
        searchBar().sendKeys(portName);
        return this;
    }

    @Step("Click on result")
    public PortsPage clickOnResult() {
         clickOnSearchResult().click();
         return this;
    }

    @Step("Get Search text")
    public String getSearchBarValue() {
        return searchBar().getAttribute("value");
    }
}