package com.training.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {

    public static WebDriver getDriver(String browser)
    {
        WebDriver driver;
        if(browser.equalsIgnoreCase("chrome"))
        {
            driver = new ChromeDriver();
        }
        else if(browser.equalsIgnoreCase("firefox"))
        {
            driver = new FirefoxDriver();
        }
        else if(browser.equalsIgnoreCase("edge"))
        {
            //System.setProperty("webdriver.edge.driver", "C:\\Users\\A181166\\Downloads\\edgedriver_win64\\msedgedriver.exe");

            driver = new EdgeDriver();
        }
        else {
            throw new RuntimeException("Browser Not Found");
        }
        return driver;
    }
}
