package com.bankapp.statementScreen;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bankapp.ErrorResponse;
import com.bankapp.R;
import com.bankapp.helper.ConstantsHelper;
import com.bankapp.helper.ValidationHelper;
import com.bankapp.loginScreen.UserAccount;
import com.bankapp.statementScreen.adapter.StatementAdapter;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

interface StatementActivityInput {
    public void displayStatementData(StatementViewModel viewModel);
    public void displayErrorStatementData(ErrorResponse error);
}

public class StatementActivity extends AppCompatActivity implements StatementActivityInput, View.OnClickListener {

    public static String TAG = StatementActivity.class.getSimpleName();
    StatementInteractorInput output;
    StatementRouterInput router;

    private StatementAdapter mAdapter;
    private RecyclerView mRecyclerView;

    private AppCompatTextView userNumberAccount;
    private AppCompatTextView userBalance;
    private AppCompatTextView userName;
    public AppCompatImageButton btLogout;

    UserAccount userAccount;
    StatementRequest aStatementRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statement);

        StatementConfigurator.INSTANCE.configure(this);

        showProgressDialog();

        userNumberAccount = findViewById(R.id.userNumberAccount);
        userBalance = findViewById(R.id.userBalance);
        userName = findViewById(R.id.userName);
        btLogout = findViewById(R.id.btLogout);
        btLogout.setOnClickListener(this);

        mRecyclerView = (RecyclerView) findViewById(R.id.rvStatement);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        userAccount = getIntent().getParcelableExtra(ConstantsHelper.USER_DETAILS);
        aStatementRequest = new StatementRequest();
        aStatementRequest.userId = userAccount.userId;

        bindFields();
        getStatements();
    }

    public void getStatements(){
        output.getStatements(aStatementRequest);
    }

    public void bindFields(){

        DecimalFormat df = (DecimalFormat) DecimalFormat.getInstance();
        df.applyPattern("R$###,###,###,##0.00;-R$###,###,###,##0.00");

        if(userAccount != null){
            userNumberAccount.setText(ValidationHelper.formatAccountNumber(userAccount.bankAccount, userAccount.agency));
            userBalance.setText(df.format(new BigDecimal(userAccount.balance)));
            userName.setText(userAccount.name);
        } else {
            Toast.makeText(this, getString(R.string.error_loading_user_details), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.btLogout) {
            output.logout(this);
            router.passDataToNextScene();
        }
    }

    @Override
    public void displayStatementData(StatementViewModel viewModel) {
        hideProgressDialog();
        prepareRecyclerView(viewModel.listStatementModel);
    }

    @Override
    public void displayErrorStatementData(ErrorResponse error) {
        hideProgressDialog();
        Toast.makeText(this, error.toString(), Toast.LENGTH_LONG).show();
    }

    private void prepareRecyclerView(List<StatementModel> statements) {
        mAdapter = new StatementAdapter(statements);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        //hideProgressDialog();
    }

    public String getDate(String strDate) throws ParseException {

        DateFormat dateFormat  = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        Date date = dateFormat.parse(strDate);
        c.setTime(date);

        SimpleDateFormat newDateFormat = new SimpleDateFormat("dd/MM/yyyy");

        return newDateFormat.format(c.getTime());
    }

    public ProgressDialog mProgressDialog;

    public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage(getString(R.string.loading));
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.show();
    }

    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }
}
