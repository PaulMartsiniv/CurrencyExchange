package overonix.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;

public class JsonReader {
    public static Map<String, Object> readJson(String url) {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map = null;
        try {
            map = objectMapper.readValue(url,
                    new TypeReference<>() {
                    });
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Can't read JSON: " + e);
        }
        return map;
    }
}
