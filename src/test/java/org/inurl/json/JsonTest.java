package org.inurl.json;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * @author raylax
 */
public class JsonTest {


    @Test
    public void parseEscapeString() {
        Json.parse("\"\\u20f0\"");
    }

    @Test
    public void parseString() {
        assertEquals("test", Json.parse("\"test\"").toString());
    }

    @Test
    public void parseBigDecimal() {
        assertEquals(new BigDecimal("123.456"), Json.parse("123.456"));
    }

    @Test
    public void parseBoolean() {
        assertTrue((Boolean) Json.parse("true"));
    }

    @Test
    public void parseNull() {
        assertNull(Json.parse("null"));
    }

    @Test
    public void parseEmptyObject() {
        assertTrue(Json.parseObject("{}").isEmpty());
    }

    @Test
    public void parseEmptyArray() {
        assertTrue(Json.parseArray("[]").isEmpty());
    }

    @Test
    public void parseNestedObject() {
        final JsonObject jsonObject = Json.parseObject("{\n" +
                "  \"a\": 1,\n" +
                "  \"b\": \"2\",\n" +
                "  \"c\": true,\n" +
                "  \"d\": {\n" +
                "    \"e\": 4\n" +
                "  },\n" +
                "  \"f\": 1111111,\n" +
                "  \"g\": 111111111111111,\n" +
                "  \"h\": 11111.11111,\n" +
                "  \"i\": [1, 2, 3]\n" +
                "}");
        assertEquals(1, jsonObject.getIntValue("a"));
        assertEquals("2", jsonObject.getString("b"));
        assertTrue(jsonObject.getBooleanValue("c"));
        final JsonObject nestedObject = jsonObject.getJsonObject("d");
        assertEquals(4, nestedObject.getIntValue("e"));
        assertEquals(1111111, jsonObject.getIntValue("f"));
        assertEquals(111111111111111L, jsonObject.getLongValue("g"));
        assertEquals(11111.11111d, jsonObject.getDoubleValue("h"), 0);
        assertEquals(3, jsonObject.getJsonArray("i").size());
    }

    @Test
    public void parseNestedArray() {
        final JsonArray jsonArray = Json.parseArray("[\n" +
                "  \"a\",\"B\", \"c\", [{\"a\":  1}, 2, 3]\n" +
                "], false, null, 111111, 111111111111111, 111111.11111");
        assertEquals(9, jsonArray.size());
        assertEquals("a", jsonArray.get(0));
        assertEquals("B", jsonArray.get(1));
        assertEquals("c", jsonArray.get(2));
        final JsonArray nestedArray = jsonArray.getJsonArray(3);
        assertEquals(3, nestedArray.size());
        final JsonObject nestedArrayJsonObject = nestedArray.getJsonObject(0);
        assertEquals(1, nestedArrayJsonObject.getIntValue("a"));
        assertEquals(2, nestedArray.getIntValue(1));
        assertEquals(3, nestedArray.getIntValue(2));
        assertFalse(jsonArray.getBooleanValue(4));
        assertNull(jsonArray.get(5));
        assertEquals(111111, jsonArray.getIntValue(6));
        assertEquals(111111111111111L, jsonArray.getLongValue(7));
        assertEquals(111111.11111d, jsonArray.getDoubleValue(8), 0);
    }

}