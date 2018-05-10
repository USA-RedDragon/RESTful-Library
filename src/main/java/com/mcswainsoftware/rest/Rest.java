package com.mcswainsoftware.rest;

import android.os.AsyncTask;

import com.mcswainsoftware.rest.responses.RestResponse;
import com.mcswainsoftware.rest.responses.RestResponseJSON;
import com.mcswainsoftware.rest.responses.RestResponsePlainText;
import com.mcswainsoftware.rest.responses.RestResponseXML;

import org.json.JSONTokener;
import org.w3c.dom.Document;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * The main class for the REST library
 *
 * You will create a {@link RestResponse} and an {@link OnRestCompletedReceiver} to give to executeRequest
 */
public class Rest {

    /**
     * Run a REST request
     * @param request The object containing a REST API request
     * @param receiver The callback for when the REST call is done
     */
    public static void executeRequest(RestRequest request, OnRestCompletedReceiver receiver) {
        RestRequestTask task = new RestRequestTask();
        task.setOnRestCompletedReceiver(receiver);
        task.execute(request);
    }

    /**
     * The callback for a rest request
     */
    public interface OnRestCompletedReceiver {

        /**
         * Callback method, must be overridden
         * @param response an object representing your response
         */
        void onRestCompleted(RestResponse response);
    }

    /**
     * The background task that preforms the REST transaction
     */
    private static class RestRequestTask extends AsyncTask<RestRequest, Void, RestResponse> {

        /**
         * The callback
         */
        private OnRestCompletedReceiver receiver;

        /**
         * Do the request
         * @param requests the Request to preform
         * @return the response from the server
         */
        @Override
        protected RestResponse doInBackground(RestRequest... requests) {
            RestResponse response = null;
            HttpURLConnection conn = null;
            try {
                final RestRequest req = requests[0];

                String baseUrlString = req.getBaseURL() + "?";
                String urlString = "";
                for (HashMap.Entry<String, String> param : req.getArguments().entrySet()) {
                    if (urlString.length() != 0) urlString += '&';
                    urlString += param.getKey() + '=' + param.getValue();
                }
                baseUrlString += urlString;

                URL url = new URL(baseUrlString);
                conn = (HttpURLConnection) url.openConnection();
                if (req.getHttpUsername() != null && req.getHttpPassword() != null) {
                    Authenticator.setDefault(new Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(req.getHttpUsername(), req.getHttpPassword().toCharArray());
                        }
                    });
                }


                for (int i = 0; i < req.getHeaders().size(); i++) {
                    String key = (String) req.getHeaders().keySet().toArray()[i];
                    conn.setRequestProperty(key, req.getHeaders().get(key));
                }

                switch (req.getMethod()) {
                    case RestRequest.METHOD_POST:
                        conn.setRequestMethod("POST");
                        break;
                    case RestRequest.METHOD_GET:
                        conn.setRequestMethod("GET");
                        break;
                }

                if (req.getMethod() == RestRequest.METHOD_POST) {
                    conn.setDoOutput(true);
                    String post = req.getPostData();
                    OutputStream outputStream = new BufferedOutputStream(conn.getOutputStream());
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    writer.write(post);
                    writer.flush();
                    writer.close();
                    outputStream.close();
                }

                conn.connect();

                Scanner s = new Scanner(conn.getInputStream()).useDelimiter("\\A");
                String respon = (s.hasNext() ? s.next() : "");

                switch (req.getDataType()) {
                    case RestRequest.RETURN_TYPE_JSON:
                        Object json = new JSONTokener(respon).nextValue();
                        response = new RestResponseJSON(json);
                        break;
                    case RestRequest.RETURN_TYPE_XML:
                        Document xmlRoot = createXMLDocument(respon);
                        response = new RestResponseXML(xmlRoot);
                        break;
                    case RestRequest.RETURN_TYPE_PLAIN_TEXT:
                        response = new RestResponsePlainText(respon);
                        break;
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if(conn != null) conn.disconnect();
            }
            return response;
        }

        /**
         * Call the callback
         * @param result the response to give the callback
         */
        @Override
        protected void onPostExecute(RestResponse result) {
            receiver.onRestCompleted(result);
        }

        public void setOnRestCompletedReceiver(OnRestCompletedReceiver receiver) {
            this.receiver = receiver;
        }
    }

    /**
     * Helper for parsing XML to a Document object
     * @param xml the String containing the XML
     * @return the formed Document
     */
    private static Document createXMLDocument(String xml) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(false);
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new ByteArrayInputStream(Charset.forName("UTF-8").encode(xml).array()));
            return doc;
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
