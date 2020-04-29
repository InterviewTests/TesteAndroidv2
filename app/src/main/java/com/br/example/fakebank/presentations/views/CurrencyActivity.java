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
import com.br.example.fakebank.databinding.ActivityCurrencyBinding;
import com.br.example.fakebank.domains.services.CurrencyService;
import com.br.example.fakebank.infrastructure.app.PreferenceFakeBank;
import com.br.example.fakebank.infrastructure.retrofit.entities.CurrencyEntity;
import com.br.example.fakebank.infrastructure.retrofit.entities.UserAccountEntity;
import com.br.example.fakebank.presentations.Erros.ErrorUtils;
import com.br.example.fakebank.presentations.handles.CurrencyHandle;
import com.br.example.fakebank.presentations.utils.StatusPreferenceUtil;
import com.br.example.fakebank.presentations.viewModels.CurrencyViewModel;
import com.br.example.fakebank.presentations.viewModels.viewModelFactories.CurrencyViewModelFactory;
import com.br.example.fakebank.presentations.views.adpters.StatementAdapter;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class CurrencyActivity extends AppCompatActivity implements CurrencyHandle {

    private ActivityCurrencyBinding activityCurrencyBinding;
    private CurrencyViewModelFactory currencyViewModelFactory;
    private CurrencyViewModel currencyViewModel;
    private StatementAdapter statementAdapter;
    private List<CurrencyEntity> currencyEntities = new ArrayList<>();
    private PreferenceFakeBank preferenceFakeBank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityCurrencyBinding = DataBindingUtil.setContentView(this, R.layout.activity_currency);

        currencyViewModelFactory = new CurrencyViewModelFactory(new CurrencyService(),this);
        currencyViewModel = new ViewModelProvider(this, currencyViewModelFactory).get(CurrencyViewModel.class);
        activityCurrencyBinding.setCurrencyViewModel(currencyViewModel);

        Intent i = getIntent();
        UserAccountEntity userAccountEntity = i.getParcelableExtra("userAccount");
        preferenceFakeBank = new PreferenceFakeBank(this);
        if (userAccountEntity != null){
            currencyViewModel.listStatements(userAccountEntity.getUserId());

            activityCurrencyBinding.textViewNameUser.setText(userAccountEntity.getName());
            String balance = userAccountEntity.getBankAccount()+"/"+userAccountEntity.getAgency();
            activityCurrencyBinding.textViewCountNumber.setText(balance);

            activityCurrencyBinding.textViewBalance.setText(formatNumberDecimal(userAccountEntity.getBalance()));

            RecyclerView recyclerView = activityCurrencyBinding.rvBankStatement;
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
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void reloadListStatement(List<CurrencyEntity> currencyEntityList) {
        for (CurrencyEntity currencyEntity: currencyEntityList){
            currencyEntities.add(currencyEntity);
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
            activityCurrencyBinding.includeLoading.getRoot().setVisibility(View.VISIBLE);
        } else {
            activityCurrencyBinding.includeLoading.getRoot().setVisibility(View.GONE);
        }
    }

    private void showMessage(String message)
    {
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
    }
}
