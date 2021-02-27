package pl.kluczewski.currency_converter.model;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class CurrencyDto {
    private String currency;
    private String code;
    private double mid;
    private double result;
}
