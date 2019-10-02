package com.riso.zup.bank.StatementScreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.riso.zup.bank.R;
import com.riso.zup.bank.helpers.Utils;
import com.riso.zup.bank.loginScreen.LoginActivity;
import com.riso.zup.bank.models.Statement;
import com.riso.zup.bank.models.UserInfo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StatementActivity extends AppCompatActivity implements
        View.OnClickListener,
        StatementInteractor.View{

    @BindView(R.id.tv_username)
    TextView tvUserName;
    @BindView(R.id.tv_account)
    TextView tvAccount;
    @BindView(R.id.tv_balance)
    TextView tvBalance;
    @BindView(R.id.btn_logout)
    ImageButton btnLogout;
    @BindView(R.id.rv_extract)
    RecyclerView recyclerView;

    private StatementPresenter presenter = new StatementPresenter(this);

    private StatementAdapter mAdapter;

    private Utils utils = new Utils();
    private Context ct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statement);
        ButterKnife.bind(this);
        ct = StatementActivity.this;

        initControl();
    }

    private void initControl(){
        btnLogout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == btnLogout){
            logout();
        }
    }

    private void retriveDataUserFromLogin(){

        UserInfo userInfo = getIntent().getParcelableExtra("userInfo");
        if(userInfo!=null){
            setDataUserToUI(userInfo);
            loadDataExtractFromDB(userInfo);
        }
    }

    private void setDataUserToUI(UserInfo userInfo){

        String account = userInfo.getBankAccount();
        String agency = userInfo.getAgency();
        if(agency != null){
            agency = Utils.formatNumberAgency(agency);
        }

        tvUserName.setText(userInfo.getName());
        tvAccount.setText(account+" / "+agency);

        String balance = Utils.formatValue(ct,userInfo.getBalance());

        tvBalance.setText(balance);
    }

    private void loadDataExtractFromDB(UserInfo userInfo){
        presenter.getListStatement(userInfo.getUserId());
    }

    private void logout(){
        callLoginScreen();
        finish();
    }

    private void callLoginScreen() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void onStart(){
        super.onStart();

        retriveDataUserFromLogin();
    }

    public void populateListStatement(List<Statement> listStatement){

        mAdapter = new StatementAdapter(listStatement, ct);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(mAdapter);

    }

    @Override
    public void receiveListStatement(List<Statement> listStatement) {

        if(listStatement == null || listStatement.size() == 0){
            loadError(R.string.error_list_statement);
        }
        else{
            populateListStatement(listStatement);
        }
    }

    @Override
    public void loadError(int error) {
        utils.showErrorDialog(ct, getString(error));
        utils.hideProgressDialog();
    }
}
