package com.br.projetotestesantanter.refactor.statementScreen;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.br.projetotestesantanter.R;
import com.br.projetotestesantanter.refactor.loginScreen.LoginModel;


interface StatementActivityInput {

    void responselistStatemnt(StatementModel.ListStatemt response);
    void showProgressBar();
    void hideProgressBar();

}

public class StatementActivity extends AppCompatActivity implements StatementActivityInput ,
        ViewHeaderStatements.OnItemClickListener{

    private LoginModel.Login login;

    @BindView(R.id.header_statements)
    ViewHeaderStatements headerStatements;

    @BindView(R.id.recycler_statements)
    RecyclerView recyclerRtatements;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;


    protected StatementIntaractorInput output;
    protected StatementRouter router;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statements);
        ButterKnife.bind(this);
        StatementConfigurator.INSTANCE.configure(this);

        login = getIntent().getParcelableExtra("loginResponse");

        initHeaderStatemt();
        setListenerExit();
        output.informationLogin(login);

    }

    private void initHeaderStatemt() {

        if(null != login) {
            headerStatements.bind(login);
        }
    }

    private void setListenerExit()
    {
        headerStatements.setListener(this);
    }

    @Override
    public void responselistStatemnt(StatementModel.ListStatemt response) {

        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerRtatements.setLayoutManager(layoutManager);

        StatementAdapter adapter = new StatementAdapter(response.getStatementArrayList(), this);

        recyclerRtatements.setAdapter(adapter);
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onItemClick(ImageView item) {
        finish();
    }
}
