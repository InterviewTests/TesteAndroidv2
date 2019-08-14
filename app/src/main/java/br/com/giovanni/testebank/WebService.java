package br.com.giovanni.testebank;


import android.os.AsyncTask;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import br.com.giovanni.testebank.Presenter.UserModel;

public class WebService extends AsyncTask <Void, Void, UserModel> {

    private final String user;

    public WebService(String getUser) {
        this.user = getUser;
    }


    @Override
    protected UserModel doInBackground(Void... voids) {
        StringBuilder resposta = new StringBuilder();

        if (this.user != null ){

            try {
                URL url = new URL("https://bank-app-test.herokuapp.com/api/login");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Content-type","application/json");
                connection.setDoOutput(true);
                connection.setConnectTimeout(5000);
                connection.connect();

                Scanner scanner = new Scanner(url.openStream());
                while (scanner.hasNext()) {
                    resposta.append(scanner.next());
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new Gson().fromJson(resposta.toString(), UserModel.class);
    }
}