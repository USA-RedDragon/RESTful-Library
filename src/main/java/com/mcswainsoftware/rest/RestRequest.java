package com.mcswainsoftware.rest;

import java.util.HashMap;

/**
 * The object representation of a REST request
 *
 * A URL to connect to, and the return data type are required
 * The default HTTP method is GET
 *
 * Basic HTTP authentication is supported
 *
 * Arguments are to be appended to the URL in a GET request
 *
 * Any headers you'd like to add must be explicitly added by addHeader or passing a HashMap of names and values
 *
 * Post data is a String object that must be formed by the user, this allows for flexibility sending JSON data, URL encoded values, etc.
 */
public class RestRequest {

    /**
     * The base URL
     */
    private String baseURL;

    /**
     * The return data type
     */
    private int dataType;

    /**
     * The HTTP method
     */
    private int method;

    /**
     * The GET arguments
     */
    private HashMap<String, String> arguments = new HashMap<>();

    /**
     * The HTTP headers
     */
    private HashMap<String, String> headers = new HashMap<>();

    /**
     * The POST data
     */
    private String postData;

    /**
     * The HTTP username
     */
    private String httpUsername;

    /**
     * The HTTP password
     */
    private String httpPassword;

    /**
     * JSON data type
     */
    public static final int RETURN_TYPE_JSON = 0;

    /**
     * XML data type
     */
    public static final int RETURN_TYPE_XML = 1;

    /**
     * Plain text data type
     */
    public static final int RETURN_TYPE_PLAIN_TEXT = 2;

    /**
     * POST HTTP method
     */
    public static final int METHOD_POST = 0;

    /**
     * GET HTTP method
     */
    public static final int METHOD_GET = 1;

    /**
     * Form a RestRequest, assumes GET method
     * @param baseURL URL to connect to
     * @param dataType the data type the service returns
     */
    public RestRequest(String baseURL, int dataType) {
        this.baseURL = baseURL;
        this.dataType = dataType;
        this.method = METHOD_GET;
    }

    /**
     * Form a RestRequest
     * @param baseURL URL to connect to
     * @param dataType the data type the service returns
     * @param method the HTTP method
     */
    public RestRequest(String baseURL, int dataType, int method) {
        this.baseURL = baseURL;
        this.dataType = dataType;
        this.method = method;
    }

    /**
     * Form a RestRequest with Basic HTTP authentication
     * @param baseURL URL to connect to
     * @param dataType the data type the service returns
     * @param method the HTTP method
     * @param httpUsername the HTTP username
     * @param httpPassword the HTTP password
     */
    public RestRequest(String baseURL, int dataType, int method, String httpUsername, String httpPassword) {
        this.baseURL = baseURL;
        this.dataType = dataType;
        this.method = method;
        this.httpUsername = httpUsername;
        this.httpPassword = httpPassword;
    }

    /**
     * Form a RestRequest with Basic HTTP authentication, assumes GET method
     * @param baseURL URL to connect to
     * @param dataType the data type the service returns
     * @param httpUsername the HTTP username
     * @param httpPassword the HTTP password
     */
    public RestRequest(String baseURL, int dataType, String httpPassword, String httpUsername) {
        this.baseURL = baseURL;
        this.dataType = dataType;
        this.httpPassword = httpPassword;
        this.httpUsername = httpUsername;
        this.method = METHOD_GET;
    }

    /**
     * Form a RestRequest with GET arguments
     * @param baseURL URL to connect to
     * @param dataType the data type the service returns
     * @param method the HTTP method
     * @param arguments the GET arguments to pass in the URL
     */
    public RestRequest(String baseURL, int dataType, int method, HashMap<String, String> arguments) {
        this.baseURL = baseURL;
        this.dataType = dataType;
        this.method = method;
        this.arguments = arguments;
    }

    /**
     * Form a RestRequest with GET arguments, assumes GET method
     * @param baseURL URL to connect to
     * @param dataType the data type the service returns
     * @param arguments the GET arguments to pass in the URL
     */
    public RestRequest(String baseURL, int dataType, HashMap<String, String> arguments) {
        this.baseURL = baseURL;
        this.dataType = dataType;
        this.arguments = arguments;
        this.method = METHOD_GET;
    }

