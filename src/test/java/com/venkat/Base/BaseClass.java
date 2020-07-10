package com.venkat.Base;

import org.assertj.core.api.JUnitSoftAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.concurrent.TimeUnit;

public class BaseClass {

    protected JUnitSoftAssertions softAssertions = new JUnitSoftAssertions();
    protected WebDriver driver;
    private static String URL = "https://www.ncl.com";

    /**
     * setup the browser
     */
    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", "/Users/venkatmittapalle/Downloads/geckodriver");
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get(URL);
    }

    /**
     * quit the browser
     */
    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    /**
     * @param seconds
     */
    public void waitInSeconds(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }
}