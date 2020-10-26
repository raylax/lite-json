package org.inurl.json;

/**
 * Token类型
 *
 * @author raylax
 */
public enum Token {

    /**
     * {
     */
    OBJ_START,
    /**
     * }
     */
    OBJ_END,
    /**
     * [
     */
    ARRAY_START,
    /**
     * ]
     */
    ARRAY_END,
    /**
     * null
     */
    NULL,
    /**
     * N
     */
    NUMBER,
    /**
     * "N"
     */
    STRING,
    /**
     * true or false
     */
    BOOLEAN,
    /**
     * :
     */
    COLON,
    /**
     * ,
     */
    COMMA,
    /**
     * 文档结束
     */
    ED,
    ;

}
