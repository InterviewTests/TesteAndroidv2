package br.com.edrsantos.santandertesteandroidv2.activitys;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.orhanobut.hawk.Hawk;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.edrsantos.santandertesteandroidv2.R;
import br.com.edrsantos.santandertesteandroidv2.util.Cpf;
import br.com.edrsantos.santandertesteandroidv2.util.Util;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity {

    private String TAG = getClass().getSimpleName();
    private final String REGEX_PASS_STRONG = "(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])";
    private String API_LOGIN = "https://bank-app-test.herokuapp.com/api/login";
    private EditText edtUser;
    private EditText edtPass;
    private TextView btnLogin;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUser = findViewById(R.id.edtUser);
        edtPass = findViewById(R.id.edtPass);
        btnLogin = findViewById(R.id.btnLogin);

        progressBar = (ProgressBar) findViewById(R.id.pbLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View v) {
                btnLogin.setEnabled(false);
                if(checkFields()){
                    progressBar.setVisibility(View.VISIBLE);
                    new Thread(new Runnable() {
                        public void run() {
                            if(isInternetAvailable())
                                login();
                        }

                    }).start();
                }
            }
        });

        Hawk.init(this).build();
    }

    @Override
    protected void onResume() {
        super.onResume();

        String userName = Hawk.get(getString(R.string.app_name), "");
        edtUser.setText(userName);
    }

    private boolean checkFields(){

        String user = edtUser.getText().toString().trim();
        String pass = edtPass.getText().toString();
        boolean isValid;

        if(Cpf.isValid(user) || isValidEmail(user))
            isValid = true;
        else {
            edtUser.setError(getString(R.string.msg_error_user));
            Util.showToast(this, getString(R.string.msg_error_user));
            isValid = false;
            btnLogin.setEnabled(true);
        }

        if(!isValidPassword(pass)){
            edtPass.setError(getString(R.string.msg_error_pass));
            Util.showToast(this, getString(R.string.msg_error_pass));
            isValid = false;
            btnLogin.setEnabled(true);
        }

        return isValid;
    }

    private boolean isValidPassword(final String password){
        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = REGEX_PASS_STRONG;
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.find();
    }

    /*
     * Fonte: https://stackoverflow.com/questions/36574183/how-to-validate-password-field-in-android/50267465#50267465
     */
    private boolean isValidEmail(CharSequence target) {
        if (target == null)
            return false;

        return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    /*
     * Fonte: https://stackoverflow.com/questions/9570237/android-check-internet-connection/16124915#16124915
     */
    public boolean isInternetAvailable() {
        try {
            final InetAddress address = InetAddress.getByName("www.google.com");
            return !address.equals("");
        } catch (UnknownHostException e) {
            Util.showToast(this,"Verifique sua conexão de Internet!");
            Log.d(TAG, e.getMessage());
        }
        btnLogin.setEnabled(true);
        return false;
    }

    private void login() {
        final String userName = edtUser.getText().toString().trim();
        final String pass = edtPass.getText().toString();

        final OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType, "user="+userName+"&password="+pass);
        Request request = new Request.Builder()
                .url(API_LOGIN)
                .post(body)
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                progressBar.setVisibility(View.GONE);
                Log.d(TAG, e.getMessage());
                Util.showToast(LoginActivity.this, "Ocorreu um erro ao conectar!");
                btnLogin.setEnabled(true);
            }
            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String myResponse = response.body().string();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {

                                JSONObject json = new JSONObject(myResponse);
                                JSONObject userAccount = json.getJSONObject("userAccount");
                                String name = userAccount.getString("name");
                                String bankAccount = userAccount.getString("bankAccount");
                                String agency = userAccount.getString("agency");
                                Double balance = Double.parseDouble(userAccount.getString("balance"));

                                Hawk.put(getString(R.string.app_name), userName);

                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                Bundle bundle = new Bundle();
                                bundle.putString("nome", name);
                                bundle.putString("bankAccount", bankAccount);
                                bundle.putString("agency", agency);
                                bundle.putDouble("balance", balance);
                                intent.putExtras(bundle);
                                startActivity(intent);

                                finish();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                } else {
                    LoginActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                progressBar.setVisibility(View.GONE);

                                Log.d(TAG, response.message());
                                Util.showToast(LoginActivity.this, "Ocorreu um erro ao conectar!");
                                btnLogin.setEnabled(true);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        });
    }

}
