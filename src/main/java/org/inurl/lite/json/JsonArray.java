package org.inurl.lite.json;

import java.math.BigDecimal;
import java.util.ArrayList;

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

    public String getString(int index) {
        return get(index, String.class);
    }

    public BigDecimal getBigDecimal(int index) {
        return get(index, BigDecimal.class);
    }

    public int getInt(int index) {
        return getBigDecimal(index).intValue();
    }

    public double getDouble(int index) {
        return getBigDecimal(index).doubleValue();
    }

    public long getLong(int index) {
        return getBigDecimal(index).longValue();
    }

}
