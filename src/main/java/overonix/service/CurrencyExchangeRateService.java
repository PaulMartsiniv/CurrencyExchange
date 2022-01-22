package overonix.service;

import java.util.List;
import java.util.Map;
import overonix.entity.CurrencyExchangeRate;

public interface CurrencyExchangeRateService {
    List<String> getAvailableCurrencyCodes();

    CurrencyExchangeRate save(CurrencyExchangeRate currency);

    List<CurrencyExchangeRate> findAll(Map<String, String> params);
}
