package com.desafiosantander.Home;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class HomeModel {
}

class HomeViewModel {
    //filter to have only the needed data
    String userValidation;
    String passwordValidation;

}

class HomeRequest extends AsyncTask<String, Void, String> {

    JSONObject postData;
    HomeInteractorInput callback;

    public HomeRequest(Map<String, String> postData, HomeInteractorInput callback) {
        if (postData != null) {
            this.postData = new JSONObject(postData);
            this.callback = callback;
        }
    }

    @Override
    protected String doInBackground(String... params) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String response = null;

        try {
            URL url = new URL(params[0]);
            Log.d("doInBackground", postData.toString().substring(1, postData.toString().length() - 1));

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            urlConnection.setRequestProperty("Content-type", "application/json");
            urlConnection.setRequestMethod("POST");

            if (this.postData != null) {
                OutputStreamWriter writer = new OutputStreamWriter(urlConnection.getOutputStream());
                writer.write(postData.toString());
                writer.flush();
            }
            int statusCode = urlConnection.getResponseCode();
            Log.d("doInBackground", "statusCode: " + statusCode);
            if (statusCode == 200) {
                Log.d("doInBackground", "statusCode: 200");
                InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());
                response = convertInputStreamToString(inputStream);

                Log.d("Responseeee", response);
                callback.getResponse(true, response);

            } else {

                callback.getResponse(false, null);
                // Status code is not 200
                // Do something to handle the error
            }

        } catch (Exception e) {

        }
        return response;
    }
    private String convertInputStreamToString(InputStream inputStream) {
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            while((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}

class HomeResponse {

}
