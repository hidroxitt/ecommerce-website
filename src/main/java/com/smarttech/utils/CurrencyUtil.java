package com.smarttech.utils;

import com.smarttech.constant.AppConstant;
import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.math.RoundingMode;

@UtilityClass
public class CurrencyUtil {

    public BigDecimal calculateOriginalPrice(BigDecimal price, int quantity) {
        return price.multiply(new BigDecimal(quantity)).setScale(2, RoundingMode.HALF_EVEN);
    }

    public BigDecimal calculateOriginalAndDiscount(BigDecimal price, int discount) {
        return price
                .multiply(calculatePercentDiscount(discount))
                .setScale(2, RoundingMode.HALF_EVEN);
    }

    public BigDecimal calculateDiscountPrice(BigDecimal price, int discount, int quantity) {
        return price
                .multiply(new BigDecimal(quantity))
                .multiply(calculatePercentDiscount(discount))
                .setScale(2, RoundingMode.HALF_EVEN);
    }

    private BigDecimal calculatePercentDiscount(int discount) {
        return AppConstant.ONE_HUNDRED
                .subtract(new BigDecimal(String.valueOf(discount)))
                .divide(AppConstant.ONE_HUNDRED);
    }
}
