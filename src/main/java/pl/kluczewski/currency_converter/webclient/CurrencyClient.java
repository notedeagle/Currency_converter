package pl.kluczewski.currency_converter.webclient;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.kluczewski.currency_converter.model.AllCurrencyDto;
import pl.kluczewski.currency_converter.model.CurrencyTable;
import pl.kluczewski.currency_converter.webclient.dto.AllRatesDto;
import pl.kluczewski.currency_converter.webclient.dto.NbpAllCurrencyDto;
import pl.kluczewski.currency_converter.webclient.dto.NbpAllTableDto;

import java.util.stream.Collectors;

@Component
public class CurrencyClient {

    private final RestTemplate restTemplate = new RestTemplate();

    public AllCurrencyDto getValueForAllCurrency() {
        NbpAllCurrencyDto[] currencyDto = callGetMethod("http://api.nbp.pl/api/exchangerates/tables/A",
                NbpAllCurrencyDto[].class);

        return AllCurrencyDto.builder()
                .rates(currencyDto[0]
                        .getRates()
                        .stream()
                        .collect(Collectors.toMap(AllRatesDto::getCode, AllRatesDto::getMid)))
                .base("PLN")
                .build();
    }

    public CurrencyTable getValueForCurrency() {
        NbpAllTableDto[] currencyTable = callGetMethod("http://api.nbp.pl/api/exchangerates/tables/A",
                NbpAllTableDto[].class);

        return null;          //todo builder
    }

    private <T> T callGetMethod(String url, Class<T> responseType, Object... objects) {
        return restTemplate.getForObject(url, responseType, objects);
    }
}
