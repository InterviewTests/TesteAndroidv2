package Services;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import Padrões.ValoresPadrao;

public class LoginResponse {
    public String get(String userJson){
        try {
            URL url = new URL(ValoresPadrao.URL_LOGIN);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type","application/json");
            OutputStream output = connection.getOutputStream();
            output.write(userJson.getBytes());
            Scanner scanner = new Scanner(connection.getInputStream());
            String resposta = scanner.next();
            while (scanner.hasNext()){
                resposta += scanner.next();
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
