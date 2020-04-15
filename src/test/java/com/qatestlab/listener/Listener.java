package com.qatestlab.listener;


import java.util.Arrays;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;

public class Listener extends AbstractWebDriverEventListener {

    private static final Logger LOG = LogManager.getLogger(Listener.class);

    @Override
    public void beforeNavigateTo(String url, WebDriver driver) {
        LOG.info("Before navigating to: '" + url);
    }

    @Override
    public void afterNavigateTo(String url, WebDriver driver) {
        LOG.info("Navigated to: '" + url);
    }

    @Override
    public void beforeFindBy(By by, WebElement element, WebDriver driver) {
        LOG.info("Before to find: by: " + by.toString());
    }

    @Override
    public void afterFindBy(By by, WebElement element, WebDriver driver) {
        LOG.info("Found element By : " + by.toString());
    }

    @Override
    public void beforeClickOn(WebElement element, WebDriver driver) {
        LOG.info("Before to click on " + element.toString());
    }

    @Override
    public void afterClickOn(WebElement element, WebDriver driver) {
        LOG.info("After to click on " + element.toString());
    }

    @Override
    public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
        LOG.info("Before to change value of " + element.toString() + " on "
                + Arrays.toString(keysToSend));
    }

    @Override
    public void afterChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
        LOG.info("After to change value of " + element.toString() + " on "
                + Arrays.toString(keysToSend));
    }

    @Override
    public void onException(Throwable throwable, WebDriver driver) {
        LOG.info("An exception " + throwable + " was thrown");
    }
}

