package com.venkat.Pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class PortsPage {
    private WebDriver driver;

    private WebElement searchBar() {
        return driver.findElement(By.id("searchbar"));
    }

    private WebElement clickOnSearchResult() {
        return driver.findElement(By.xpath("//*[@class='list-find-port']/li[2]/a"));
    }

    private List<WebElement> portInformation() {
        return driver.findElements(By.xpath("//*[@id='map-info-clone']/div/ul/li[1]/div[2]/span"));
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

    @Step("Get Port Information")
    public String getPortInformation() {
        return portInformation().stream().map(i -> i.getText()).collect(Collectors.joining(" "));
    }
}