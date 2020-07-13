package com.venkat.Pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.stream.Collectors;

public class ShoreExcursionsPage {
    private WebDriver driver;

    private WebElement searchBar() {
        return driver.findElement(By.id("searchbar"));
    }

    private WebElement getPageTitle() {
        return driver.findElement(By.xpath("//*[@class='breadcrumb-detail visible-desktops']/h2"));
    }

    private WebElement destinationOptions() {
        return driver.findElement(By.xpath("//*[@id='search_destinations_chosen']/a/span[contains(text(), 'Destination')]"));
    }

    private WebElement searchDestination() {
        return driver.findElement(By.xpath("//*[@id='search_destinations_chosen']/div/div/input[@class='chosen-search-input']"));
    }

    private WebElement selectDestination() {
        return driver.findElement(By.xpath("//*[@id='search_destinations_chosen']/div/ul/li"));
    }

    private WebElement findExcursionsButton() {
        return driver.findElement(By.xpath("//*[@class='col-sm-4 col-sm-offset-4 text-center search-column']/button"));
    }

    private WebElement selectPortFilter() {
        return driver.findElement(By.xpath("//*[@class='link-text' and contains(text(), 'Port')]"));
    }

    private WebElement priceRangeSlider() {
        return driver.findElement(By.xpath("//*[@class='ui-slider-handle ui-corner-all ui-state-default'][2]"));
    }

    private WebElement priceRange() {
        return driver.findElement(By.xpath("//*[@id='price-slider-legend']/span[2]"));
    }

    private List<WebElement> getAdultStartingPrices() {
        return driver.findElements(By.xpath("//div[@class='price-different clearfix']/ul/li/strong"));
    }

    private List<WebElement> getChildStartingPrices() {
        return driver.findElements(By.xpath("//div[@class='price-different clearfix']/ul/li[2]/strong"));
    }

    public ShoreExcursionsPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Click on Find Excursions")
    public ShoreExcursionsPage clickOnFindExcursions() {
        findExcursionsButton().click();
        return this;
    }

    @Step("Set price range to 30 dollars")
    public ShoreExcursionsPage setPriceRange() {

        JavascriptExecutor je = (JavascriptExecutor)driver;
        je.executeScript("scroll(0, 250);");

        Actions action = new Actions(driver);
        action.moveToElement(priceRangeSlider()).clickAndHold().moveByOffset(-184,0).release().perform();
        return this;
    }

    @Step("Get the filtered price range")
    public String getFilteredPriceRange() {
        return priceRange().getText();
    }

    @Step("Verify the adult starting price range")
    public boolean verifyAdultPriceRange() {
        List<Integer> prices = getAdultStartingPrices().stream().map(p -> p.getText().replaceAll("[^\\d.]", "").substring(0,2)).map(Integer::parseInt).collect(Collectors.toList());

        return prices.stream().allMatch(p -> p <= 30);
    }

    @Step("Verify the child starting price range")
    public boolean verifyChildPriceRange() {
        List<Integer> prices = getChildStartingPrices().stream().map(p -> p.getText().replaceAll("[^\\d.]", "").substring(0,2)).map(Integer::parseInt).collect(Collectors.toList());

        return prices.stream().allMatch(p -> p <= 30);
    }

    @Step("Enter the port name to explore")
    public ShoreExcursionsPage searchPort(String portName) {
        searchBar().sendKeys(portName);
        return this;
    }
}