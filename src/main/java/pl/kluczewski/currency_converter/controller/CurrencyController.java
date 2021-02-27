package pl.kluczewski.currency_converter.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.kluczewski.currency_converter.model.AllCurrencyDto;
import pl.kluczewski.currency_converter.model.CurrencyDto;
import pl.kluczewski.currency_converter.service.CurrencyService;

@RestController
@RequiredArgsConstructor
public class CurrencyController {

    private final CurrencyService currencyService;

    @GetMapping("/currency/{currency}/{number}")
    public CurrencyDto getCurrency(@PathVariable String currency, @PathVariable double number) {
        return currencyService.getValue(currency, number);
    }

    @GetMapping("/currency/all")
    public AllCurrencyDto getAllCurrency() {
        return currencyService.getAllValue();
    }
}
