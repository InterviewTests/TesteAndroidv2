package com.accenture.android.app.testeandroid.data.http.responses.generics;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Denis Magno on 09/07/2020.
 * denis_magno16@hotmail.com
 */
public class ErrorResponse {
    private Integer statusCode;
    private String message;

    public ErrorResponse() {
    }

    public ErrorResponse(Integer statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessageFormat() {
        String error = "";
        try {
            JSONObject json = new JSONObject(this.message);
            error = json.getString("error");
        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        return error;
    }
}
