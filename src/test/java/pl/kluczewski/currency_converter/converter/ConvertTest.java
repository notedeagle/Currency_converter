package pl.kluczewski.currency_converter.converter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ConvertTest {

    private Convert convert;
    private BigDecimal quantity, bid;

    @BeforeEach
    public void setUp() {
        convert = new Convert();
        quantity = new BigDecimal(1500);
        bid = new BigDecimal("4.1355");
    }

    @Test
    public void fromPln() {
       assertEquals(BigDecimal.valueOf(362.71), convert.fromPln(quantity, bid));
    }

    @Test
    public void toPln() {
        assertEquals(BigDecimal.valueOf(6203.25), convert.toPln(quantity, bid));
    }

    @Test
    public void shouldThrownIllegalArgumentExceptionOnNagativeQuantity() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> convert.fromPln(new BigDecimal(-100), bid)
        );
        assertEquals("Values must be greater then 0", exception.getMessage());
    }

    @Test
    public void shouldThrownIllegalArgumentExceptionOnNagativeBid() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> convert.fromPln(quantity, new BigDecimal(-100))
        );
        assertEquals("Values must be greater then 0", exception.getMessage());
    }

    @Test
    public void shouldThrownIllegalArgumentExceptionOnNagativeBidAndQuantity() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> convert.fromPln(new BigDecimal(-100), new BigDecimal(-100))
        );
        assertEquals("Values must be greater then 0", exception.getMessage());
    }
}