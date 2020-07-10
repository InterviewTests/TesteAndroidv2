package com.accenture.android.app.testeandroid.data.http.helpers;

import com.accenture.android.app.testeandroid.data.http.responses.generics.ErrorResponse;

import java.io.IOException;
import java.net.ConnectException;
import java.net.UnknownHostException;

import retrofit2.Response;

/**
 * Created by Denis Magno on 09/07/2020.
 * denis_magno16@hotmail.com
 */
public class ResponseHelper {
    public static ErrorResponse createResponse(Response<?> response) {
        ErrorResponse errorResponse = new ErrorResponse();

        if (response.errorBody() != null) {
            String messageErrorBody = "";

            try {
                messageErrorBody = response.errorBody().string();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (!messageErrorBody.isEmpty()) {
                errorResponse = new ErrorResponse(0, messageErrorBody);
            } else {
                errorResponse.setStatusCode(response.code());
                errorResponse.setMessage(StatusCode.switchStatusCodeToJson(response.code()));
            }

        } else {
            errorResponse.setStatusCode(response.code());
            errorResponse.setMessage(StatusCode.switchStatusCodeToJson(response.code()));
        }

        return errorResponse;
    }

    public static ErrorResponse createResponse(Throwable t) {
        ErrorResponse errorResponse;

        if (t instanceof UnknownHostException) {
            errorResponse = new ErrorResponse(2, "Erro: " + t.getMessage() + "\n\nContate o administrador.");
        } else if (t.getCause() instanceof ConnectException) {
            errorResponse = new ErrorResponse(1, "Erro: " + t.getMessage() + "\n\nVerifique sua conexão com a internet.");
        } else if (t.getMessage() == null) {
            errorResponse = new ErrorResponse(0, "Erro: " + t.toString() + "\n\nContate o administrador.");
        } else {
            errorResponse = new ErrorResponse(0, "Erro: " + t.getMessage() + "\n\nContate o administrador.");
        }

        return errorResponse;
    }
}
