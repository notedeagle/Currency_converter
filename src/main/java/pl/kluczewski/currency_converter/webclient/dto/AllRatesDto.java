package pl.kluczewski.currency_converter.webclient.dto;

import lombok.Getter;

@Getter
public class AllRatesDto {
    private String currency;
    private String code;
    private double mid;
}
