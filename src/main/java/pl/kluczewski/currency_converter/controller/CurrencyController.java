package pl.kluczewski.currency_converter.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.kluczewski.currency_converter.model.AllCurrencyDto;
import pl.kluczewski.currency_converter.model.CurrencyTable;
import pl.kluczewski.currency_converter.service.CurrencyService;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/currency")
public class CurrencyController {

    private final CurrencyService currencyService;

    @GetMapping("/table")
    public CurrencyTable getCurrencyTable() {
        return currencyService.getValue();
    }

    @GetMapping("/all")
    public AllCurrencyDto getAllCurrency() {
        return currencyService.getAllValue();
    }
}
