package org.inurl.lite.json;

/**
 * @author raylax
 */
public class TokenValue {

    public static final TokenValue OBJ_START = new TokenValue(Token.OBJ_START);
    public static final TokenValue OBJ_END = new TokenValue(Token.OBJ_END);
    public static final TokenValue ARRAY_START = new TokenValue(Token.ARRAY_START);
    public static final TokenValue ARRAY_END = new TokenValue(Token.ARRAY_END);
    public static final TokenValue NULL = new TokenValue(Token.NULL);
    public static final TokenValue BOOLEAN_TRUE = new TokenValue(Token.BOOLEAN, true);
    public static final TokenValue BOOLEAN_FALSE = new TokenValue(Token.BOOLEAN, false);
    public static final TokenValue COLON = new TokenValue(Token.COLON);
    public static final TokenValue COMMA = new TokenValue(Token.COMMA);
    public static final TokenValue ED = new TokenValue(Token.ED);

    private Token type;

    private Object value;


    private TokenValue(Token type) {
        this(type, null);
    }

    private TokenValue(Token type, Object value) {
        this.type = type;
        this.value = value;
    }

    public static TokenValue string(Object value) {
        return new TokenValue(Token.STRING, value);
    }

    public static TokenValue number(Object value) {
        return new TokenValue(Token.NUMBER, value);
    }

    public Token type() {
        return type;
    }

    @SuppressWarnings("unchecked")
    public <T> T value(Class<T> clazz) {
        return (T)type;
    }

    @Override
    public String toString() {
        return String.format("<%s> %s", type, value == null ? "" : value);
    }

}
