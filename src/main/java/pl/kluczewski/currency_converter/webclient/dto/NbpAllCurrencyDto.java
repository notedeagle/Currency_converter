package pl.kluczewski.currency_converter.webclient.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class NbpAllCurrencyDto {
    private List<AllRatesDto> rates;
    private String effectiveDate;
}
