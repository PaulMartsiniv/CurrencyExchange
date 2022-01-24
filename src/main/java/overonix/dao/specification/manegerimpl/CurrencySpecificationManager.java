package overonix.dao.specification.manegerimpl;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import overonix.dao.specification.SpecificationManager;
import overonix.dao.specification.SpecificationProvider;
import overonix.entity.CurrencyExchangeRate;

@Component
public class CurrencySpecificationManager implements SpecificationManager<CurrencyExchangeRate> {
    private final Map<String, SpecificationProvider<CurrencyExchangeRate>> providersMap;

    public CurrencySpecificationManager(List<SpecificationProvider<CurrencyExchangeRate>> list) {
        this.providersMap = list.stream()
                .collect(Collectors.toMap(SpecificationProvider::getFilterKey,
                        Function.identity()));
    }

    @Override
    public Specification<CurrencyExchangeRate> get(String filterKey, String[] params) {
        if (!providersMap.containsKey(filterKey)) {
            throw new RuntimeException("Key " + filterKey + " is no supported for data filtering");
        }
        return providersMap.get(filterKey).getSpecification(params);
    }
}
