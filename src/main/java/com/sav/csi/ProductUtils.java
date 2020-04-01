package com.sav.csi;

import com.sav.csi.entity.ProductPrice;

import java.util.ArrayList;
import java.util.List;

public class ProductUtils {
    /**
     * Метод в качестве параметров получает коллекцию имеющихся цен товара, новых цен и
     * должен вернуть коллекцию объединенных цен, для дальнейшего сохранения в БД.
     */
    public static List<ProductPrice> mergePrice(List<ProductPrice> oldPrices, List<ProductPrice> newPrices) {
        List<ProductPrice> resultPrices = new ArrayList<>();
        if (oldPrices.isEmpty()) {
            resultPrices.addAll(newPrices);
        } else {
            resultPrices.addAll(oldPrices);
            for(ProductPrice newPrice : newPrices) {
                resultPrices = mergeOnePrice(newPrice, resultPrices);
            }
        }

        return resultPrices;
    }

    private static List<ProductPrice> mergeOnePrice(ProductPrice newPrice, List<ProductPrice> oldPrices) {
        List<ProductPrice> mergedPrices = new ArrayList<>();
        boolean notSaveNewPrice = false;  //true - если новая цена сливается со старой
        for(ProductPrice oldPrice : oldPrices) {
            //новая цена не пересекается со старой ценой
            if (newPrice.getEnd().before(oldPrice.getBegin()) || newPrice.getBegin().after(oldPrice.getEnd())) {
                //do nothing
            }
            //диапазон новой цены полностью перекрывает диапазон старой цены
            else if (newPrice.getBegin().compareTo(oldPrice.getBegin()) <= 0
                            && newPrice.getEnd().compareTo(oldPrice.getEnd()) >= 0) {
                continue;
            }
            //диапазон новой цены внутри диапазона старой цены
            else if (oldPrice.getBegin().before(newPrice.getBegin()) && oldPrice.getEnd().after(newPrice.getEnd())) {
                if (newPrice.getValue() == oldPrice.getValue()) {
                    notSaveNewPrice = true;
                } else {
                    oldPrice.setEnd(newPrice.getBegin());
                    ProductPrice partOldPrice = new ProductPrice(oldPrice);
                    partOldPrice.setBegin(newPrice.getEnd());
                    mergedPrices.add(partOldPrice);
                }
            }
            //новая цена пересекается в начале старой цены
            else if (oldPrice.getBegin().before(newPrice.getEnd()) && oldPrice.getEnd().after(newPrice.getEnd())) {
                if (newPrice.getValue() == oldPrice.getValue()) {
                    oldPrice.setBegin(newPrice.getBegin());
                    notSaveNewPrice = true;
                } else {
                    oldPrice.setBegin(newPrice.getEnd());
                }
            }
            //новая цена пересекается в конце старой цены
            else if (oldPrice.getEnd().after(newPrice.getBegin()) && oldPrice.getBegin().before(newPrice.getBegin())) {
                if (newPrice.getValue() == oldPrice.getValue()) {
                    oldPrice.setEnd(newPrice.getEnd());
                    notSaveNewPrice = true;
                } else {
                    oldPrice.setEnd(newPrice.getBegin());
                }
            }
            if (oldPrice.getBegin().before(oldPrice.getEnd())) {
                mergedPrices.add(oldPrice);
            }
        }
        if (!notSaveNewPrice) {
            mergedPrices.add(newPrice);
        }
        return mergedPrices;
    }
}
