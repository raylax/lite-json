package org.inurl.json;

/**
 * @author raylax
 */
public interface CharReader {

    /**
     * 返回下一个字符，如果没有更多则返回{@code (char)-1}
     * @return next char
     */
    char next();

    /**
     * 回退
     */
    void back();

    /**
     * 当前字符串
     * @return current char
     */
    char current();

    /**
     * 是否还有字符
     * @return true of false
     */
    boolean hasMore();

}
