package put.selenium.linear;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import put.selenium.utils.ScreenshotAndQuitOnFailureRule;

import static org.junit.Assert.*;


public class AccountsLinearScriptAT {

    private WebDriver driver;

    @Rule
    public ScreenshotAndQuitOnFailureRule screenshotOnFailureAndWebDriverQuitRule =
            new ScreenshotAndQuitOnFailureRule();


    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "chromedriver-97.exe");
        this.driver = new ChromeDriver();
        screenshotOnFailureAndWebDriverQuitRule.setWebDriver(driver);
        driver.get("http://localhost:8080/accounts/controller?action=db_reset");
    }

    @Test
    public void successfulUserRegistration() throws Exception {
        driver.get("http://localhost:8080/accounts/controller?action=db_reset");
        driver.findElement(By.linkText("Register")).click();
        driver.findElement(By.name("username")).click();
        driver.findElement(By.name("username")).sendKeys("TestUser");
        driver.findElement(By.name("password")).click();
        driver.findElement(By.name("password")).sendKeys("TestPassword");
        driver.findElement(By.cssSelector("tr:nth-child(4) > td:nth-child(2)")).click();
        driver.findElement(By.name("repeat_password")).click();
        driver.findElement(By.name("repeat_password")).sendKeys("TestPassword");
        driver.findElement(By.name("name")).click();
        driver.findElement(By.name("name")).sendKeys("TestName");
        driver.findElement(By.name("addressData")).click();
        driver.findElement(By.name("addressData")).sendKeys("TestAddress");
        driver.findElement(By.name("submit")).click();
        driver.findElement(By.name("username")).click();
        driver.findElement(By.name("username")).sendKeys("TestUser");
        driver.findElement(By.name("password")).click();
        driver.findElement(By.name("password")).sendKeys("TestPassword");
        driver.findElement(By.name("submit")).click();
    }


}
