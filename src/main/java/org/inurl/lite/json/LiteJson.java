package org.inurl.lite.json;

/**
 * @author raylax
 */
public class LiteJson {

    public static JsonObject parseObject(String text) {
        return (JsonObject) parse(text);
    }

    public static JsonArray parseArray(String text) {
        return (JsonArray) parse(text);
    }

    private static Object parse(String text) {
        return new JsonReader(text).parse();
    }

}
