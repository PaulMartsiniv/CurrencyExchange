package overonix.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import overonix.dao.CurrencyExchangeRateDao;
import overonix.dao.specification.manegerimpl.CurrencySpecificationManager;
import overonix.entity.CurrencyExchangeRate;
import overonix.service.CurrencyExchangeRateService;

@Service
@AllArgsConstructor
public class CurrencyExchangeRateServiceImpl implements CurrencyExchangeRateService {
    CurrencySpecificationManager currencySpecificationManager;
    CurrencyExchangeRateDao currencyDao;

    @Override
    public Set<String> getAvailableCurrencyCodes() {
        return new HashSet<>(currencyDao.getAvailableCurrencyCodes());
    }

    @Override
    public CurrencyExchangeRate save(CurrencyExchangeRate currency) {
        return currencyDao.save(currency);
    }

    @Override
    public List<CurrencyExchangeRate> findAll(Map<String, String> params) {
        Specification<CurrencyExchangeRate> specification = null;
        for (Map.Entry<String, String> entry : params.entrySet()) {
            Specification<CurrencyExchangeRate> spec = currencySpecificationManager
                    .get(entry.getKey(), entry.getValue().split(","));
            specification = specification == null
                    ? Specification.where(spec) : specification.and(spec);
        }
        return currencyDao.findAll(specification);
    }
}
