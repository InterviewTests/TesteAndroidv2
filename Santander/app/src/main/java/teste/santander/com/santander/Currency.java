package teste.santander.com.santander;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

interface CurrencyInput {
    void displayCurrencyMetaData(StatementAdapter statementAdapter);
}

public class Currency extends AppCompatActivity implements CurrencyInput {

    ProgressDialog nDialog;

    static ClientModel cm = new ClientModel();
    ListView listView;
    CurrencyInteractorInput output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        nDialog = new ProgressDialog(this);
        nDialog.setMessage("Saindo..");
        nDialog.setTitle("Por favor aguarde");
        nDialog.setIndeterminate(false);
        nDialog.setCancelable(true);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency);

        CurrencyConfigurator.INSTANCE.configure(this);

        // Init
        SecurePreferences preferences = new SecurePreferences(this, "user-secure-data", "SantBank23@4", true);

        String user = preferences.getString("lastUser");
        Log.d("useruser", user);
        listView = findViewById(R.id.lv);
        statementData();
        fetchCurrencyMetaData();
    }

    public void fetchCurrencyMetaData() {
        CurrencyRequest cr = new CurrencyRequest();
        cr.userId = cm.getUserId();
        output.fetchCurrencyMetaData(cr);
    }

    public void setData(ClientModel clientModel)
    {
        cm.setName(clientModel.getName());
        cm.setAgency(clientModel.getAgency());
        cm.setBalance(clientModel.getBalance());
        cm.setUserId(clientModel.getUserId());
        cm.setBankAccount(clientModel.getBankAccount());

    }

    @Override
    public void onBackPressed() {
        onExit();
    }

    public void onExit() {
        new AlertDialog.Builder(Currency.this)
                .setTitle("Sair")
                .setMessage("Deseja realmente sair?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        nDialog.show();
                        finish();
                        System.exit(0);
                    }

                })
                .setNegativeButton("Não", null)
                .show();
    }

    public void statementData(){
        TextView textView4 = (TextView) findViewById(R.id.textView4);
        TextView textView6 = (TextView) findViewById(R.id.textView6);
        TextView textView8 = (TextView) findViewById(R.id.textView8);
        textView4.setText(cm.getName());
        textView6.setText(cm.getBankAccount() + " / " + cm.getAgency());

        Double d = cm.getBalance();
        Locale ptBr = new Locale("pt", "BR");
        String valorString = NumberFormat.getCurrencyInstance(ptBr).format(d);
        System.out.println(valorString);

        textView8.setText(valorString);

    }

    public void goBack(View v) {
        new AlertDialog.Builder(v.getContext())
                .setTitle("Sair")
                .setMessage("Deseja realmente sair?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        nDialog.show();
                        finish();
                        System.exit(0);
                    }

                })
                .setNegativeButton("Não", null)
                .show();
    }


    @Override
    public void displayCurrencyMetaData(StatementAdapter statementAdapter) {
        listView.setAdapter(statementAdapter);
    }
}
