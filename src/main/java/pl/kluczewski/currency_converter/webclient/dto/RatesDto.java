package pl.kluczewski.currency_converter.webclient.dto;

import lombok.Getter;

@Getter
public class RatesDto {
    private String no;
    private String effectiveDate;
    private double mid;
}
