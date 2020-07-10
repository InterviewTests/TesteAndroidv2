package com.accenture.android.app.testeandroid.data.http.responses.generics;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Denis Magno on 09/07/2020.
 * denis_magno16@hotmail.com
 */
public class ErrorResponse {
    private Integer code;
    private String message;

    public ErrorResponse() {
    }

    public ErrorResponse(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getStatusCode() {
        return code;
    }

    public void setStatusCode(Integer code) {
        this.code = code;
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
