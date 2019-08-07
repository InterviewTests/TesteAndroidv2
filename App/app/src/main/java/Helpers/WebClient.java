package Helpers;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import Domain.UserAccount;
import Padrões.ValoresPadrao;

public class WebClient {
    private UserAccount userAccount;
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
            return resposta;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public UserAccount createUserAccount(JSONObject userAccount){
        this.userAccount = new UserAccount();
        try {
            this.userAccount.setUserId(userAccount.getInt("userId"));
            this.userAccount.setName(userAccount.getString("name"));
            this.userAccount.setAgency(userAccount.getString("agency"));
            this.userAccount.setBankAccount(userAccount.getString("bankAccount"));
            this.userAccount.setBalance(userAccount.getDouble("balance"));
            System.out.println(this.userAccount.getBalance());
            return this.userAccount;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

}
