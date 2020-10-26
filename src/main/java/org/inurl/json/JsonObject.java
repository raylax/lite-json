package org.inurl.json;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Optional;
import java.util.function.Function;

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

    public JsonArray getJsonArray(String key) {
        return get(key, JsonArray.class);
    }

    public String getString(String key) {
        return get(key, String.class);
    }

    public BigDecimal getBigDecimal(String key) {
        return get(key, BigDecimal.class);
    }

    public int getIntValue(String key) {
        return getInt(key);
    }

    public double getDoubleValue(String key) {
        return getDouble(key);
    }

    public long getLongValue(String key) {
        return getLong(key);
    }

    public boolean getBooleanValue(String key) {
        return getBoolean(key);
    }

    public Integer getInt(String key) {
        return getBigDecimalVal(key, BigDecimal::intValue);
    }

    public Double getDouble(String key) {
        return getBigDecimalVal(key, BigDecimal::doubleValue);
    }

    public Long getLong(String key) {
        return getBigDecimalVal(key, BigDecimal::longValue);
    }

    public Boolean getBoolean(String key) {
        return (Boolean) Optional.ofNullable(get(key)).orElse(null);
    }

    private <R> R getBigDecimalVal(String key, Function<BigDecimal, R> mapper) {
        return Optional.ofNullable(getBigDecimal(key)).map(mapper).orElse(null);
    }

}
