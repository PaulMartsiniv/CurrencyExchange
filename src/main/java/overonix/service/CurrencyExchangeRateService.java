package overonix.service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import overonix.entity.CurrencyExchangeRate;

public interface CurrencyExchangeRateService {
    Set<String> getAvailableCurrencyCodes();

    CurrencyExchangeRate save(CurrencyExchangeRate currency);

    List<CurrencyExchangeRate> findAll(Map<String, String> params);
}
