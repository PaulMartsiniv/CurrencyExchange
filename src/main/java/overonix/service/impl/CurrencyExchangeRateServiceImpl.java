package overonix.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import overonix.dao.CurrencyExchangeRateDao;
import overonix.dao.specification.CurrencySpecificationManager;
import overonix.entity.CurrencyExchangeRate;
import overonix.service.CurrencyExchangeRateService;

@Service
@AllArgsConstructor
public class CurrencyExchangeRateServiceImpl implements CurrencyExchangeRateService {
    CurrencySpecificationManager currencySpecificationManager;
    CurrencyExchangeRateDao currencyDao;

    @Override
    public List<CurrencyExchangeRate> getExchangeRatesForCurrencies() {
        return currencyDao.findAll();
    }

    @Override
    public List<String> getAvailableCurrencyCodes() {
        return currencyDao.getAvailableCurrencyCodes();
    }

    @Override
    public List<CurrencyExchangeRate> getExchangeRatesHistory(LocalDate data) {
        return currencyDao.getExchangeRatesHistory(data);
    }

    @Override
    public CurrencyExchangeRate save(CurrencyExchangeRate currency) {
        return currencyDao.save(currency);
    }

    @Override
    public List<CurrencyExchangeRate> findAll(Map<String, String> params) {
        Specification<CurrencyExchangeRate> specification = null;
        for (Map.Entry<String, String> entry : params.entrySet()) {
            Specification<CurrencyExchangeRate> s = currencySpecificationManager
                    .get(entry.getKey(), entry.getValue().split(","));
            specification = specification == null
                    ? Specification.where(s) : specification.and(s);
        }
        return currencyDao.findAll(specification);
    }
}
