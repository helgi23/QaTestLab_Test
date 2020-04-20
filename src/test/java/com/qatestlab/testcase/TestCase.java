package com.qatestlab.testcase;

import com.qatestlab.configurations.Value;
import com.qatestlab.page.CheckPage;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class TestCase extends CheckPage {

    @Test
    public void testUsd() {
        changeValue(Value.USD);
        assertTrue(checkCurrency(Value.USD));
    }

    @Test
    public void testEur() {

        changeValue(Value.EUR);
        assertTrue(checkCurrency(Value.EUR));
    }

    @Test
    public void testUah(){
        changeValue(Value.UAH);
        assertTrue(checkCurrency(Value.UAH));
    }

    @Test
    public void testSearch(){
        changeValue(Value.USD);
        searchByWord();
        assertTrue(checkCountFoundedProducts());
        assertTrue(checkCurrencyFoundedProducts(Value.USD));
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
