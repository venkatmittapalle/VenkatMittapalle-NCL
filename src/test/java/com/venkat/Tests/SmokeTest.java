package com.venkat.Tests;

import com.venkat.Base.BaseClass;
import com.venkat.Pages.LoginPage;
import com.venkat.Pages.HomePage;
import com.venkat.Pages.PortsPage;
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

    @BeforeEach
    public void initEach() {
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        portsPage = new PortsPage(driver);
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
        homePage.hoverToExploreNav().clickOnPorts();
        waitInSeconds(5);

        // WHEN
        portsPage.searchPort(PORT).clickOnResult();
        waitInSeconds(5);

        // THEN
        Assert.assertEquals(portsPage.getSearchBarValue(), "Honolulu, Oahu");
    }
}