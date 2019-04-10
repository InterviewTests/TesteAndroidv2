package com.desafiosantander.DashBoard;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class DashBoardModel {
}

class DashBoardViewModel {
    //filter to have only the needed data
    List<Statement> statementList;

}

class DashBoardRequest  extends AsyncTask<String, Void, String> {

    DashBoardInteractor callback;
    public DashBoardRequest(DashBoardInteractor callback){
        this.callback = callback;
    }

    @Override
    protected String doInBackground(String... params) {


        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String response = null;
        String inputLine;

        try {
            URL url = new URL(params[0]);
            Log.d("doInBackground",params[0]);

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            int statusCode = urlConnection.getResponseCode();
            Log.d("doInBackground","statusCode: "+statusCode);

            if (statusCode ==  200) {
                Log.d("doInBackground","statusCode: 200");
                InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());
                response = convertInputStreamToString(inputStream);

                Log.d("Responseeee",response);
                callback.getResponse(true,response);

            } else {

                callback.getResponse(false,null);
                // Status code is not 200
                // Do something to handle the error
            }


        } catch (IOException e){
            e.printStackTrace();
            response = null;
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

class DashBoardResponse {

}
