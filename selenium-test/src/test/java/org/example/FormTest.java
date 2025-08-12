package org.example;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.*;

import java.net.URL;
import java.time.Duration;
public class FormTest {
    private WebDriver driver;
    private String webUrl;

    @BeforeEach
    void setUp() throws Exception {
        String seleniumUrl = System.getenv().getOrDefault("SELENIUM_URL","http://localhost:4444/wd/hub");
        webUrl = System.getenv().getOrDefault("WEB_URL","http://localhost:3000");
        ChromeOptions opts = new ChromeOptions();
        opts.addArguments("--headless=new", "--no-sandbox","--disable-dev-shm-usage");
        driver = new RemoteWebDriver(new URL(seleniumUrl), opts);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
    }

    @AfterEach
    void tearDown() { if (driver != null) driver.quit(); }

    @Test
    void submitFormShowsSuccess() {
        driver.get(webUrl + "/");
        driver.findElement(By.name("name")).sendKeys("Aruna");
        driver.findElement(By.name("email")).sendKeys("aruna@example.com");
        driver.findElement(By.name("message")).sendKeys("Hello!");
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        WebElement success = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("success")));
        Assertions.assertTrue(success.getText().contains("Thanks"));
    }
}
