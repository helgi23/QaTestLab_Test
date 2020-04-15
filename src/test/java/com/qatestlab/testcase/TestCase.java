package com.qatestlab.testcase;

import com.qatestlab.configurations.Currency;
import com.qatestlab.page.CheckPage;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class TestCase extends CheckPage {

    @Test
    public void testAll() {
        changeCurrency(Currency.USD);
        assertTrue(checkCurrency(Currency.UAH));

        changeCurrency(Currency.EUR);
        assertTrue(checkCurrency(Currency.EUR));

        changeCurrency(Currency.UAH);
        assertTrue(checkCurrency(Currency.UAH));

        changeCurrency(Currency.USD);
        searchByWord();
        assertTrue(checkCountFoundedProducts());
        assertTrue(checkCurrencyFoundedProducts(Currency.USD));

        setSortingFromHighToLow();
        assertTrue(checkSortingWithoutDiscount());

        assertTrue(checkDiscount());
    }
}