    /**
     * Form a RestRequest with GET arguments and HTTP authentication
     * @param baseURL URL to connect to
     * @param dataType the data type the service returns
     * @param method the HTTP method
     * @param arguments the GET arguments to pass in the URL
     * @param headers the HTTP headers to add
     * @param httpUsername the HTTP username
     * @param httpPassword the HTTP password
     */
    public RestRequest(String baseURL, int dataType, int method, HashMap<String, String> arguments, HashMap<String, String> headers, String httpUsername, String httpPassword) {
        this.baseURL = baseURL;
        this.dataType = dataType;
        this.method = method;
        this.arguments = arguments;
        this.headers = headers;
        this.httpUsername = httpUsername;
        this.httpPassword = httpPassword;
    }

    /**
     *
     * Form a RestRequest with GET arguments and HTTP authentication, assumes GET method
     * @param baseURL URL to connect to
     * @param dataType the data type the service returns
     * @param arguments the GET arguments to pass in the URL
     * @param httpUsername the HTTP username
     * @param httpPassword the HTTP password
     */
    public RestRequest(String baseURL, int dataType, String httpUsername, String httpPassword, HashMap<String, String> arguments) {
        this.baseURL = baseURL;
        this.dataType = dataType;
        this.httpUsername = httpUsername;
        this.httpPassword = httpPassword;
        this.arguments = arguments;
        this.method = METHOD_GET;
    }

    /**
     * Form a RestRequest with GET arguments and HTTP authentication
     * @param baseURL URL to connect to
     * @param dataType the data type the service returns
     * @param method the HTTP method
     * @param arguments the GET arguments to pass in the URL
     * @param httpUsername the HTTP username
     * @param httpPassword the HTTP password
     */
    public RestRequest(String baseURL, int dataType, int method, HashMap<String, String> arguments, String httpUsername, String httpPassword) {
        this.baseURL = baseURL;
        this.dataType = dataType;
        this.method = method;
        this.arguments = arguments;
        this.httpUsername = httpUsername;
        this.httpPassword = httpPassword;
    }

    /**
     * Get the GET arguments
     * @return the arguments
     */
    public HashMap<String, String> getArguments() {
        return arguments;
    }

    /**
     * Set the GET arguments
     * @param arguments the arguments
     */
    public void setArguments(HashMap<String, String> arguments) {
        this.arguments = arguments;
    }

    /**
     * Get the HTTP headers
     * @return the headers
     */
    public HashMap<String, String> getHeaders() {
        return headers;
    }

    /**
     * Set the HTTP headers
     * @param headers the headers
     */
    public void setHeaders(HashMap<String, String> headers) {
        this.headers = headers;
    }

    /**
     * Get the HTTP auth username
     * @return the username
     */
    public String getHttpUsername() { return httpUsername; }

    /**
     * Set the HTTP auth username
     * @param httpUsername the username
     */
    public void setHttpUsername(String httpUsername) {
        this.httpUsername = httpUsername;
    }

    /**
     * Get the HTTP auth password
     * @return the password
     */
    public String getHttpPassword() {
        return httpPassword;
    }

    /**
     * Set the HTTP auth password
     * @param httpPassword the password
     */
    public void setHttpPassword(String httpPassword) {
        this.httpPassword = httpPassword;
    }

    /**
     * Get the return data type
     * @return the data type
     */
    public int getDataType() {
        return dataType;
    }

    /**
     * Set the return data type
     * @param dataType the date type
     */
    public void setDataType(int dataType) {
        this.dataType = dataType;
    }

    /**
     * Get the base URL
     * @return the URL
     */
    public String getBaseURL() {
        return this.baseURL;
    }

    /**
     * Set the base URL
     * @param baseURL the URL
     */
    public void setBaseURL(String baseURL) {
        this.baseURL = baseURL;
    }

    /**
     * Get the HTTP method
     * @return the method
     */
    public int getMethod() {
        return method;
    }

    /**
     * Set the HTTP method
     * @param method the method
     */
    public void setMethod(int method) {
        this.method = method;
    }

    /**
     * Add a HTTP header
     * @param name the header name
     * @param value the header value
     */
    public void addHeader(String name, String value) {
        headers.put(name, value);
    }

    /**
     * Remove a header
     * @param name the name of the HTTP header to remove
     */
    public void removeHeader(String name) {
        headers.remove(name);
    }

    /**
     * Add a GET argument
     * @param name the name of the GET argument
     * @param value the value of the GET argument
     */
    public void addArgument(String name, String value) {
        arguments.put(name, value);
    }

    /**
     * Remove a GET argument
     * @param name the name of the GET argument to remove
     */
    public void removeArgumenr(String name) {
        arguments.remove(name);
    }

    /**
     * Get the POST data
     * @return the POST data
     */
    public String getPostData() {
        return postData;
    }

    /**
     * Set the POST data
     * @param postData the POST data
     */
    public void setPostData(String postData) {
        this.postData = postData;
    }

}
