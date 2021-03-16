package pl.kluczewski.currency_converter.converter;

public class Convert {
    public double fromPln(double quantity, double bid) {
        return Math.round(quantity * bid * 100.0) / 100.0;
    }

    public double toPln(double quantity, double bid) {
        return Math.round(quantity / bid * 100.0) / 100.0;
    }
}
