package pl.kluczewski.currency_converter.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.kluczewski.currency_converter.model.AllCurrencyDto;
import pl.kluczewski.currency_converter.model.CurrencyDto;
import pl.kluczewski.currency_converter.service.CurrencyService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/currency")
public class CurrencyController {

    private final CurrencyService currencyService;

    @GetMapping("/all")
    public List<AllCurrencyDto> getAllCurrency() {
        return currencyService.getAllValue();
    }

    @GetMapping("/from/{currency}/{quantity}")
    public CurrencyDto getValueFromPln(@PathVariable String currency, @PathVariable double quantity) {
        return currencyService.getValueFromPln(currency, quantity);
    }

    @GetMapping("/from/{currency}/{quantity}/{date}")
    public CurrencyDto getValueFromPln(@PathVariable String currency, @PathVariable double quantity, @PathVariable String date) {
        return currencyService.getValueFromPln(currency, quantity, date);
    }

    @GetMapping("/to/{currency}/{quantity}")
    public CurrencyDto getValueToPln(@PathVariable String currency, @PathVariable double quantity) {
        return currencyService.getValueToPln(currency, quantity);
    }

    @GetMapping("/to/{currency}/{quantity}/{date}")
    public CurrencyDto getValueToPln(@PathVariable String currency, @PathVariable double quantity, @PathVariable String date) {
        return currencyService.getValueToPln(currency, quantity, date);
    }
}
