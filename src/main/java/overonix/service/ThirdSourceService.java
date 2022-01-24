package overonix.service;

import java.util.List;
import java.util.Map;
import overonix.entity.ThirdSource;

public interface ThirdSourceService {
    ThirdSource save(ThirdSource source);

    List<ThirdSource> findAll(Map<String, String> params);
}
