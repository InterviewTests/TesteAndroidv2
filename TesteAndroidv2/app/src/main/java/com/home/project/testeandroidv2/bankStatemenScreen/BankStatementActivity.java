package com.home.project.testeandroidv2.bankStatemenScreen;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.home.project.testeandroidv2.R;
import com.home.project.testeandroidv2.databinding.ActivityStatementBankBinding;
import com.home.project.testeandroidv2.model.BankStatement;
import com.home.project.testeandroidv2.model.UserAccount;

import java.util.List;

public class BankStatementActivity extends AppCompatActivity {

    private BankStatmentAdapter bankStatmentAdapter;
    private Context context = this;
    public BankStatementRouter router;
    private ActivityStatementBankBinding binding;
    public BankStatementViewModel bankStatementViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_statement_bank);

        bankStatementViewModel = ViewModelProviders.of(this).get(BankStatementViewModel.class);

        BankStatementConfigurator.INSTANCE.configure(this);

        startRecyclerView();

        if (getIntent().getSerializableExtra("userData") != null) {
            UserAccount userAccount = (UserAccount) getIntent().getSerializableExtra("userData");
            //seta os dados do usuário retornados do login no layout dessa activity
            binding.setUserAccount(userAccount);
        }

        //observa quando a lista de extratos recebe atualização
        bankStatementViewModel.getBankStatementObservable().observe(this, new Observer<List<BankStatement>>() {
            @Override
            public void onChanged(@Nullable List<BankStatement> list) {
                //recebe os dados da lista de extrato  e atualiza o adapter
                binding.proogressBar.setVisibility(View.GONE);
                if (list != null) {
                    bankStatmentAdapter.setItems(list);
                } else {
                    Toast.makeText(context, "Ocorreu um erro ao executar a operação, verifique sua conexão" +
                            " com a internet.", Toast.LENGTH_LONG).show();
                }

            }
        });


    }

    public void startRecyclerView() {
        //inicia o recyclerview e instância o adapter
        binding.rvStatementList.setLayoutManager(new LinearLayoutManager(context));
        bankStatmentAdapter = new BankStatmentAdapter(context);
        binding.rvStatementList.setAdapter(bankStatmentAdapter);

    }

    public void onClickLogout(View view) {
        router.startNextActivity();
    }
}
