package pl.kluczewski.currency_converter.converter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConvertTest {

    private Convert convert;
    private double quantity, bid;

    @BeforeEach
    public void setUp() {
        convert = new Convert();
        quantity = 1500;
        bid = 4.1355;
    }

    @Test
    public void fromPln() {
       assertEquals(convert.fromPln(quantity, bid), 362.71);
    }

    @Test
    public void toPln() {
        assertEquals(convert.toPln(quantity, bid), 6203.25);
    }
}