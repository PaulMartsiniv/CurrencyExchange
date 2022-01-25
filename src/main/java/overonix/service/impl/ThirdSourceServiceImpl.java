package overonix.service.impl;

import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import overonix.dao.ThirdSourceDao;
import overonix.dao.specification.manegerimpl.ThirdSourceSpecificationManager;
import overonix.entity.ThirdSource;
import overonix.service.ThirdSourceService;

@Service
@AllArgsConstructor
public class ThirdSourceServiceImpl implements ThirdSourceService {
    ThirdSourceSpecificationManager thirdSourceSpecificationManager;
    ThirdSourceDao dao;

    @Override
    public ThirdSource save(ThirdSource source) {
        return dao.save(source);
    }

    @Override
    public List<ThirdSource> findAll(Map<String, String> params) {
        Specification<ThirdSource> specification = null;
        for (Map.Entry<String, String> entry : params.entrySet()) {
            Specification<ThirdSource> spec = thirdSourceSpecificationManager
                    .get(entry.getKey(), entry.getValue().split(","));
            specification = specification == null
                    ? Specification.where(spec) : specification.and(spec);
        }
        return dao.findAll(specification);
    }
}
