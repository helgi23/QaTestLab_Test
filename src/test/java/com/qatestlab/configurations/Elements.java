package com.qatestlab.configurations;

import org.openqa.selenium.By;

public class Elements {

    public final static By SELECTOR_CURRENCY = By.cssSelector(".expand-more._gray-darker");
    public final static By XPATH_EUR = By.xpath("//a[.='EUR €']");
    public final static By XPATH_UAH = By.xpath("//a[.='UAH ₴']");
    public final static By XPATH_USD = By.xpath("//a[.='USD $']");
    public final static By BY_PRICE = By.className("price");
    public final static By BY_INPUT = By.name("s");
    public final static By BY_TOTAL_PRODUCTS = By.className("total-products");
    public final static By BY_PRODUCT_MINIATURE = By.className("product-miniature");
    public final static By BY_SELECT_TITLE = By.className("select-title");
    public final static By BY_PRODUCT_PRICE =  By.className("product-price-and-shipping");
    public final static By BY_PRICE_HIGH_TO_LOW =
            By.cssSelector("#js-product-list-top > div:nth-child(2) > div > div > div > a:nth-child(5)");

}