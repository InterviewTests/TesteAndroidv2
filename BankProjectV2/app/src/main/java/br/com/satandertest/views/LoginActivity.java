package br.com.satandertest.views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.RequestBody;

import org.json.JSONException;
import org.json.JSONObject;

import br.com.satandertest.R;
import br.com.satandertest.dao.BancoLocal;
import br.com.satandertest.models.UserAccount;
import br.com.satandertest.utils.AdministratorRequests;
import br.com.satandertest.utils.Utilities;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class LoginActivity extends AppCompatActivity {

    //Inputs
    private EditText mUser;
    private EditText mPassword;

    //Buttons
    private Button mLogin;

    //Loader
    private RelativeLayout mLoad;

    //Controle de requisição
    private UserAccount mUserAccount;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initViews();
        initActions();
    }

    //Inicializando views
    private void initViews() {

        mUser = (EditText) findViewById(R.id.tv_user);
        mPassword = (EditText) findViewById(R.id.tv_password);
        mLogin = (Button) findViewById(R.id.btn_login);
        mLoad = (RelativeLayout) findViewById(R.id.all_load);
    }

    //Inicializando métodos de ação
    private void initActions() {

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Validando o login
                String error = validateLogin(mUser.getText().toString(), mPassword.getText().toString());
                if (error.equals("")) {
                    doLogin(mUser.getText().toString(), mPassword.getText().toString());
                } else {
                    Toast.makeText(LoginActivity.this, error, Toast.LENGTH_LONG)
                            .show();
                }
            }
        });
    }

    private String validateLogin(String user, String password) {

        //Verificando user
        if (user.length() <= 0) {
            return "Informe um usuário";
        }

        //Verificando senha (uma letra maiuscula, um caracter especial e um caracter alfanumérico)
        return Utilities.checkString(password);

    }

    public void doLogin(String user, String password) {

        //Exibindo loader
        mLoad.setVisibility(View.VISIBLE);

        //Inicializando account com null, caso a requisição não seja sucesso ele permanecerá com valor nulo
        //então assim poderemos no fimDaThread se a requisição pelo menos obteve resposta
        mUserAccount = null;

        final RequestBody formBody = new FormEncodingBuilder()
                .add("user", user)
                .add("password", password)
                .build();

        AdministratorRequests.postFormBody(
                formBody,
                "https://bank-app-test.herokuapp.com/api/login",
                new AdministratorRequests.RespostaRequisicao() {
                    @Override
                    public void respostaSucesso(String body) {
                        super.respostaSucesso(body);

                        System.out.println("RESPOSTA LOGIN: " + body);
                        mUserAccount = mountLoginResponse(body);

                    }

                    @Override
                    public void fimDaThread() {
                        super.fimDaThread();

                        //Aqui chamamos a UIThread
                        //Apenas ela pode atualizar componetes de tela no Android
                        Utilities.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                //Testamos se o objeto userAccount é nulo, o que significaria que a requisição não trouxe uma resposta
                                if (mUserAccount == null) {
                                    Toast.makeText(LoginActivity.this, R.string.conn_error, Toast.LENGTH_LONG).show();
                                } else {

                                    //Requisição obteve resposta, userAccount foi povoado
                                    //Inicializando banco local e salvando resposta
                                    BancoLocal bancoLocal = new BancoLocal(LoginActivity.this);
                                    bancoLocal.saveUserAccount(mUserAccount);

                                    //Direcionando para
                                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                    finish();

                                }

                                //Esconendo loader
                                mLoad.setVisibility(View.GONE);


                            }
                        });
                    }
                }
        );

    }

    private UserAccount mountLoginResponse(String body) {

        UserAccount userAccount = new UserAccount();

        try {

            JSONObject userAccountJsonObject = new JSONObject(body).getJSONObject("userAccount");
            userAccount.setUserId(userAccountJsonObject.getInt("userId"));
            userAccount.setName(userAccountJsonObject.getString("name"));
            userAccount.setBankAccount(userAccountJsonObject.getString("bankAccount"));
            userAccount.setAgency(userAccountJsonObject.getString("agency"));
            userAccount.setBalance(userAccountJsonObject.getString("balance"));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return userAccount;
    }

}
