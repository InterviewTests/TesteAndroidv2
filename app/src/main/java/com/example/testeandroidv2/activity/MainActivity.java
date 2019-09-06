package com.example.testeandroidv2.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.testeandroidv2.R;
import com.example.testeandroidv2.helper.UsuarioDAO;
import com.example.testeandroidv2.model.Usuario;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private EditText loginUser;
    private EditText loginPsw;
    private Button btnLogin;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginUser = findViewById(R.id.editTextUser);
        loginPsw = findViewById(R.id.editPassword);
        btnLogin = findViewById(R.id.btnLogin);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);

        UsuarioDAO usuarioDAO = new UsuarioDAO(getApplicationContext());
        JSONObject temUsuario = null;
        try {

            temUsuario = (JSONObject) usuarioDAO.ultimoLogin();
            if(temUsuario.length() > 0) {

                String login = temUsuario.getString("login");
                String psw = temUsuario.getString("lastPassword");
                loginUser.setText(login);
                loginPsw.setText(psw);
            }

            btnLogin.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    final String u = loginUser.getText().toString();
                    final String p = loginPsw.getText().toString();

                    progressBar.setVisibility(View.VISIBLE);
                    btnLogin.setVisibility(View.GONE);

                    try {

                        if(u.equals(""))
                            throw new Exception("Login não informado!");
                        else {

                            Boolean login = false;
                            if (validateEmail(u))// valida se é email
                                login = true;
                            if(validateCPF(u)) // valida CPF
                                login = true;

                            if(!login)
                                throw new Exception("Login deve ser E-mail/CPF");

                        }


                        if (p.equals(""))
                            throw new Exception("Senha não informada!");


                        ArrayList a_erros = validarSenha(p);
                        String erros = TextUtils.join("\n", a_erros);
                        if(erros.length() > 0)
                            throw new Exception(erros);


                        OkHttpClient httpClient = new OkHttpClient();

                        RequestBody formBody = new FormBody.Builder()
                                .add("user", u)
                                .add("password", p)
                                .build();
                        Request request = new Request.Builder()
                                .url("https://bank-app-test.herokuapp.com/api/login")
                                .post(formBody)
                                .build();

                        httpClient.newCall(request).enqueue(new Callback() {

                            @Override
                            public void onFailure(Call call, IOException e) {
                                e.printStackTrace();
                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {

                                if(response.isSuccessful()) {

                                    String jsonData = response.body().string();
                                    try {

                                        JSONObject Jobject = new JSONObject(jsonData);
                                        JSONObject userAccount = Jobject.getJSONObject("userAccount");

                                        userAccount.put("login", u);
                                        userAccount.put("lastPassword", p);
                                        dadosUsuario(userAccount);


                                        String userId = userAccount.getString("userId");
                                        String nome = userAccount.getString("name");
                                        String bankAccount = userAccount.getString("bankAccount");
                                        String agency = userAccount.getString("agency");
                                        String balance = userAccount.getString("balance");


                                        Intent intent = new Intent(getApplicationContext(), LancamentoActivity.class);
                                        intent.putExtra("userId", userId);
                                        intent.putExtra("nome", nome);
                                        intent.putExtra("bankAccount", bankAccount);
                                        intent.putExtra("agency", agency);
                                        intent.putExtra("balance", balance);

                                        startActivity(intent);
                                        finish();

                                    }
                                    catch (JSONException e) {
                                        e.getMessage();
                                    }

                                }

                            }
                        });

                    }
                    catch (Exception e){

                        e.getMessage();
                        alerta(false, "Erro", e.getMessage());

                    }

                }
            });


        } catch (Exception e) {
            alerta(false, "Erro", e.getMessage());
        }

    }

    public ArrayList validarSenha(String senha) {

        ArrayList<String> msgs = new ArrayList<>();

        if(senha.length() < 3)
            msgs.add("A senha deve conter pelo menos 3 caracteres");

        boolean achouNumero = false;
        boolean achouMaiuscula = false;
        boolean achouMinuscula = false;
        boolean achouSimbolo = false;

        for (char c : senha.toCharArray()) {

            if (c >= '0' && c <= '9')
                achouNumero = true;
            else if (c >= 'A' && c <= 'Z')
                achouMaiuscula = true;
            else if (c >= 'a' && c <= 'z')
                achouMinuscula = true;
            else
                achouSimbolo = true;

        }
        if (!achouNumero)
            msgs.add("Utilize pelo menos 1 numero");
        if (!achouMaiuscula)
            msgs.add("Utilize pelo menos 1 letra maiuscula");
        if (!achouMinuscula)
            msgs.add("Utilize pelo menos 1 letra minuscula");
        if (!achouSimbolo)
            msgs.add("Utilize pelo menos 1 caracter especial");

        return msgs;

    }

    public void dadosUsuario(JSONObject userAccount) throws JSONException {

        Usuario usuario = new Usuario();
        UsuarioDAO usuarioDAO = new UsuarioDAO(getApplicationContext());

        Integer userId = Integer.parseInt(userAccount.getString("userId"));
        String nome = userAccount.getString("name");
        String login = userAccount.getString("login");
        String lastPassword = userAccount.getString("lastPassword");
        String agency = userAccount.getString("agency");
        String bankAccount = userAccount.getString("bankAccount");

        usuario.setId(userId);
        usuario.setNome(nome);
        usuario.setLogin(login);
        usuario.setLastPassword(lastPassword);
        usuario.setAgency(agency);
        usuario.setBankAccount(bankAccount);

        if(userId != null) {

            JSONObject existeUsuario = (JSONObject) usuarioDAO.buscar(userId);
            if(existeUsuario.length() == 0) {

                if(usuarioDAO.create(usuario) != true)
                    alerta(false, "Erro", "Erro ao buscar usuário");

            }
            else {

                if(!nome.isEmpty())
                    if(usuarioDAO.update(usuario) != true)
                        alerta(false, "Erro", "Erro ao buscar usuário");

            }

        }
        else {

            if(!nome.isEmpty())
                if(usuarioDAO.create(usuario))
                    alerta(false, "Erro", "Erro ao buscar usuário");

        }

    }

    public void alerta(Boolean status, String title, String desc) {

        progressBar.setVisibility(View.GONE);
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);

        dialog.setCancelable(false);
        if(status)
            dialog.setIcon(android.R.drawable.ic_dialog_info);
        else
            dialog.setIcon(android.R.drawable.ic_dialog_alert);

        dialog.setTitle(title);
        dialog.setMessage("\n"+desc);

        // BTNs
        dialog.setPositiveButton("OK!", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                loginPsw.requestFocus();
            }

        });


        dialog.create();
        dialog.show();
        btnLogin.setVisibility(View.VISIBLE);

    }

    public static boolean validateCPF(String CPF) {

        if (CPF.equals("00000000000") || CPF.equals("11111111111")
                || CPF.equals("22222222222") || CPF.equals("33333333333")
                || CPF.equals("44444444444") || CPF.equals("55555555555")
                || CPF.equals("66666666666") || CPF.equals("77777777777")
                || CPF.equals("88888888888") || CPF.equals("99999999999")) {
            return false;
        }

        char dig10, dig11;
        int sm, i, r, num, peso;

        try {

            sm = 0;
            peso = 10;

            for (i = 0; i < 9; i++) {

                num = (int) (CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;

            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig10 = '0';
            else
                dig10 = (char) (r + 48);

            sm = 0;
            peso = 11;

            for (i = 0; i < 10; i++) {

                num = (int) (CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;

            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig11 = '0';
            else
                dig11 = (char) (r + 48);

            if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)))
                return (true);
            else
                return (false);

        }
        catch (Exception erro) {
            return (false);
        }

    }

    public final static boolean validateEmail(String txtEmail) {

        if (TextUtils.isEmpty(txtEmail))
            return false;
        else
            return android.util.Patterns.EMAIL_ADDRESS.matcher(txtEmail).matches();

    }

}
