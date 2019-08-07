package Helpers;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import Domain.UserAccount;

public class LoginTask extends AsyncTask {
    private String json;
    private Context ctx;
    private String resposta;
    private WebClient client;
    public LoginTask(String json){
        this.json = json;
    }

    public void setContext(Context ctx){
        this.ctx = ctx;
    }
    @Override
    protected Object doInBackground(Object[] objects) {
        client = new WebClient();
        resposta = client.get(json);
        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        try {
            JSONObject userAccount = new JSONObject(resposta).getJSONObject("userAccount");
            JSONObject error = new JSONObject(resposta).getJSONObject("error");
            if (error.toString().contentEquals("{}")) {
                Toast.makeText(ctx, userAccount.get("name").toString(), Toast.LENGTH_SHORT).show();
                client.createUserAccount(userAccount);
                // Criar intent para chamar nova activity
            } else
                Toast.makeText(ctx,error.get("message").toString(), Toast.LENGTH_SHORT).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
