package pl.kluczewski.currency_converter.Converter;

public class Convert {
    public static double fromPln(double quantity, double bid) {
        return Math.round(quantity * bid * 100.0) / 100.0;
    }

    public static double toPln(double quantity, double bid) {
        return Math.round(quantity / bid * 100.0) / 100.0;
    }
}
