package com.venkat.Tests;

import com.venkat.Base.BaseClass;
import com.venkat.Pages.LoginPage;
import com.venkat.Pages.HomePage;
import com.venkat.Pages.PortsPage;
import com.venkat.Pages.ShoreExcursionsPage;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SmokeTest extends BaseClass {

    private static String USERNAME = "venkat29";
    private static String PASSWORD = "Cruise12345!";
    private static String PORT = "Honolulu";
    private HomePage homePage;
    private LoginPage loginPage;
    private PortsPage portsPage;
    private ShoreExcursionsPage shoreExcursionsPage;

    @BeforeEach
    public void initEach() {
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        portsPage = new PortsPage(driver);
        shoreExcursionsPage = new ShoreExcursionsPage(driver);
    }

    @Test
    @Order(1)
    @DisplayName("Explore the NCL ports")
    public void smokeTestExploringNCLPorts() {
        // GIVEN
        homePage.clickOnLoginPageLink();
        loginPage.enterUsername(USERNAME).enterPassword(PASSWORD).clickOnLoginButton();
        waitInSeconds(5);

        // AND
        homePage.hoverToExploreNavigation();
        waitInSeconds(2);
        homePage.clickOnPorts();
        waitInSeconds(3);

        // WHEN
        portsPage.searchPort(PORT).clickOnResult();
        waitInSeconds(3);

        // THEN
        Assert.assertEquals(portsPage.getSearchBarValue(), "Honolulu, Oahu");

        // AND
        Assert.assertEquals(portsPage.getPortInformation(), "PORT OF DEPARTURE");
    }

    @Test
    @Order(2)
    @DisplayName("Search Shore Excursions based on price range")
    public void smokeTestShoreExcursions() {
        // GIVEN
        homePage.clickOnLoginPageLink();
        loginPage.enterUsername(USERNAME).enterPassword(PASSWORD).clickOnLoginButton();
        waitInSeconds(5);

        // AND
        homePage.hoverToExploreNavigation();
        waitInSeconds(2);
        homePage.clickOnShoreExcursions();
        waitInSeconds(3);

        // WHEN
        shoreExcursionsPage.clickOnFindExcursions();

        // THEN
        shoreExcursionsPage.setPriceRange();
        waitInSeconds(3);

        // AND
        Assert.assertEquals(shoreExcursionsPage.getFilteredPriceRange(), "$0-$30");

        // AND
        Assert.assertEquals(shoreExcursionsPage.verifyAdultPriceRange(), true);
        Assert.assertEquals(shoreExcursionsPage.verifyChildPriceRange(), true);
    }
}