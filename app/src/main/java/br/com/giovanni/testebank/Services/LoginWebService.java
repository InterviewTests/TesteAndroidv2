package br.com.giovanni.testebank.Services;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class LoginWebService {
       public String post(String userJson){

            try {
                URL url = new URL("https://bank-app-test.herokuapp.com/api/login");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.setRequestProperty("Content-type","application/json");
                connection.setConnectTimeout(5000);

                OutputStream outputStream = connection.getOutputStream();
                outputStream.write(userJson.getBytes());

                Scanner scanner = new Scanner(connection.getInputStream());
                String resposta = scanner.nextLine();

                while (scanner.hasNext()){
                    resposta += scanner.nextLine();
                }
                scanner.close();

                return resposta;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        return null;
        }
}