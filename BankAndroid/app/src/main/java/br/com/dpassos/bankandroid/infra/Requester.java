package br.com.dpassos.bankandroid.infra;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Requester {

    private String doRequest(String endpoint, String verb, String params) throws Exception{
        StringBuffer strB = new StringBuffer();
        URL url = new URL(endpoint);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod(verb);
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

        if(params!= null) {
            connection.setDoOutput(true);
            connection.getOutputStream().write(params.getBytes());
        }

        InputStream is = connection.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(is);
        int byteLido;
        while ((byteLido = inputStreamReader.read())!= -1) {
            strB.append((char)byteLido);
        }
        inputStreamReader.close();
        return strB.toString();
    }


    public String doPost(String endpoint, String params) throws Exception{
        return doRequest(endpoint, "POST", params);
    }

    public String doGet(String endpoint) throws Exception{
        return doRequest(endpoint, "GET", null);
    }
}
