package com.br.example.fakebank.presentations.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.br.example.fakebank.R;
import com.br.example.fakebank.databinding.ActivityStatementBinding;
import com.br.example.fakebank.domains.services.StatementService;
import com.br.example.fakebank.infrastructure.retrofit.entities.StatementEntity;
import com.br.example.fakebank.infrastructure.retrofit.entities.UserAccountEntity;
import com.br.example.fakebank.presentations.Erros.ErrorUtils;
import com.br.example.fakebank.presentations.handles.StatementHandle;
import com.br.example.fakebank.presentations.viewModels.StatementViewModel;
import com.br.example.fakebank.presentations.viewModels.viewModelFactories.StatementViewModelFactory;
import com.br.example.fakebank.presentations.views.adpters.StatementAdapter;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class StatementActivity extends AppCompatActivity implements StatementHandle {

    private ActivityStatementBinding activityStatementBinding;
    private StatementAdapter statementAdapter;
    private List<StatementEntity> currencyEntities = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityStatementBinding = DataBindingUtil.setContentView(this, R.layout.activity_statement);

        StatementViewModelFactory statementViewModelFactory = new StatementViewModelFactory(new StatementService(), this);
        StatementViewModel statementViewModel = new ViewModelProvider(this, statementViewModelFactory).get(StatementViewModel.class);
        activityStatementBinding.setStatementViewModel(statementViewModel);

        Intent i = getIntent();
        UserAccountEntity userAccountEntity = i.getParcelableExtra("userAccount");
        if (userAccountEntity != null){
            statementViewModel.listStatements(userAccountEntity.getUserId());

            activityStatementBinding.textViewNameUser.setText(userAccountEntity.getName());
            String balance = userAccountEntity.getBankAccount()+"/"+userAccountEntity.getAgency();
            activityStatementBinding.textViewCountNumber.setText(balance);

            activityStatementBinding.textViewBalance.setText(formatNumberDecimal(userAccountEntity.getBalance()));

            RecyclerView recyclerView = activityStatementBinding.rvBankStatement;
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);
            statementAdapter = new StatementAdapter(currencyEntities);
            recyclerView.setAdapter(statementAdapter);
        }else {
            finish();
        }

    }

    private String formatNumberDecimal(Double value){
        DecimalFormat precision = new DecimalFormat("#,##0.00");
        return precision.format(value);
    }

    @Override
    public void actionLogout() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    @Override
    public void reloadListStatement(List<StatementEntity> statementEntityList) {
        for (StatementEntity statementEntity : statementEntityList){
            currencyEntities.add(statementEntity);
        }
        statementAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError(ErrorUtils error) {
        showMessage(error.returnMessageByContext(this));
    }

    @Override
    public void setLoading(Boolean isLoading) {
        if (isLoading) {
            activityStatementBinding.includeLoading.getRoot().setVisibility(View.VISIBLE);
        } else {
            activityStatementBinding.includeLoading.getRoot().setVisibility(View.GONE);
        }
    }

    private void showMessage(String message)
    {
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
    }
}
