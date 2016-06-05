package com.mcswainsoftware.rest.responses;

/**
 * An plain text response from a Rest request
 */
public class RestResponsePlainText extends RestResponse {

    /**
     * The data container holding the response
     */
    private String content;

    /**
     * The type of data this response represents
     */
    private int type = RestResponse.TYPE_PLAIN_TEXT;

    /**
     * Create an plain text response
     * @param content the String representing your content
     */
    public RestResponsePlainText(String content) {
        this.content = content;
    }

    /**
     * Get the response's content
     * @return a String containing your response
     */
    @Override
    public String getContent() {
        return content;
    }

    /**
     * Get the data type
     * @return the data's type
     */
    @Override
    public int getType() {
        return type;
    }
}
