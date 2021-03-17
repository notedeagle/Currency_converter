package pl.kluczewski.currency_converter.model;

import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class CurrencyDto {
    String code;
    double mid;
    double result;
    String effectiveDate;
}
