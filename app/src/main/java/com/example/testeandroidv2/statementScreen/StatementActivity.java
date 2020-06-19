package com.example.testeandroidv2.statementScreen;

import android.os.Bundle;

import com.example.testeandroidv2.loginScreen.UserModel;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.example.testeandroidv2.R;
import com.example.testeandroidv2.databinding.StatementScreenBinding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

interface StatementActivityInput {
    void displayStatementData(List<StatementModel> statementModels);
    void displayStatementError(Object error);
    void displayClientData(ClientModel clientModel);
    void displayClientError(Object error);
}

public class StatementActivity extends AppCompatActivity implements StatementActivityInput {

//    public static String TAG = StatementActivity.class.getSimpleName();
    private StatementScreenBinding binding;
    private TransactionHistoryAdapter adapter;
    StatementInteractorInput output;
    UserModel userModel;
    List<StatementModel> statementModelList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.statement_screen);
        binding.setActivity(this);
        toggleLoading(true);

        StatementConfigurator.INSTANCE.configure(this);
        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            JsonObject object = new Gson().toJsonTree(bundle.get("user")).getAsJsonObject();
            userModel = new Gson().fromJson(object, UserModel.class);
        }

        RecyclerView rv = findViewById(R.id.transactionHistory);
        adapter = new TransactionHistoryAdapter(statementModelList);
        rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rv.setHasFixedSize(true);
        rv.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchMetaData();
    }

    public void fetchMetaData(){
        ClientRequest clientRequest = new ClientRequest();
        StatementRequest statementRequest = new StatementRequest();
        clientRequest.userModel = userModel;
        statementRequest.user = userModel;
        if(clientRequest.userModel != null) {
            output.fetchClientData(clientRequest);
            output.fetchStatementData(statementRequest);
        }
    }

    private void toggleLoading(Boolean isVisible){
        binding.setLoadingIsVisible(isVisible);
    }

    @Override
    public void displayStatementData(List<StatementModel> statementModels) {
        toggleLoading(false);
        adapter.setListOfStatement(statementModels);
    }

    @Override
    public void displayStatementError(Object error) {

    }

    @Override
    public void displayClientData(ClientModel clientModel) {
        binding.setClient(clientModel);
    }

    @Override
    public void displayClientError(Object error) {

    }

    public void logout(){
        finish();
    }
}
