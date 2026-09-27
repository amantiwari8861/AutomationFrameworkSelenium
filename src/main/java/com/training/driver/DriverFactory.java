package com.training.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chromium.ChromiumOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public final class DriverFactory {

    private DriverFactory() {
    }

    public static WebDriver getDriver(String browser) {

        boolean headless = Boolean.parseBoolean(
                System.getProperty("headless", "true")
        );

        return switch (browser.toLowerCase()) {

            case "chrome" -> createChromeDriver(headless);

            case "edge" -> createEdgeDriver(headless);

            case "firefox" -> createFirefoxDriver(headless);

            default ->
                    throw new IllegalArgumentException(
                            "Unsupported browser: " + browser
                    );
        };
    }

    private static WebDriver createChromeDriver(boolean headless) {

        ChromeOptions options = new ChromeOptions();

        configureChromiumOptions(options, headless);

        return new ChromeDriver(options);
    }

    private static WebDriver createEdgeDriver(boolean headless) {

        EdgeOptions options = new EdgeOptions();

        configureChromiumOptions(options, headless);

        return new EdgeDriver(options);
    }

    private static WebDriver createFirefoxDriver(boolean headless) {

        FirefoxOptions options = new FirefoxOptions();

        if (headless) {
            options.addArguments("-headless");
        }

        return new FirefoxDriver(options);
    }

    private static void configureChromiumOptions(
            ChromiumOptions<?> options,
            boolean headless) {

        if (headless) {
            options.addArguments("--headless=new");
        }

        options.addArguments(
                "--no-sandbox",
                "--disable-dev-shm-usage",
                "--window-size=1920,1080"
        );
    }
}