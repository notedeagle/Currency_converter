package pl.kluczewski.currency_converter.converter;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Convert {
    public BigDecimal fromPln(BigDecimal quantity, BigDecimal bid) {
        checkValues(quantity, bid);
        return quantity.divide(bid, 2, RoundingMode.HALF_UP);
    }

    public BigDecimal toPln(BigDecimal quantity, BigDecimal bid) {
        checkValues(quantity, bid);
        return quantity.multiply(bid).setScale(2, RoundingMode.HALF_UP);
    }

    private void checkValues(BigDecimal quantity, BigDecimal value) {
        if (quantity.signum() < 0 || value.signum() < 0)
            throw new IllegalArgumentException("Values must be greater then 0");
    }
}
