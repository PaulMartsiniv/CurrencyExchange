package overonix.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import overonix.entity.CurrencyExchangeRate;

public interface CurrencyExchangeRateService {
    List<CurrencyExchangeRate> getExchangeRatesForCurrencies();

    List<String> getAvailableCurrencyCodes();

    List<CurrencyExchangeRate> getExchangeRatesHistory(LocalDate data);

    CurrencyExchangeRate save(CurrencyExchangeRate currency);

    List<CurrencyExchangeRate> findAll(Map<String, String> params);
}
