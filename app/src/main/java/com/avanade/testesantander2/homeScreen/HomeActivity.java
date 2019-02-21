package com.avanade.testesantander2.homeScreen;

import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.Toast;
import android.widget.Toolbar;

import com.avanade.testesantander2.R;
import com.avanade.testesantander2.UserAccount;
import com.avanade.testesantander2.databinding.ActivityHomeBinding;
import com.avanade.testesantander2.loginScreen.LoginRouter;
import com.avanade.testesantander2.util.MonetarioUtil;


interface HomeActivityInput {
    void displayUsuario(UserAccount userAccount);

    void displayCurrency(StatementViewModel statementViewModel);

    void showErro(String erro);
}

public class HomeActivity extends AppCompatActivity implements HomeActivityInput {

    public static String TAG = HomeActivity.class.getSimpleName();
    ActivityHomeBinding binding;
    HomeInteractorInput output;
    HomeRouter router;
    StatementViewModel statementViewModel;
    UserAccount userAccount;
    String erro;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_perfil);

//        Toolbar toolbar = findViewById(R.id.my_toolbar);
//        this.setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(userAccount.getName());
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        HomeConfigurator.INSTANCE.configure(this);

        userAccount = (UserAccount)getIntent().getSerializableExtra(LoginRouter.CHAVE);

        buscarDados();
    }

    @Override
    public void displayUsuario(UserAccount userAccount) {
        binding.setAccount(userAccount);
        binding.setValorMonetario(MonetarioUtil.formataMoedaPtBr(userAccount.getBalance()));
    }

    @Override
    public void displayCurrency(StatementViewModel viewModel) {
        statementViewModel = viewModel;


        RecyclerView.LayoutManager layout = new LinearLayoutManager(
                this, RecyclerView.VERTICAL, false);
        binding.rcViewCurrency.setLayoutManager(layout);
        binding.rcViewCurrency.setHasFixedSize(true);
        binding.rcViewCurrency.setItemViewCacheSize(20);
        binding.rcViewCurrency.setDrawingCacheEnabled(true);
        binding.rcViewCurrency.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_LOW);

        binding.rcViewCurrency.setVisibility(View.VISIBLE);
        binding.loadingRecycler.setVisibility(View.INVISIBLE);


        CurrencyAdapter currencyAdapter = new CurrencyAdapter(viewModel.listaStatement);
        binding.rcViewCurrency.setAdapter(currencyAdapter);
    }

    @Override
    public void showErro(String erro) {
        Toast.makeText(this, erro, Toast.LENGTH_LONG).show();
    }

    void buscarDados() {
        binding.rcViewCurrency.setVisibility(View.INVISIBLE);
        binding.loadingRecycler.setVisibility(View.VISIBLE);
        output.getData(userAccount);
    }

    public void logout(View view) {
        finish();
    }

    @Override
    public void onBackPressed() {
        finish();
    }


}
