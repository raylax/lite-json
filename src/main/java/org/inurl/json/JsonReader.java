package org.inurl.json;

import java.util.Stack;

/**
 * @author raylax
 */
public class JsonReader {

    private final TokenReader tr;
    private final Stack<TokenValue> tokenStack = new Stack<>();
    private final Stack<Object> valueStack = new Stack<>();

    public JsonReader(String text) {
        this(new StringReader(text));
    }

    public JsonReader(CharReader cr) {
        this(new DefaultTokenReader(cr));
    }

    public JsonReader(TokenReader tr) {
        this.tr = tr;
    }

    public Object parse() {
        TokenValue tokenValue;
        do {
            tokenValue = tr.next();
            processTokenValue(tokenValue);
        } while (tokenValue.type() != Token.ED);
        return valueStack.isEmpty() ? null : valueStack.pop();
    }

    private void processTokenValue(TokenValue tokenValue) {
        final Token type = tokenValue.type();
        switch (type) {
            case OBJ_START:
                valueStack.push(new JsonObject());
                tokenStack.push(tokenValue);
                break;
            case ARRAY_START:
                valueStack.push(new JsonArray());
                tokenStack.push(tokenValue);
                break;
            case OBJ_END:
            case ARRAY_END:
                tokenStack.pop();
                if (valueStack.size() > 1) {
                    addValue(valueStack.pop());
                }
                break;
            case STRING:
                if (!tokenStack.isEmpty() && tokenStack.peek().type() == Token.OBJ_START) {
                    tokenStack.push(tokenValue);
                    return;
                }
            case BOOLEAN:
            case NULL:
            case NUMBER:
                addValue(tokenValue.value());
                break;
            case COLON:
            case COMMA:
            case ED:
                // noop
        }
    }

    private void addValue(Object value) {
        if (valueStack.isEmpty()) {
            valueStack.push(value);
            return;
        }
        final Object c = valueStack.peek();
        if (c instanceof JsonArray) {
            ((JsonArray) c).add(value);
        }
        if (c instanceof JsonObject) {
            ((JsonObject) c).put(tokenStack.pop().value(String.class), value);
        }
    }


}
