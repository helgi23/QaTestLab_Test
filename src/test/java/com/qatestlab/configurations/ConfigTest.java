package com.qatestlab.configurations;

import java.io.File;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.qatestlab.listener.Listener;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class ConfigTest {

    private static final Logger LOG = LogManager.getLogger(ConfigTest.class);
    private EventFiringWebDriver driver;
    private Wait<WebDriver> wait;

    @BeforeClass
    public void setUp() {
        driver = getEventDriver();
        driver.get("http://prestashop-automation.qatestlab.com.ua/ru/");
        wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(1))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class, StaleElementReferenceException.class);
        LOG.info("Driver successfully setup");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            LOG.info("Driver successfully quit");
        }
    }

    private WebDriver getDriver() {
        System.setProperty("webdriver.gecko.driver",
                new File(ConfigTest.class.getResource("/geckodriver.exe").getFile()).getPath());
        return new FirefoxDriver();
    }

    private EventFiringWebDriver getEventDriver() {
        WebDriver driver = getDriver();
        driver.manage().window().maximize();
        EventFiringWebDriver eventDriver = new EventFiringWebDriver(driver);
        eventDriver.register(new Listener());
        eventDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        LOG.info("Get Event driver successfully");
        return eventDriver;
    }

    protected WebElement waitElementToByClickable(By by) {
        return wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    protected WebElement waitVisibilityOfElementLocated(By by) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    protected List<WebElement> waitVisibilityOfAllElementsLocatedBy(By by) {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
    }
}
