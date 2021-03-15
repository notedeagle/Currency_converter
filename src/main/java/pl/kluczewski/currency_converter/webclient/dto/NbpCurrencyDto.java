package pl.kluczewski.currency_converter.webclient.dto;

public class NbpCurrencyDto {
    private RatesDto[] rates;

    public RatesDto[] getRates() {
        return rates;
    }

    public double getMid() {
        return rates[0].getMid();
    }

    public String getEffectiveDate() {
        return rates[0].getEffectiveDate();
    }
}
