package org.inurl.json;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Function;

/**
 * @author raylax
 */
public class JsonArray extends ArrayList<Object> {

    @Override
    public Object get(int index) {
        return super.get(index);
    }

    @SuppressWarnings("unchecked")
    public <T> T get(int index, Class<T> clazz) {
        return (T) get(index);
    }

    public JsonObject getJsonObject(int index) {
        return get(index, JsonObject.class);
    }

    public JsonArray getJsonArray(int index) {
        return get(index, JsonArray.class);
    }

    public String getString(int index) {
        return get(index, String.class);
    }

    public BigDecimal getBigDecimal(int index) {
        return get(index, BigDecimal.class);
    }

    public int getIntValue(int index) {
        return getInt(index);
    }

    public double getDoubleValue(int index) {
        return getDouble(index);
    }

    public long getLongValue(int index) {
        return getLong(index);
    }

    public boolean getBooleanValue(int index) {
        return getBoolean(index);
    }

    public Integer getInt(int index) {
        return getBigDecimalVal(index, BigDecimal::intValue);
    }

    public Double getDouble(int index) {
        return getBigDecimalVal(index, BigDecimal::doubleValue);
    }

    public Long getLong(int index) {
        return getBigDecimalVal(index, BigDecimal::longValue);
    }

    public Boolean getBoolean(int index) {
        return (Boolean) Optional.ofNullable(get(index)).orElse(null);
    }

    private <R> R getBigDecimalVal(int index, Function<BigDecimal, R> mapper) {
        return Optional.ofNullable(getBigDecimal(index)).map(mapper).orElse(null);
    }

}
