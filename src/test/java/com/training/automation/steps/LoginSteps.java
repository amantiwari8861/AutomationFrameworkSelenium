package com.training.automation.steps;

import com.training.driver.DriverManager;
import com.training.pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

public class LoginSteps {

    private static final Logger log =
            LoggerFactory.getLogger(LoginSteps.class);

    private LoginPage loginPage;

    @Given("user is on the SauceDemo login page")
    public void userIsOnLoginPage() {

        DriverManager.getDriver()
                .get("https://www.saucedemo.com");

        loginPage =
                new LoginPage(
                        DriverManager.getDriver()
                );

        log.info("Starting login test");
    }

    @When("user enters email {string}")
    public void userEntersUsername(String username) {

        log.debug("Username: {}", username);

        loginPage.fillUserName(username);
    }

    @When("user enters password {string}")
    public void userEntersPassword(String password) {

        loginPage.fillPassword(password);
    }

    @When("user clicks on the login button")
    public void userClicksLoginButton() {

        loginPage.clickLoginButton();

        log.info("Login button clicked");
    }

    @Then("user should be redirected to the inventory page")
    public void userShouldBeRedirectedToInventoryPage() {

        String expectedUrl =
                "https://www.saucedemo.com/inventory.html";

        String actualUrl =
                DriverManager.getDriver()
                        .getCurrentUrl();

        Assert.assertEquals(
                actualUrl,
                expectedUrl,
                "Login URL validation failed"
        );

        log.info("Login successful");
    }
}