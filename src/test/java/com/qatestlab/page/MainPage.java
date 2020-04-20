package com.qatestlab.page;

import java.util.List;

import com.qatestlab.configurations.ConfigTest;
import com.qatestlab.configurations.Value;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;

import static com.qatestlab.configurations.Elements.*;

public class MainPage extends ConfigTest {

    private static final Logger LOG = LogManager.getLogger(MainPage.class);
    private List<WebElement> prices;

    protected void changeValue(Value value) {
        waitElementToByClickable(SELECTOR_CURRENCY).click();
        switch (value) {
            case EUR:
                waitElementToByClickable(XPATH_EUR).click();
                break;
            case UAH:
                waitElementToByClickable(XPATH_UAH).click();
                break;
            case USD:
                waitElementToByClickable(XPATH_USD).click();
                break;
            default:
                throw new IllegalArgumentException("Wrong value, should be: EUR, UAH, USD");
        }
        LOG.info("Value successfully change on: " + value);
    }

    protected boolean checkValue(Value value) {
        prices = waitElementToByClickable(BY_PRICE).findElements(BY_PRICE);

        LOG.info("Verifying product's price value type ...");
        switch (value) {
            case EUR:
                return checkCurrencySymbol("€", " is displayed in EUR value. Verified.");
            case UAH:
                return checkCurrencySymbol("₴", " is displayed in UAH value. Verified.");
            case USD:
                return checkCurrencySymbol("$", " is displayed in USD value. Verified.");
            default:
                throw new IllegalArgumentException(
                        "Wrong value, should be: EUR, UAH, USD");
        }
    }

    private boolean checkCurrencySymbol(String symbol, String text) {
        for (WebElement price : prices) {
            if (price.getText().contains(symbol)) {
                LOG.info(price.getText() + text);
            } else {
                return false;
            }
        }
        return true;
    }
}
