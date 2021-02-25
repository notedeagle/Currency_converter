package pl.kluczewski.currency_converter.webclient;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.kluczewski.currency_converter.model.CurrencyDto;
import pl.kluczewski.currency_converter.webclient.dto.NbpCurrencyDto;

@Component
public class CurrencyClient {

    private final RestTemplate restTemplate = new RestTemplate();

    public CurrencyDto getValueForCurrency(String currency, double number) {
        NbpCurrencyDto currencyDto = callGetMethod("http://api.nbp.pl/api/exchangerates/rates/C/{currency}",
                NbpCurrencyDto.class, currency);

        return CurrencyDto.builder()
                .currency(currencyDto.getCurrency())
                .code(currencyDto.getCode())
                .bid(currencyDto.getRates()[0].getAsk())
                .ask(currencyDto.getRates()[0].getBid())
                .result(number / currencyDto.getRates()[0].getBid())
                .build();
    }

    private <T> T callGetMethod(String url, Class<T> responseType, Object... objects) {
        return restTemplate.getForObject(url, responseType, objects);
    }

}
