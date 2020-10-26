package org.inurl.lite.json;

import java.util.Stack;

/**
 * @author raylax
 */
public class JsonReader {

    private TokenReader tr;
    private Stack<TokenValue> tokenStack = new Stack<>();

    public JsonReader(String text) {
        this(new StringReader(text));
    }

    public JsonReader(CharReader cr) {
        this(new DefaultTokenReader(cr));
    }

    public JsonReader(TokenReader tr) {
        this.tr = tr;
    }

    public void parse() {
        TokenValue tokenValue;
        do {
            tokenValue = tr.next();
            System.err.println(tokenValue);
        } while (tokenValue.type() != Token.ED);
    }

    public static void main(String[] args) {
        final JsonReader reader = new JsonReader("true");
        reader.parse();
    }


}
