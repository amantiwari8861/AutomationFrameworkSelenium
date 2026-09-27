package com.training.automation.base;

import com.training.driver.DriverFactory;
import com.training.driver.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {

        String browser =
                System.getProperty("browser", "chrome");

        driver = DriverFactory.getDriver(browser);

        DriverManager.setDriver(driver);

        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown() {

        DriverManager.quitDriver();
    }
}