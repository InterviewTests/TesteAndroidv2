package com.android.bankapp.statements.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.bankapp.R;
import com.android.bankapp.statements.StatementConfigurator;
import com.android.bankapp.statements.interactor.StatementInteractor;
import com.android.bankapp.statements.model.Statement;
import com.android.bankapp.statements.view.adapter.StatementRecyclerAdapter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.List;

@SuppressLint("Registered")
@EActivity(R.layout.activity_steatements)
public class StatementsActivity extends AppCompatActivity implements StatementActivityInput {

    @ViewById(R.id.recycler_view)
    RecyclerView recyclerView;

    public StatementInteractor output;
    private StatementRecyclerAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatementConfigurator.INSTANCE.configure(this);
    }

    @AfterViews
    void afterViews(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        adapter = new StatementRecyclerAdapter();

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        output.loadData();
    }

    @Override
    public void dataLoaded(List<Statement> statementList) {
        adapter.addAll(statementList);
    }

    @Override
    public void errorLoadData() {

    }
}
