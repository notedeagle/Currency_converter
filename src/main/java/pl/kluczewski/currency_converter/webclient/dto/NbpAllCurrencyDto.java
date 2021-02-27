package pl.kluczewski.currency_converter.webclient.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class NbpAllCurrencyDto {
    private String table;
    private String no;
    private String tradingDate;
    private String effectiveDate;
    private List<AllRatesDto> rates;
}
