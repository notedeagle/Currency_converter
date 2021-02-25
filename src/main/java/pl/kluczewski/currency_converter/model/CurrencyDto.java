package pl.kluczewski.currency_converter.model;

import lombok.Builder;
import lombok.Getter;
import pl.kluczewski.currency_converter.webclient.dto.RatesDto;

@Builder
@Getter
public class CurrencyDto {
    private String currency;
    private String code;
    private double bid;
    private double ask;
    private double result;
}
