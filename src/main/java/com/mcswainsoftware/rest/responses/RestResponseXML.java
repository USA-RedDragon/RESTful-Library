package com.mcswainsoftware.rest.responses;

import org.w3c.dom.Document;

/**
 * An XML response from a Rest request
 */
public class RestResponseXML extends RestResponse {

    /**
     * The data container holding the response
     */
    private Document content;

    /**
     * The type of data this response represents
     */
    private int type = RestResponse.TYPE_XML;

    /**
     * Create an XML response
     * @param content the Document representing your XML
     */
    public RestResponseXML(Document content) {
        this.content = content;
    }

    /**
     * Get the response's XML Document
     * @return a Document representing your XML
     */
    @Override
    public Document getContent() {
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
