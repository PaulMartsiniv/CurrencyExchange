package overonix.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import overonix.dao.CurrencyDetailsDao;
import overonix.dao.specification.SpecificationManager;
import overonix.dao.specification.manegerimpl.CurrencyDetailsSpecificationManager;
import overonix.entity.CurrencyDetails;
import overonix.service.CurrencyDetailsService;

@Service
@AllArgsConstructor
public class CurrencyDetailsServiceImpl implements CurrencyDetailsService {
    SpecificationManager<CurrencyDetails> specificationManager;
    CurrencyDetailsDao dao;

    @Override
    public CurrencyDetails save(CurrencyDetails currencyDetails) {
        return dao.save(currencyDetails);
    }

    @Override
    public List<CurrencyDetails> findAll(Map<String, String> params) {
        Specification<CurrencyDetails> specification = null;
        for (Map.Entry<String, String> entry : params.entrySet()) {
            Specification<CurrencyDetails> spec = specificationManager
                    .get(entry.getKey(), entry.getValue().split(","));
            specification = specification == null
                    ? Specification.where(spec) : specification.and(spec);
        }
        return dao.findAll(specification);
    }

    @Override
    public List<CurrencyDetails> findAllByDateBetween(String from, String to) {
        LocalDate parseFrom = LocalDate.parse(from);
        LocalDate parseTo = LocalDate.parse(to);
        return dao.findAllByDateBetween(parseFrom, parseTo);
    }
}
