package com.mcswainsoftware.rest.responses;

/**
 * An abstract implementation of a response from a rest request
 */
public abstract class RestResponse {

    /**
     * The data container holding the response
     */
    private Object content;

    /**
     * The type of data this response represents
     */
    private int type;

    /**
     * JSON data type
     */
    public static final int TYPE_JSON = 0;

    /**
     * XML data type
     */
    public static final int TYPE_XML = 1;

    /**
     * Plain text data type
     */
    public static final int TYPE_PLAIN_TEXT = 2;

    /**
     * Get the response's content
     * @return an Object representing your content
     */
    public abstract Object getContent();

    /**
     * Get the data type
     * @return the data's type
     */
    public abstract int getType();

}
