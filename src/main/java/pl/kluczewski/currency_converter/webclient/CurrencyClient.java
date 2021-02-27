package pl.kluczewski.currency_converter.webclient;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.kluczewski.currency_converter.model.AllCurrencyDto;
import pl.kluczewski.currency_converter.model.CurrencyDto;
import pl.kluczewski.currency_converter.webclient.dto.AllRatesDto;
import pl.kluczewski.currency_converter.webclient.dto.NbpAllCurrencyDto;
import pl.kluczewski.currency_converter.webclient.dto.NbpCurrencyDto;

import java.util.stream.Collectors;

@Component
public class CurrencyClient {

    private final RestTemplate restTemplate = new RestTemplate();

    public CurrencyDto getValueForCurrency(String currency, double number) {
        NbpCurrencyDto currencyDtoTableA = callGetMethod("http://api.nbp.pl/api/exchangerates/rates/A/{currency}",
                NbpCurrencyDto.class, currency);

        NbpCurrencyDto currencyDtoTableB = callGetMethod("http://api.nbp.pl/api/exchangerates/rates/B/{currency}",
                NbpCurrencyDto.class, currency);

        return CurrencyDto.builder()
                .currency(currencyDtoTableA.getCurrency())
                .code(currencyDtoTableA.getCode())
                .mid(currencyDtoTableA.getRates()[0].getMid())
                .result(number / currencyDtoTableA.getRates()[0].getMid())
                .build();
    }

    public AllCurrencyDto getValueForAllCurrency() {
        NbpAllCurrencyDto[] currencyDto = callGetMethod("http://api.nbp.pl/api/exchangerates/tables/A",
                NbpAllCurrencyDto[].class);

        return AllCurrencyDto.builder()
                .allCurrency(currencyDto[0]
                        .getRates()
                        .stream()
                        .collect(Collectors.toMap(AllRatesDto::getCurrency, AllRatesDto::getCode)))
                .build();
    }

    private <T> T callGetMethod(String url, Class<T> responseType, Object... objects) {
        return restTemplate.getForObject(url, responseType, objects);
    }
}
