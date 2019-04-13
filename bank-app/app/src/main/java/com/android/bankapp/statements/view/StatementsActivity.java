package com.android.bankapp.statements.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.bankapp.R;
import com.android.bankapp.login.model.UserAccount;
import com.android.bankapp.statements.StatementConfigurator;
import com.android.bankapp.statements.interactor.StatementInteractor;
import com.android.bankapp.statements.model.Statement;
import com.android.bankapp.statements.router.StatementRouter;
import com.android.bankapp.statements.view.adapter.StatementRecyclerAdapter;
import com.android.bankapp.util.UserStateUtil;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.List;
import java.util.Locale;

@SuppressLint("Registered")
@EActivity(R.layout.activity_steatements)
public class StatementsActivity extends AppCompatActivity implements StatementActivityInput {

    @ViewById(R.id.recycler_view)
    RecyclerView recyclerView;

    @ViewById(R.id.text_view_user_name)
    TextView textViewUserName;

    @ViewById(R.id.text_view_user_account)
    TextView textViewUserAccount;

    @ViewById(R.id.txt_view_user_balance)
    TextView textViewUserBalance;

    @ViewById(R.id.progress)
    ProgressBar progressBar;

    @ViewById(R.id.text_view_error_state)
    TextView textViewError;

    @ViewById(R.id.text_view_no_content)
    TextView textViewNoContent;

    @ViewById(R.id.button_retry)
    Button buttonRetry;


    public StatementInteractor output;
    private StatementRecyclerAdapter adapter;
    public StatementRouter router;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatementConfigurator.INSTANCE.configure(this);
    }

    @AfterViews
    void afterViews() {
        UserAccount user = UserStateUtil.getUserAccount();

        String account = formatUserAccount(user.getAgency(), user.getBankAccount());
        String balance = formatCurrencyValue(user.getBalance());

        textViewUserName.setText(user.getName());
        textViewUserAccount.setText(account);
        textViewUserBalance.setText(balance);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        adapter = new StatementRecyclerAdapter();

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        if (isNetworkConnection()) {
            output.loadData();
        } else {
            textViewError.setText(getString(R.string.no_internet_connection));
            showErrorState();
        }
    }


    public static String formatCurrencyValue(Double value) {
        if (value != null) {
            String aux = String.format(Locale.US, "%.2f", value);

            aux = aux.replace(".", ",");
            StringBuilder stringBuilder = new StringBuilder(aux);
            if (aux.length() <= 9 && aux.length() > 6)
                stringBuilder.insert(aux.length() - 6, ".");
            else if (aux.length() <= 12 && aux.length() > 9) {
                stringBuilder.insert(aux.length() - 6, ".");
                stringBuilder.insert(aux.length() - 9, ".");
            } else if (aux.length() <= 15 && aux.length() > 12) {
                stringBuilder.insert(aux.length() - 6, ".");
                stringBuilder.insert(aux.length() - 9, ".");
                stringBuilder.insert(aux.length() - 12, ".");

            }

            String valueFormatted = stringBuilder.toString();

            if (valueFormatted.charAt(0) == '-' && valueFormatted.charAt(1) == '.')
                valueFormatted = valueFormatted.replace(".", "");

            return "R$" + valueFormatted;
        } else
            return "0,00";
    }

    private String formatUserAccount(String agency, String bankAccount) {
        String firstPart = agency.substring(0, 2);
        String secondPart = agency.substring(2, agency.length() - 2);

        agency = firstPart + "." + secondPart + "-" + agency.charAt(agency.length() - 1);

        return bankAccount + "/" + agency;
    }

    @Override
    public void dataLoaded(List<Statement> statementList) {
        adapter.addAll(statementList);

        if (adapter.getItemCount() <= 0) {
            showNoContentState();
        } else {
            showContentState();
        }
    }

    public void retry(View view) {
        showLoadingState();
        if (isNetworkConnection()) {
            textViewError.setText(getString(R.string.request_error));
            output.loadData();
        } else {
            textViewError.setText(getString(R.string.no_internet_connection));
            showErrorState();
        }
    }

    @Override
    public void errorLoadData() {
        showErrorState();
    }

    private void showNoContentState() {
        buttonRetry.setVisibility(View.GONE);
        textViewError.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);
        textViewNoContent.setVisibility(View.VISIBLE);
    }

    private void showLoadingState() {
        buttonRetry.setVisibility(View.GONE);
        textViewError.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
        textViewNoContent.setVisibility(View.VISIBLE);
    }

    private void showErrorState() {
        buttonRetry.setVisibility(View.VISIBLE);
        textViewError.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);
        textViewNoContent.setVisibility(View.VISIBLE);
    }

    private void showContentState() {
        buttonRetry.setVisibility(View.GONE);
        textViewError.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        textViewNoContent.setVisibility(View.GONE);
    }

    private boolean isNetworkConnection() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;

    }


    public void logOut(View view) {
        router.goToLogin(this);
    }
}
