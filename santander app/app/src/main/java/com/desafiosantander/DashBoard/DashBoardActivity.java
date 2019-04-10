package com.desafiosantander.DashBoard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.desafiosantander.R;

import java.util.List;


interface DashBoardActivityInput {
    public void loadList(DashBoardViewModel viewModel);
}


public class DashBoardActivity extends AppCompatActivity
        implements DashBoardActivityInput {

    public static String TAG = DashBoardActivity.class.getSimpleName();
    DashBoardInteractorInput output;
    DashBoardRouter router;
    MyRecyclerViewAdapter adapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //do the setup
        setContentView(R.layout.dashboard);
        getSupportActionBar().hide();

        loadUserDashBoard(getIntent());
        loadBtnExit();

        recyclerView = findViewById(R.id.rv_recents);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DashBoardConfigurator.INSTANCE.configure(this);
        //DashBoardRequest aDashBoardRequest = new DashBoardRequest();
        //populate the request

        output.loadStatements();
    }

    public void loadUserDashBoard(Intent intent){

        TextView userName = (TextView) findViewById(R.id.txt_user_name);
        TextView userAccount = (TextView) findViewById(R.id.txt_dashboard_account_value);
        TextView userBalance = (TextView) findViewById(R.id.txt_balance_value);

        userName.setText(intent.getStringExtra("userName"));
        userAccount.setText(intent.getStringExtra("account")+" / "+intent.getStringExtra("agency"));
        userBalance.setText("R$ "+intent.getStringExtra("balance"));
    }

    @Override
    public void loadList(DashBoardViewModel viewModel) {
        Log.d("loadListloadList", "loadList: "+viewModel.statementList.size());

        final List<Statement> list = viewModel.statementList;
        this.runOnUiThread(new Runnable() {
            public void run() {
                adapter = new MyRecyclerViewAdapter(getApplicationContext(), list);
                recyclerView.setAdapter(adapter);


            }
        });
    }
    public void loadBtnExit(){
        ImageView exit = findViewById(R.id.img_exit);

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DashBoardActivity.super.onBackPressed();
            }
        });
    }

}
