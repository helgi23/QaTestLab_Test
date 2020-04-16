package com.qatestlab.testcase;

import com.qatestlab.configurations.Currency;
import com.qatestlab.page.CheckPage;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class TestCase extends CheckPage {

    @Test
    public void testUsd() {
        changeCurrency(Currency.USD);
        assertTrue(checkCurrency(Currency.USD));
    }

    @Test
    public void testEur() {

        changeCurrency(Currency.EUR);
        assertTrue(checkCurrency(Currency.EUR));
    }

    @Test
    public void testUah(){
        changeCurrency(Currency.UAH);
        assertTrue(checkCurrency(Currency.UAH));
    }

    @Test
    public void testSearch(){
        changeCurrency(Currency.USD);
        searchByWord();
        assertTrue(checkCountFoundedProducts());
        assertTrue(checkCurrencyFoundedProducts(Currency.USD));
    }

    @Test
    public void testSort(){
        setSortingFromHighToLow();
        assertTrue(checkSortingWithoutDiscount());
    }

    @Test
    public void testDiscount(){
        assertTrue(checkDiscount());
    }
}
