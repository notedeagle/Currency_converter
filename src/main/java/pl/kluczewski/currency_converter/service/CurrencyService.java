package pl.kluczewski.currency_converter.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.kluczewski.currency_converter.model.AllCurrencyDto;
import pl.kluczewski.currency_converter.model.CurrencyTable;
import pl.kluczewski.currency_converter.webclient.CurrencyClient;

@Service
@RequiredArgsConstructor
public class CurrencyService {

    private final CurrencyClient currencyClient;

    public CurrencyTable getValue() {
        return currencyClient.getValueForCurrency();
    }

    public AllCurrencyDto getAllValue() {
        return currencyClient.getValueForAllCurrency();
    }
}
