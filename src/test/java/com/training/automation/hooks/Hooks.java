package com.training.automation.hooks;

import com.training.driver.DriverFactory;
import com.training.driver.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;

public class Hooks {

    @Before
    public void setUp() {

        String browser =
                System.getProperty("browser", "chrome");

        WebDriver driver =
                DriverFactory.getDriver(browser);

        DriverManager.setDriver(driver);

        driver.manage()
                .window()
                .maximize();
    }

    @After
    public void tearDown() {

        DriverManager.quitDriver();
    }
}