package pl.kluczewski.currency_converter.webclient;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.kluczewski.currency_converter.converter.Convert;
import pl.kluczewski.currency_converter.model.AllCurrencyDto;
import pl.kluczewski.currency_converter.model.CurrencyDto;
import pl.kluczewski.currency_converter.webclient.dto.AllRatesDto;
import pl.kluczewski.currency_converter.webclient.dto.NbpAllCurrencyDto;
import pl.kluczewski.currency_converter.webclient.dto.NbpCurrencyDto;

import java.util.stream.Collectors;

@Component
public class CurrencyClient {

    private final RestTemplate restTemplate = new RestTemplate();
    private final Convert convert = new Convert();

    private <T> T callGetMethod(String url, Class<T> responseType, Object... objects) {
        return restTemplate.getForObject(url, responseType, objects);
    }

    public AllCurrencyDto getValueForAllCurrency() {
        NbpAllCurrencyDto nbpAllCurrencyDto = callGetMethod("http://api.nbp.pl/api/exchangerates/tables/A",
                NbpAllCurrencyDto[].class)[0];

        return AllCurrencyDto.builder()
                .rates(nbpAllCurrencyDto
                        .getRates()
                        .stream()
                        .collect(Collectors.toMap(AllRatesDto::getCode, AllRatesDto::getMid)))
                .base("PLN")
                .effectiveDate(nbpAllCurrencyDto.getEffectiveDate())
                .build();
    }

    public CurrencyDto getValueFromPln(String currency, double quantity) {
        NbpCurrencyDto nbpCurrencyDto = callGetMethod("http://api.nbp.pl/api/exchangerates/rates/a/{currency}/",
                NbpCurrencyDto.class, currency);

        return CurrencyDto.builder()
                .mid(nbpCurrencyDto.getMid())
                .result(convert.fromPln(quantity, nbpCurrencyDto.getMid()))
                .effectiveDate(nbpCurrencyDto.getEffectiveDate())
                .build();
    }

    public CurrencyDto getValueFromPln(String currency, double quantity, String date) {
        NbpCurrencyDto nbpCurrencyDto = callGetMethod("http://api.nbp.pl/api/exchangerates/rates/a/{currency}/{date}/",
                NbpCurrencyDto.class, currency, date);

        return CurrencyDto.builder()
                .mid(nbpCurrencyDto.getMid())
                .result(convert.fromPln(quantity, nbpCurrencyDto.getMid()))
                .effectiveDate(nbpCurrencyDto.getEffectiveDate())
                .build();
    }

    public CurrencyDto getValueToPln(String currency, double quantity) {
        NbpCurrencyDto nbpCurrencyDto = callGetMethod("http://api.nbp.pl/api/exchangerates/rates/a/{currency}/",
                NbpCurrencyDto.class, currency);

        return CurrencyDto.builder()
                .mid(nbpCurrencyDto.getMid())
                .result(convert.toPln(quantity, nbpCurrencyDto.getMid()))
                .effectiveDate(nbpCurrencyDto.getEffectiveDate())
                .build();
    }

    public CurrencyDto getValueToPln(String currency, double quantity, String date) {
        NbpCurrencyDto nbpCurrencyDto = callGetMethod("http://api.nbp.pl/api/exchangerates/rates/a/{currency}/{date}/",
                NbpCurrencyDto.class, currency, date);

        return CurrencyDto.builder()
                .mid(nbpCurrencyDto.getMid())
                .result(convert.toPln(quantity, nbpCurrencyDto.getMid()))
                .effectiveDate(nbpCurrencyDto.getEffectiveDate())
                .build();
    }
}
