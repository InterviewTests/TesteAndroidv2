package br.com.giovanni.testebank.Services;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;


public class StatementsWebService {

    public String get(int idUser) {

        try {
            URL url = new URL("https://bank-app-test.herokuapp.com/api/statements/" + idUser);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);


            Scanner scanner = new Scanner(connection.getInputStream());
            String resposta = scanner.nextLine();

            while (scanner.hasNext()) {
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