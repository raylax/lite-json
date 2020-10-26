package org.inurl.lite.json;

/**
 * @author raylax
 */
public interface TokenReader {

    /**
     * 下一个token
     * @return token value
     */
    TokenValue next();

    /**
     * 当前行
     * @return line
     */
    int line();

    /**
     * 当前列
     * @return column
     */
    int column();

}
