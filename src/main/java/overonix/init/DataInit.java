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
import overonix.utils.ClientReaderImpl;
import overonix.utils.JsonReader;
import overonix.utils.JsonReaderImpl;
import overonix.utils.urls.ExchangeRateApiCom;
import overonix.utils.urls.ExchangeRatesApiIo;

@Component
@AllArgsConstructor
public class DataInit implements ApplicationRunner {
    private static final String API_KAY = "******";
    private final CurrencyDetailsService detailsService;
    private final CurrencyExchangeRateService currencyService;
    private final ThirdSourceService thirdSourceService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        ThirdSource source = ThirdSource.builder()
                .url(ExchangeRatesApiIo.URL)
                .apikey(API_KAY)
                .build();

        saveCurrencyV1();
        /*
        saveCurrencyV2(new ThirdSource(API_KAY, FreeCurrencyApi.URL),
                "base_currency", "timestamp","data");
        */
        saveCurrencyV2(new ThirdSource(API_KAY, ExchangeRateApiCom.URL2),
                "base_code", "time_last_update_unix", "conversion_rates");

        saveCurrencyV2(new ThirdSource(API_KAY, ExchangeRatesApiIo.URL),
                "base", "timestamp", "rates");

    }

    private void saveCurrencyV1() {
        ThirdSource source = ThirdSource.builder()
                .url("api.exchangeratesapi.io")
                .apikey(API_KAY)
                .build();
        String[] split = new ClientReaderImpl().read(ExchangeRatesApiIo.URL).split(",");
        List<CurrencyExchangeRate> list = new ArrayList<>();
        CurrencyDetails currencyDetails = new CurrencyDetails();
        for (String s : split) {
            if (s.contains("base")) {
                String base = s.replaceAll(".*([A-Z]{3}).*", "$1");
                currencyDetails.setBase(base);
            }
            if (s.contains("date")) {
                String date = s.replaceAll("[^\\d+\\-]", "");
                currencyDetails.setDate(LocalDate.parse(date));
            }
            if (s.matches(".*\"(\\w{3})\":(\\d+\\.[0-9]+).*")) {
                String[] array = s.replaceAll(".*\"(\\w{3})\":(\\d+\\.[0-9]+).*", "$1:$2")
                        .split(":");
                list.add(CurrencyExchangeRate.builder()
                        .rate(Double.parseDouble(array[1]))
                        .name(array[0])
                        .build());
            }
        }
        currencyDetails.setRates(list);
        source.setDetails(currencyDetails);
        list.forEach(currencyService::save);
        detailsService.save(currencyDetails);
        thirdSourceService.save(source);
    }

    private void saveCurrencyV2(ThirdSource source, String baseKey, String timestampKey,
                                String ratesKey) {
        JsonReader jsonReader = new JsonReaderImpl();
        Map<String, Object> map = jsonReader.readJson(source.getUrl());
        long timestamp = Long.parseLong(map.get(timestampKey).toString());
        LocalDate date = Instant.ofEpochSecond(timestamp)
                .atZone(ZoneId.systemDefault()).toLocalDate();
        CurrencyDetails details = new CurrencyDetails(date, (String) map.get(baseKey));
        Map<String, Number> rates = (Map<String, Number>) map.get(ratesKey);
        List<CurrencyExchangeRate> list = new ArrayList<>();
        for (Map.Entry<String, Number> entry : rates.entrySet()) {
            list.add(CurrencyExchangeRate.builder()
                    .rate(entry.getValue().doubleValue())
                    .name(entry.getKey())
                    .build());
        }
        details.setRates(list);
        source.setDetails(details);
        list.forEach(currencyService::save);
        detailsService.save(details);
        thirdSourceService.save(source);
    }

}
