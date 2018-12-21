package teste.claudio.com.testsantander;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import okhttp3.ResponseBody;


public class LoginActivity extends AppCompatActivity {

    private EditText userId;
    private EditText senhaId;
    private Button btnlogin;

    //Variáveis para receber os campos userId, name, bankAccount, agency e balance
    private static Integer userIdRetorno;
    private static String nameRetorno;
    private static String bankAccountRetorno;
    private static String agencyRetorno;
    private static Double balanceRetorno;

    //Nome do arquivo de Preferencias para salvar o ultimo user
    private static final String ARQUIVO_PREFERENCIAS = "ArquivoPreferencias";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //ligar o recurso com o java
        userId = (EditText) findViewById(R.id.editUserId);
        senhaId = (EditText) findViewById(R.id.editSenha);
        btnlogin = (Button) findViewById(R.id.btnLogin);

        //Preferencias - Mostrar o ultimo user salvo
        preferencias();

        //Listener do botão de Login
        listenerBotao();
    }

    public void listenerBotao() {
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Pegar o valor do texto do userId
                String user_id = userId.getText().toString();
                String senha_id = senhaId.getText().toString();

                //Validar o cpf digitado no userId se for todo numerico este campo
                if (Util.ValidarNumeros(user_id) == true) {
                    if (ValidaCPF.isCPF(user_id)==false) {
                        Util.msg(LoginActivity.this, "CPF inválido!");
                    }
                } else {
                    //Validar a senha
                    if (Util.ValidarSenha(senha_id)==true) {
                        retrofitConverter(user_id, senha_id);
                        //Grava o user localmente no arquivo de preferencias para mostrar na próxima carga do app
                        gravar_preferencias();

                    }
                }

            }
        });
    }

    //Rotina para tratar e receber os dados da API
    public  void retrofitConverter(String usuario, String senha) {

        RetrofitService service = ServiceGenerator.createService(RetrofitService.class);

        Call<user> call = service.login(usuario,senha);
        call.enqueue(new Callback<user>() {
            @Override
            public  void onResponse(Call<user> call, Response<user> response) {
                int statuscode = response.code();
                if (response.isSuccessful()) {

                    user respostaServidor = response.body();

                    //verifica aqui se o corpo da resposta não é nulo
                    if (respostaServidor != null) {

                            respostaServidor.setUserAccount(respostaServidor.getUserAccount());

                            setaValores(respostaServidor.getUserAccount());
                            irParaActivity();
                    }
                    else {

                        Util.msg(getApplicationContext(),"Resposta nula do servidor");
                    }

                } else {

                    Util.msg(getApplicationContext(),"Resposta não foi sucesso");
                    // segura os erros de requisição
                    ResponseBody errorBody = response.errorBody();
                }

            }

            @Override
            public void onFailure(Call<user> call, Throwable t) {
                Util.msg(getApplicationContext(),"Erro na chamada ao servidor");
            }
        });

    }


    //Seta valores trazidos do Json
    public  void setaValores(userId respostaFinal){
        userIdRetorno = respostaFinal.getUserId();
        nameRetorno = respostaFinal.getName().toString();
        bankAccountRetorno = respostaFinal.getBankAccount().toString();
        agencyRetorno = respostaFinal.getAgency().toString();
        balanceRetorno = respostaFinal.getBalance();
    }

    public void irParaActivity() {
        //Passar os dados obtidos no Retrofit para a próxima tela
        Intent intent = new Intent(LoginActivity.this, Currency.class);
        intent.putExtra("userIdRetorno",userIdRetorno);
        intent.putExtra("nameRetorno",nameRetorno);
        intent.putExtra("bankAccountRetorno",bankAccountRetorno);
        intent.putExtra("agencyRetorno",agencyRetorno);
        intent.putExtra("balanceRetorno", balanceRetorno);

        startActivity(intent);
    }


    //Mostrar no campo user o ultimo user salvo
    public void preferencias() {
        SharedPreferences sharedPreferences =
                getSharedPreferences(ARQUIVO_PREFERENCIAS, 0);
        if (sharedPreferences.contains("USER")) {
            userId.setText(sharedPreferences.getString("USER",""));
        }

    }

    //Gravar o arquivo de preferencias com o ultimo user validado
    //Gravar os valores no arquivo do usuário - SharedPreferences
    public void gravar_preferencias() {
        SharedPreferences prefs =
                LoginActivity.this.getSharedPreferences(ARQUIVO_PREFERENCIAS,
                        LoginActivity.this.MODE_PRIVATE);

        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("USER", userId.getText().toString());

        editor.commit();
    }
}
