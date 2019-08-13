package br.com.giovanni.testebank.Interactor;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DaoLogin {

    private static String readStream(InputStream in){
        BufferedReader r = new BufferedReader(new InputStreamReader(in));
        StringBuilder total = new StringBuilder();
        String line;

        try {
            while ((line = r.readLine()) != null) {
                total.append(line).append('\n');
            }
        }catch(Exception ex) {
            ex.printStackTrace();
        }
        return total.toString();
    }

    private static String request(String stringUrl){
        URL url = null;
        HttpURLConnection urlConnection = null;
        try {
            url = new URL(stringUrl);
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            return readStream(in);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            urlConnection.disconnect();
        }
        return "";
    }

    public static LoginRequest loginRequest (String user, String password) throws JSONException {
        String loginAcces = request("https://bank-app-test.herokuapp.com/api/login" + user + password);
        JSONObject obj = new JSONObject(loginAcces);

        String getUSer = obj.getString("name");
        String getBankAccount = obj.getString("bankAccount");
        String getAgency = obj.getString("agency");
        double getBalance = obj.getDouble("balance");

        return new LoginRequest(getBankAccount,getAgency,getBalance);
    }

}
