package overonix.service.impl;

import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import overonix.dao.CurrencyDetailsDao;
import overonix.dao.specification.CurrencyDetailsSpecificationManager;
import overonix.entity.CurrencyDetails;
import overonix.service.CurrencyDetailsService;

@Service
@AllArgsConstructor
public class CurrencyDetailsServiceImpl implements CurrencyDetailsService {
    CurrencyDetailsSpecificationManager specificationManager;
    CurrencyDetailsDao dao;

    @Override
    public CurrencyDetails save(CurrencyDetails currencyDetails) {
        return dao.save(currencyDetails);
    }

    @Override
    public List<CurrencyDetails> findAll(Map<String, String> params) {
        Specification<CurrencyDetails> specification = null;
        for (Map.Entry<String, String> entry : params.entrySet()) {
            Specification<CurrencyDetails> s = specificationManager
                    .get(entry.getKey(), entry.getValue().split(","));
            specification = specification == null
                    ? Specification.where(s) : specification.and(s);
        }
        return dao.findAll(specification);
    }
}
