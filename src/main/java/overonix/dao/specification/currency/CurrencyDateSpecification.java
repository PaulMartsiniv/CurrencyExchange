package overonix.dao.specification.currency;

import javax.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import overonix.dao.specification.SpecificationProvider;
import overonix.entity.CurrencyDetails;

@Component
public class CurrencyDateSpecification implements SpecificationProvider<CurrencyDetails> {
    private static final String FILTER_KEY = "dateIn";
    private static final String FIELD_NAME = "date";

    @Override
    public Specification<CurrencyDetails> getSpecification(String[] params) {
        return (root, query, cb) -> {
            CriteriaBuilder.In<String> predicate = cb.in(root.get(FIELD_NAME));
            for (String value : params) {
                predicate.value(value);
            }
            return cb.and(predicate, predicate);
        };
    }

    @Override
    public String getFilterKey() {
        return FILTER_KEY;
    }
}
