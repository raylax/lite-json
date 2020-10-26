package org.inurl.lite.json;

import java.math.BigDecimal;
import java.util.HashMap;

/**
 * @author raylax
 */
public class JsonObject extends HashMap<String, Object> {


    public Object get(String key) {
        return super.get(key);
    }

    @SuppressWarnings("unchecked")
    public <T> T get(String key, Class<T> clazz) {
        return (T) get(key);
    }

    public JsonObject getJsonObject(String key) {
        return get(key, JsonObject.class);
    }

    public String getString(String key) {
        return get(key, String.class);
    }

    public BigDecimal getBigDecimal(String key) {
        return get(key, BigDecimal.class);
    }

    public int getInt(String key) {
        return getBigDecimal(key).intValue();
    }

    public double getDouble(String key) {
        return getBigDecimal(key).doubleValue();
    }

    public long getLong(String key) {
        return getBigDecimal(key).longValue();
    }


}
