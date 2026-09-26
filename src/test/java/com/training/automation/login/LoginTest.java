package com.training.automation.login;

import com.training.driver.DriverFactory;
import com.training.pages.InventoryPage;
import com.training.pages.LoginPage;
import com.training.util.CsvUtil;
import com.training.util.ExcelUtil;
import com.training.util.JsonUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class LoginTest {
    private WebDriver driver;
    private static final Logger log = LoggerFactory.getLogger(LoginTest.class);

    @BeforeClass
    public void init()
    {
        driver = DriverFactory.getDriver("edge");
    }

//    @Test
//    @Parameters({"uname","pass"})
//    public void loginTest(String username,String password) throws InterruptedException
//    {
//        driver.get("https://saucedemo.com");
//        driver.manage().window().maximize();
//        LoginPage loginPage = new LoginPage(driver);
//        loginPage.fillUserName(username);
//        loginPage.fillPassword(password);
//        InventoryPage inventoryPage = loginPage.clickLoginButton();
//        inventoryPage.openSidebar();
//        Thread.sleep(4000);
//        inventoryPage.closeSidebar();
//        Thread.sleep(4000);
//    }

    @DataProvider(name = "csv_data")
    public Object[][] getCsvData() {

        Map<String, List<String>> csvMap =
                CsvUtil.readCSV(
                        "src/test/resources/testdata/credentials.csv"
                );

        List<String> usernames = csvMap.get("username");
        List<String> passwords = csvMap.get("pass");

        Object[][] data = new Object[usernames.size()][2];

        for (int i = 0; i < usernames.size(); i++) {

            data[i][0] = usernames.get(i);
            data[i][1] = passwords.get(i);
        }

        return data;
    }

    @DataProvider(name = "excel_data")
    public Object[][] getExcelData() {
        return ExcelUtil.readExcel("src/test/resources/testdata/credentials.xlsx","Sheet1");
    }
    @DataProvider(name = "json_data")
    public Object[][] getJsonData() {
        return JsonUtil.readJson("src/test/resources/testdata/credentials.json");
    }


//    @Test(dataProvider = "csv_data")
//    @Test(dataProvider = "excel_data")
    @Test(dataProvider = "json_data")
    public void loginTestData(String username,String password) throws InterruptedException
    {
        driver.get("https://saucedemo.com");
        driver.manage().window().maximize();
        LoginPage loginPage = new LoginPage(driver);
        log.info("Starting login test");
        log.debug("Username: {}", username);
        loginPage.fillUserName(username);
        loginPage.fillPassword(password);
        loginPage.clickLoginButton();
        log.info("Login button clicked");
        boolean loggedIn=false;
        try {
            Assert.assertEquals(
                    driver.getCurrentUrl(),
                    "https://www.saucedemo.com/inventory.html"
            );
            loggedIn=true;
        } catch (AssertionError e) {
            if (!loggedIn) {
                log.warn("Login may not have succeeded");
            }
            log.error("Login URL validation failed", e);
            throw e;
        }



    }


    @AfterClass
    public void tearDown()
    {
        driver.quit();
    }
}
