package org.inurl.lite.json;


/**
 * @author raylax
 */
public class StringReader implements CharReader {

    private final char[] chars;
    private int index = 0;

    public StringReader(String text) {
        this.chars = text.toCharArray();
    }

    @Override
    public char next() {
        if (!hasMore()) {
            return (char) -1;
        }
        return chars[index++];
    }

    @Override
    public void back() {
        index--;
    }

    @Override
    public char current() {
        return chars[index - 1];
    }

    @Override
    public boolean hasMore() {
        return index < chars.length;
    }

}
