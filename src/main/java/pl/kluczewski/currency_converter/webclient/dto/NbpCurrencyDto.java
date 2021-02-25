package pl.kluczewski.currency_converter.webclient.dto;

import lombok.Getter;

@Getter
public class NbpCurrencyDto {
    String currency;
    String code;
    RatesDto[] rates;
}
