package com.mcswainsoftware.rest.responses;

/**
 * An JSON response from a Rest request
 */
public class RestResponseJSON extends RestResponse {

    /**
     * The data container holding the response, either a JSONArray or JSONObject
     */
    private Object content;

    /**
    * The type of data this response represents
    */
    private int type = RestResponse.TYPE_JSON;

    /**
     * Create an JSON response
     * @param content the JSONArray or JSONObject representing your data
     */
    public RestResponseJSON(Object content) {
        this.content = content;
    }

    /**
     * Get the response's JSON data
     * @return either a JSONObject or JSONArray representing your response
     */
    @Override
    public Object getContent() {
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
