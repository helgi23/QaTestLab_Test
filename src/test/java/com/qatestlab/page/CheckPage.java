package com.qatestlab.page;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.qatestlab.configurations.Currency;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.qatestlab.configurations.Elements.*;

public class CheckPage extends MainPage {

    private static final Logger LOG = LogManager.getLogger(MainPage.class);

    protected void searchByWord() {
        waitVisibilityOfElementLocated(BY_INPUT).sendKeys("dress");
        waitVisibilityOfElementLocated(BY_INPUT).submit();
        LOG.info("Products successfully found.");
    }

    protected boolean checkCountFoundedProducts() {
        WebElement element = waitVisibilityOfElementLocated(BY_TOTAL_PRODUCTS);
        String count = element.getText();
        count = count.split(":")[1].replace(".", "").trim();

        LOG.info("Count product = " + count);

        List<WebElement> elements = waitVisibilityOfAllElementsLocatedBy(BY_PRODUCT_MINIATURE);

        LOG.info("Count product image = " + elements.size());
        return Integer.parseInt(count) == elements.size();
    }

    protected boolean checkCurrencyFoundedProducts(Currency currency) {
        List<WebElement> prices = waitVisibilityOfAllElementsLocatedBy(BY_PRICE);

        switch (currency) {
            case EUR:
                LOG.info("Check symbol currency " +  currency);
                return checkSymbol(prices, "€");
            case UAH:
                LOG.info("Check symbol currency " +  currency);
                return checkSymbol(prices, "₴");
            case USD:
                LOG.info("Check symbol currency " +  currency);
                return checkSymbol(prices, "$");
            default:
                throw new IllegalArgumentException(
                        "Not correct currency name, should be: €, ₴, $");
        }
    }

    private boolean checkSymbol(List<WebElement> prices, String symbol) {
        for (WebElement value : prices) {
            if (!value.getText().contains(symbol)) {
                return false;
            }
        }
        return true;
    }

    protected void setSortingFromHighToLow() {
        waitVisibilityOfElementLocated(BY_SELECT_TITLE).click();
        waitVisibilityOfElementLocated(BY_PRICE_HIGH_TO_LOW).click();
        LOG.info("Selected sorting from high to low");
    }

    protected boolean checkSortingWithoutDiscount() {
        List<WebElement> elements = waitVisibilityOfAllElementsLocatedBy(BY_PRODUCT_PRICE);

        List<Double> listPrices = new ArrayList<>();
        for (WebElement element : elements) {
            if (element.getText().contains("%")) {
                searchRegularPrice(listPrices, element, "regular-price");
            } else {
                searchRegularPrice(listPrices, element, "price");
            }
        }

        List<Double> tempList = new ArrayList<>(listPrices);
        tempList.sort(Collections.reverseOrder());
        if (tempList.equals(listPrices)) {
            LOG.info("Check sorting without discount is true");
            return true;
        } else {
            LOG.info("Check sorting without discount is false");
            return false;
        }
    }

    private void searchRegularPrice(List<Double> listPrices, WebElement element, String by) {
        WebElement price = element.findElement(By.className(by));
        String[] prices = price.getText().split(" ");
        listPrices.add(Double.parseDouble(prices[0].replace(",", ".")));
    }

    protected boolean checkDiscount() {
        List<WebElement> elements = waitVisibilityOfAllElementsLocatedBy(BY_PRODUCT_MINIATURE);
        for (WebElement element : elements) {
            String[] lines = element.getText().split("\n");
            if (lines[2].contains("%")) {
                double discount = Double.parseDouble(lines[2].substring(1, lines[2].length() - 1));
                double itemPrice = Double
                        .parseDouble(lines[1].substring(0, lines[1].length() - 2)
                                .replaceAll(",", "."));
                double discountedPrice = Double
                        .parseDouble(lines[3].substring(0, lines[3].length() - 2)
                                .replaceAll(",", "."));

                double expectDiscountedPrice =
                        Math.round((itemPrice * (1 - discount / 100)) * 100) / 100.00;
                if (discountedPrice == expectDiscountedPrice) {
                    LOG.info("Check discount : " + discount + " is correctly");
                    return true;
                } else {
                    LOG.info("Check discount - discounted price: "
                            + discountedPrice + " expect discounted price: " + expectDiscountedPrice);
                }
            }
        }
        LOG.info("Check discount is not correctly");
        return false;
    }
}

