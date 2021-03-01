package pl.kluczewski.currency_converter.webclient.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class NbpAllTableDto {
    private String effectiveDate;
    private List<AllRatesDto> rates;
}
