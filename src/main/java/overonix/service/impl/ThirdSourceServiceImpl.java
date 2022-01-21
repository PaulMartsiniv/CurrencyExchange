package overonix.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import overonix.dao.ThirdSourceDao;
import overonix.entity.ThirdSource;
import overonix.service.ThirdSourceService;

@Service
@AllArgsConstructor
public class ThirdSourceServiceImpl implements ThirdSourceService {
    ThirdSourceDao dao;

    @Override
    public ThirdSource save(ThirdSource source) {
        return dao.save(source);
    }
}
