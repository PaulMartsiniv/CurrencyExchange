package overonix.service;

import java.util.List;
import java.util.Map;
import overonix.entity.CurrencyDetails;

public interface CurrencyDetailsService {
    CurrencyDetails save(CurrencyDetails currencyDetails);

    List<CurrencyDetails> findAll(Map<String, String> params);

    List<CurrencyDetails> findAllByDateBetween(String from, String to);
}
