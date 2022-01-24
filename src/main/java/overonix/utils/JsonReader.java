package overonix.utils;

import java.util.Map;

public interface JsonReader {
    Map<String, Object> readJson(String url);
}
