package org.inurl.lite.json;

import java.math.BigDecimal;

/**
 * @author raylax
 */
public class DefaultTokenReader implements TokenReader {

    /**
     * End of File
     */
    private static final char[] TOKEN_NULL = "null".toCharArray();
    private static final char[] TOKEN_TRUE = "true".toCharArray();
    private static final char[] TOKEN_FALSE = "false".toCharArray();

    private final CharReader r;
    private int line = 1;
    private int column = -1;

    public DefaultTokenReader(CharReader charReader) {
        this.r = charReader;
    }

    @Override
    public TokenValue next() {
        for (;;) {
            if (!r.hasMore()) {
                return TokenValue.ED;
            }
            final char c = nextChar();
            if (c == '-' || (c >= '0' && c <= '9')) {
                return readNumber();
            }
            switch (c) {
                case '\n':
                    line++;
                    column = -1;
                case '\t':
                case ' ':
                    continue;
                case '{':
                    return TokenValue.OBJ_START;
                case '}':
                    return TokenValue.OBJ_END;
                case '[':
                    return TokenValue.ARRAY_START;
                case ']':
                    return TokenValue.ARRAY_END;
                case ':':
                    return TokenValue.COLON;
                case ',':
                    return TokenValue.COMMA;
                case 't':
                    return readBoolean(TOKEN_TRUE, TokenValue.BOOLEAN_TRUE);
                case 'f':
                    return readBoolean(TOKEN_FALSE, TokenValue.BOOLEAN_FALSE);
                case 'n':
                    return readNull();
                case '"':
                    return readString();
                default:
                    throw makeUnexpectedCharException();
            }
        }
    }

    @Override
    public int line() {
        return line;
    }

    @Override
    public int column() {
        return column;
    }

    private TokenValue readNumber() {
        StringBuilder sb = new StringBuilder();
        sb.append(r.current());
        while (r.hasMore()) {
            final char c = nextChar();
            if (c != '.' && !isNumber(c)) {
                final BigDecimal value = parseBigDecimal(sb.toString());
                return TokenValue.number(value);
            }
            sb.append(c);
        }
        throw makeEndOfFileException();
    }

    private BigDecimal parseBigDecimal(String text) {
        try {
            return new BigDecimal(text);
        } catch (NumberFormatException ex) {
            throw makeUnexpectedCharException();
        }
    }

    private TokenValue readString() {
        StringBuilder sb = new StringBuilder();
        while (r.hasMore()) {
            char c = nextChar();
            switch (c) {
                case '\\':
                    char n = nextChar();
                    if (!isEscape(n)) {
                        throw makeUnexpectedCharException();
                    }
                    sb.append("\\");
                    sb.append(n);
                    if (n != 'u') {
                        break;
                    }
                    for (int i = 0; i < 4; i++) {
                        n = nextChar();
                        if (!isHex(n)) {
                            throw makeUnexpectedCharException();
                        }
                        sb.append(n);
                    }
                    break;
                case '"':
                    return TokenValue.string(sb.toString());
                case '\r':
                case '\n':
                    throw makeUnexpectedCharException();
                default:
                    sb.append(c);
                    break;
            }
        }
        throw makeEndOfFileException();
    }

    private boolean isEscape(char c) {
        return (c == '"' || c == '\\' || c == 'u' || c == 'r' || c == 'n' || c == 'b' || c == 't' || c == 'f');
    }

    private boolean isHex(char c) {
        return (('0' <= c && c <= '9') || ('a' <= c && c <= 'f') || ('A' <= c && c <= 'F'));
    }

    private boolean isNumber(char c) {
        return c >= '0' && c <= '9';
    }

    private TokenValue readNull() {
        if (isExpectValue(TOKEN_NULL)) {
            return TokenValue.NULL;
        }
        throw makeUnexpectedCharException();
    }

    private TokenValue readBoolean(char[] expectValue, TokenValue result) {
        if (isExpectValue(expectValue)) {
            return result;
        }
        throw makeUnexpectedCharException();
    }

    private boolean isExpectValue(char[] expectValue) {
        backChar();
        for (char c : expectValue) {
            if (c != nextChar()) {
                return false;
            }
        }
        return true;
    }

    private void backChar() {
        column--;
        r.back();
    }

    private char nextChar() {
        column++;
        return r.next();
    }

    private JsonParseException makeUnexpectedCharException() {
        return new JsonParseException(String.format("Unexpected char '%s' at %d:%d", r.current(), line, column));
    }

    private JsonParseException makeEndOfFileException() {
        return new JsonParseException("Unexpected EOF");
    }

}
