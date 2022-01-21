package overonix.init;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import overonix.entity.CurrencyDetails;
import overonix.entity.CurrencyExchangeRate;
import overonix.entity.ThirdSource;
import overonix.service.CurrencyDetailsService;
import overonix.service.CurrencyExchangeRateService;
import overonix.service.ThirdSourceService;
import overonix.utils.JsonReader;
import overonix.utils.ReaderImpl;
import overonix.utils.urls.ExchangeRatesApiIo;

@Component
@AllArgsConstructor
public class DataInit implements ApplicationRunner {
    private final CurrencyDetailsService detailsService;
    private final CurrencyExchangeRateService currencyService;
    private final ThirdSourceService thirdSourceService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        ThirdSource source = ThirdSource.builder()
                .url(ExchangeRatesApiIo.URL)
                .apikey(ExchangeRatesApiIo.APIKEY)
                .build();

        saveCurrencyV1();
        /*
        saveCurrencyV2(ThirdSource.builder().url(FreeCurrencyApi.URL)
                        .apikey(FreeCurrencyApi.APIKEY).build(),
                "base_currency", "time_last_update_unix","rates");
        saveCurrencyV2(ThirdSource.builder().url(ExchangeRateApiCom.URL2)
                        .apikey(ExchangeRateApiCom.APIKEY).build(),
                "base_code", "time_last_update_unix","rates");
        saveCurrencyV2(ThirdSource.builder().url(ExchangeRatesApiIo.URL)
                        .apikey(ExchangeRatesApiIo.APIKEY).build(),
                "base", "timestamp","rates");
         */
    }

    private void saveCurrencyV1() {
        ThirdSource source = ThirdSource.builder()
                .url(ExchangeRatesApiIo.URL)
                .apikey(ExchangeRatesApiIo.APIKEY)
                .build();
        thirdSourceService.save(source);
        String[] split = new ReaderImpl().read(ExchangeRatesApiIo.URL).split(",");
        List<CurrencyExchangeRate> list = new ArrayList<>();
        CurrencyDetails currencyDetails = new CurrencyDetails();
        for (String s : split) {
            if (s.contains("base")) {
                String base = s.replaceAll(".*([A-Z]{3}).*", "$1");
                currencyDetails.setBase(base);
            }
            CurrencyDetails save = null;
            if (s.contains("date")) {
                String date = s.replaceAll("[^\\d+\\-]", "");
                currencyDetails.setDate(LocalDate.parse(date));
                currencyDetails.setSource(source);
                save = detailsService.save(currencyDetails);
            }
            if (s.matches(".*\"(\\w{3})\":(\\d+\\.[0-9]+).*")) {
                String[] array = s.replaceAll(".*\"(\\w{3})\":(\\d+\\.[0-9]+).*", "$1:$2")
                        .split(":");
                list.add(CurrencyExchangeRate.builder()
                        .rate(Double.parseDouble(array[1]))
                        .name(array[0])
                        .currency_details_id(save)
                        .build());
            }
        }
        list.forEach(currencyService::save);
    }

    private void saveCurrencyV2(ThirdSource source, String baseName, String timestamp,
                                String ratesName) {
        Map<String, Object> map = JsonReader.readJson(source.getUrl());
        LocalDate date = Instant.ofEpochSecond((Long) map.get(timestamp))
                .atZone(ZoneId.systemDefault()).toLocalDate();
        CurrencyDetails currencyDetails = CurrencyDetails.builder()
                .base((String) map.get(baseName))
                .date(date)
                .source(thirdSourceService.save(source))
                .build();
        Map<String, Double> rates = (Map<String, Double>) map.get(ratesName);
        List<CurrencyExchangeRate> list = new ArrayList<>();
        for (Map.Entry<String, Double> entry : rates.entrySet()) {
            list.add(CurrencyExchangeRate.builder()
                    .rate(entry.getValue())
                    .name(entry.getKey())
                    .currency_details_id(detailsService.save(currencyDetails))
                    .build());
        }
        list.forEach(currencyService::save);
    }
}
