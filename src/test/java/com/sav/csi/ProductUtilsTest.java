package com.sav.csi;

import com.sav.csi.entity.ProductPrice;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class ProductUtilsTest {

    @Test
    public void test1() {
        List<ProductPrice> oldPrices = new ArrayList<>();
        List<ProductPrice> newPrices = new ArrayList<>();
        List<ProductPrice> resultPrices = new ArrayList<>();

        oldPrices.add(new ProductPrice("product1", 1, 1, new Date(2020, Calendar.JANUARY, 1), new Date(2020, Calendar.JANUARY, 10), 100));
        oldPrices.add(new ProductPrice("product1", 2, 1, new Date(2020, Calendar.JANUARY, 10), new Date(2020, Calendar.JANUARY, 15), 120));

        newPrices.add(new ProductPrice("product1", 2, 1, new Date(2020, Calendar.JANUARY, 8), new Date(2020, Calendar.JANUARY, 12), 110));
        resultPrices = ProductUtils.mergePrice(oldPrices, newPrices);
        assertEquals(3, resultPrices.size());
    }

    @Test
    public void test2() {
        List<ProductPrice> oldPrices = new ArrayList<>();
        List<ProductPrice> newPrices = new ArrayList<>();
        List<ProductPrice> resultPrices;

        oldPrices.add(new ProductPrice("product1", 1, 1, new Date(2020, Calendar.JANUARY, 1), new Date(2020, Calendar.JANUARY, 10), 100));

        newPrices.add(new ProductPrice("product1", 2, 1, new Date(2020, Calendar.JANUARY, 5), new Date(2020, Calendar.JANUARY, 8), 110));
        resultPrices = ProductUtils.mergePrice(oldPrices, newPrices);
        assertEquals(3, resultPrices.size());
    }

    @Test
    public void test3() {
        List<ProductPrice> oldPrices = new ArrayList<>();
        List<ProductPrice> newPrices = new ArrayList<>();
        List<ProductPrice> resultPrices = new ArrayList<>();

        oldPrices.add(new ProductPrice("product1", 1, 1, new Date(2020, Calendar.JANUARY, 1), new Date(2020, Calendar.JANUARY, 10), 80));
        oldPrices.add(new ProductPrice("product1", 2, 1, new Date(2020, Calendar.JANUARY, 10), new Date(2020, Calendar.JANUARY, 15), 87));
        oldPrices.add(new ProductPrice("product1", 3, 1, new Date(2020, Calendar.JANUARY, 15), new Date(2020, Calendar.JANUARY, 20), 90));

        newPrices.add(new ProductPrice("product1", 4, 1, new Date(2020, Calendar.JANUARY, 8), new Date(2020, Calendar.JANUARY, 13), 80));
        newPrices.add(new ProductPrice("product1", 5, 1, new Date(2020, Calendar.JANUARY, 13), new Date(2020, Calendar.JANUARY, 17), 85));
        resultPrices = ProductUtils.mergePrice(oldPrices, newPrices);
        assertEquals(3, resultPrices.size());
    }
}