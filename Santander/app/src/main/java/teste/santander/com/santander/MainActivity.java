package teste.santander.com.santander;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

interface MainActivityInput {
    void displayMainMetaData();
}

public class MainActivity extends AppCompatActivity implements MainActivityInput {

    private EditText usernameEt;
    private EditText passwordEt;
    ProgressDialog nDialog;
    MainInteractorInput output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainConfigurator.INSTANCE.configure(this);

        EditText username = (EditText) findViewById(R.id.username);
        SecurePreferences preferences = new SecurePreferences(this, "user-secure-data", "SantBank23@4", true);

        String user = preferences.getString("lastUser");
        username.setText(user);
        bindView();
    }

    @Override
    public void onResume() {
        super.onResume();
        passwordEt.setText("");
    }

    public void bindView(){
        usernameEt = (EditText) findViewById(R.id.username);
        passwordEt = (EditText) findViewById(R.id.password);
    }

    public void onBtnClick(View v) {
        fetchMetaData();
    }

    public void fetchMetaData() {
        MainRequest mr = new MainRequest();
        mr.user = usernameEt;
        mr.pass = passwordEt;

        output.fetchMainMetaData(mr);
    }


    @Override
    public void displayMainMetaData() {
        EditText username = (EditText) findViewById(R.id.username);
        SecurePreferences preferences = new SecurePreferences(this, "user-secure-data", "SantBank23@4", true);

        String user = preferences.getString("lastUser");
        username.setText(user);
    }
}
