package org.inurl.json;

/**
 * @author raylax
 */
public class Json {

    public static JsonObject parseObject(String text) {
        return (JsonObject) parse(text);
    }

    public static JsonArray parseArray(String text) {
        return (JsonArray) parse(text);
    }

    public static Object parse(String text) {
        return new JsonReader(text).parse();
    }

}
