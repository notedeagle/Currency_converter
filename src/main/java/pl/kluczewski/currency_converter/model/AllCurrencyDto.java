package pl.kluczewski.currency_converter.model;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Builder
@Getter
public class AllCurrencyDto {
    private Map<String, String> allCurrency;
}
