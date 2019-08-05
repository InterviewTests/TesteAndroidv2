package Helpers;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

public class LoginTask extends AsyncTask {
    private String json;
    private Context ctx;
    private String resposta;
    public LoginTask(String json){
        this.json = json;
    }

    public void setContext(Context ctx){
        this.ctx = ctx;
    }
    @Override
    protected Object doInBackground(Object[] objects) {
        WebClient client = new WebClient();
        resposta = client.get(json);
        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        Toast.makeText(ctx, ""+resposta, Toast.LENGTH_SHORT).show();
    }
}
