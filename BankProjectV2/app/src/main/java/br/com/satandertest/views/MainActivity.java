package br.com.satandertest.views;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.com.satandertest.R;
import br.com.satandertest.adapters.StatementsAdapter;
import br.com.satandertest.dao.BancoLocal;
import br.com.satandertest.models.Statement;
import br.com.satandertest.models.UserAccount;
import br.com.satandertest.utils.AdministratorRequests;
import br.com.satandertest.utils.Utilities;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity {

    private TextView mName;
    private TextView mAccount;
    private TextView mBalance;

    private ImageView mLogout;
    private ProgressBar mLoadStatements;

    private LinearLayout mLlTryAgain;
    private Button mTryAgain;

    private List<Statement> mStatements;
    private StatementsAdapter mAdapter;
    private RecyclerView mRecyclerView;

    private UserAccount mUserAccount;

    //Confirmação de saída da aplicação
    private Dialog mDialogLogout;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initActions();
        loadData();
    }

    private void initViews() {

        mName = (TextView) findViewById(R.id.tv_name);
        mAccount = (TextView) findViewById(R.id.tv_account_number);
        mBalance = (TextView) findViewById(R.id.tv_balance);

        //Exibido quando não há conexão com a internet
        mLlTryAgain = (LinearLayout) findViewById(R.id.ll_try_again);
        mTryAgain = (Button) findViewById(R.id.btn_try_again);

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_statements);
        mRecyclerView.setHasFixedSize(true); // Tamanho fixo para todos os cards
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        mLogout = (ImageView) findViewById(R.id.btn_logout);
        mLoadStatements = (ProgressBar) findViewById(R.id.pb_statements);

    }

    private void loadData() {

        //Obtendo user account salva no banco local
        BancoLocal bancoLocal = new BancoLocal(MainActivity.this);
        mUserAccount = bancoLocal.getUserAccount();

        mName.setText(mUserAccount.getName());
        mAccount.setText(mUserAccount.getBankAccount() + " / " + Utilities.formatAgency(mUserAccount.getAgency()));
        Utilities.setCurrencyText(mBalance, mUserAccount.getBalance());

        //Chamando requisição para obtenção dos statmentes
        getStatements(mUserAccount);
    }

    private void initActions() {

        mLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showDialogLogout();
            }
        });

        //Botão exibido quando não houve conexão com a internet
        mTryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getStatements(mUserAccount);
            }
        });
    }

    public void getStatements(UserAccount userAccount) {

        mLlTryAgain.setVisibility(View.GONE);
        mLoadStatements.setVisibility(View.VISIBLE);

        //Inicializando statments com null, caso a requisição não seja sucesso ele permanecerá com valor nulo
        //então assim poderemos no fimDaThread se a requisição pelo menos obteve resposta
        mStatements = null;

        AdministratorRequests.get(
                "https://bank-app-test.herokuapp.com/api/statements/" + userAccount.getUserId(),
                new AdministratorRequests.RespostaRequisicao() {
                    @Override
                    public void respostaSucesso(String body) {
                        super.respostaSucesso(body);

                        System.out.println("RESPOSTA STATEMENTS: " + body);
                        mStatements = mountStatementsResponse(body);

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
                                if (mStatements == null) {

                                    //Exibindo mensagem e botão de tentar novamente
                                    mLlTryAgain.setVisibility(View.VISIBLE);
                                } else {

                                    //Garantindo que não seja exibido o componente de tentar novamente
                                    mLlTryAgain.setVisibility(View.GONE);

                                    //Requisição obteve resposta, a lista mStatements foi povoada
                                    mAdapter = new StatementsAdapter(MainActivity.this, mStatements);
                                    mRecyclerView.setAdapter(mAdapter);

                                }

                                //Esconendo loader
                                mLoadStatements.setVisibility(View.GONE);


                            }
                        });
                    }
                }
        );

    }

    private List<Statement> mountStatementsResponse(String body) {

        List<Statement> statements = new ArrayList<>();

        try {

            JSONArray statementList = new JSONObject(body).getJSONArray("statementList");

            for (int i = 0; i < statementList.length(); i++) {
                JSONObject statementObj = statementList.getJSONObject(i);

                Statement s = new Statement();
                s.setTitle(statementObj.getString("title"));
                s.setDesc(statementObj.getString("desc"));
                s.setDate(statementObj.getString("date"));
                s.setValue(statementObj.getDouble("value"));

                statements.add(s);
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return statements;
    }

    public void showDialogLogout() {
        mDialogLogout = new Dialog(MainActivity.this);
        mDialogLogout.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialogLogout.setContentView(R.layout.dialog_logout);
        mDialogLogout.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        Window window = mDialogLogout.getWindow();
        window.setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);

        mDialogLogout.setCancelable(true);

        Button logout = (Button) mDialogLogout.findViewById(R.id.btn_dialog_logout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Apagando dados do banco local
                BancoLocal bancoLocal = new BancoLocal(MainActivity.this);
                bancoLocal.cleanDb();

                //Direcinando para a tela de login
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                finish();
            }
        });

        Button cancel = (Button) mDialogLogout.findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mDialogLogout.dismiss();
            }
        });

        mDialogLogout.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {

            }
        });

        mDialogLogout.show();
    }
}
