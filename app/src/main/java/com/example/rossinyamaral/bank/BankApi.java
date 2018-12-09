package com.example.rossinyamaral.bank;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.rossinyamaral.bank.model.UserAccountModel;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rossinyamaral on 08/12/18.
 */

public class BankApi {

    private static final String LOGTAG = BankApi.class.getSimpleName();
    private static final int MY_SOCKET_TIMEOUT_MS = 10000;
    private static final String USER_PARAM = "user";
    private static final String PASSWORD_PARAM = "password";
    private Context context;
    private Gson gson;

    private Uri uri = Uri.parse(BuildConfig.SERVICE_BASE_URL);

    public BankApi() {
        //this.context = context;
        gson = new Gson();
    }

    private void doRequest(int requestMethod, String url, final Map<String, String> map, final Response.Listener<JSONObject> listener, final Response.ErrorListener errorListener) {
        Log.d(LOGTAG, url + " " + map.toString());
        StringRequest request = new StringRequest(requestMethod, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.d(LOGTAG, response);
                            JSONObject jsonObject = new JSONObject(response);
                            listener.onResponse(jsonObject);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(LOGTAG, error.toString());
                        errorListener.onErrorResponse(error);
                    }
                }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Content-Type", "application/x-www-form-urlencoded");
                params.put("cache-control", "no-cache");
                params.put("Postman-Token", "c935d6a8-0dfa-4363-9fff-d77647765165");
                return params;
            }

            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded";
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return map;
            }
        };

        request.setRetryPolicy(new DefaultRetryPolicy(
                MY_SOCKET_TIMEOUT_MS,
                3,
                0));

        BankApplication.getInstance().addToRequestQueue(request);
    }


    public void login(String user, String password, final ApiCallback<UserAccountModel> callback) {

        try {
            Map<String, String> map = new HashMap<>();
            map.put(USER_PARAM, user);
            map.put(PASSWORD_PARAM, password);
            Uri uri = this.uri.buildUpon().appendPath("login").build();
            Response.Listener<JSONObject> okListener = new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        if (callback != null) {
                            if (!response.isNull("error")) {
                                JSONObject jsonError = response.getJSONObject("error");
                                ErrorResponse error = gson.fromJson(jsonError.toString(), ErrorResponse.class);
                                callback.onError(error);
                                return;
                            }

                            JSONObject jsonObject = response.getJSONObject("userAccount");
                            UserAccountModel userAccountModel = gson.fromJson(jsonObject.toString(), UserAccountModel.class);

                            callback.onSuccess(userAccountModel);
                        }
                    } catch (Exception e) {
                        ErrorResponse errorResponse = new ErrorResponse();
                        errorResponse.code = -1;
                        errorResponse.message = "Ops! An errorResponse has occurred";
                        callback.onError(errorResponse);
                    }
                }
            };
            Response.ErrorListener errorListener = new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    if (callback != null) {
                        ErrorResponse errorResponse = new ErrorResponse();
                        errorResponse.code = -1;
                        errorResponse.message = error.getMessage();
                        callback.onError(errorResponse);
                    }
                }
            };
            doRequest(Request.Method.POST, uri.toString(), map, okListener, errorListener);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public interface ApiCallback<T> {
        void onSuccess(T object);

        void onError(ErrorResponse message);
    }

    public class ErrorResponse {
        int code;
        String message;
    }

}
