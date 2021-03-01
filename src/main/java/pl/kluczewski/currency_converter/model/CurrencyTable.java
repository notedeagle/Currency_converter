package pl.kluczewski.currency_converter.model;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Builder
@Getter
public class CurrencyTable {
    private String effectiveDate;
    private Map<String, Double> rates;
}
