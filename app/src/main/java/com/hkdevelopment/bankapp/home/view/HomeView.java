package com.hkdevelopment.bankapp.home.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hkdevelopment.bankapp.R;
import com.hkdevelopment.bankapp.home.model.StatementList;
import com.hkdevelopment.bankapp.home.presenter.HomePresenter;
import com.hkdevelopment.bankapp.home.presenter.HomePresenterInt;
import com.hkdevelopment.bankapp.home.view.adapters.HomeAdapter;
import com.hkdevelopment.bankapp.login.model.User;
import com.hkdevelopment.bankapp.login.model.UserAccount;
import com.hkdevelopment.bankapp.utils.MaskFormater;
import com.hkdevelopment.bankapp.utils.MaskFormaterInt;
import com.hkdevelopment.bankapp.utils.PreferencesManager;
import com.hkdevelopment.bankapp.utils.PreferencesManagerInt;

import java.util.List;

public class HomeView extends AppCompatActivity implements HomeViewInt{

    private HomePresenterInt presenter;
    private MaskFormaterInt formater;
    private UserAccount user;
    private RecyclerView rvHomeList;
    private TextView txtAccountHome, txtUserHome, txtSaldoHome, txtInfoHome;
    private ImageView imgLogOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_view);

        Bundle bundle = getIntent().getExtras();
        user = (UserAccount) bundle.getSerializable("userPojo");
        presenter=new HomePresenter(this,this);
        PreferencesManagerInt prefs=new PreferencesManager(this);
        Log.d("testePrefaHome",prefs.getString(getString(R.string.user))+prefs.getString(getString(R.string.password)));
        findViews();

        presenter.getStatements(user.getUserId().toString());

        imgLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.logOut();
            }
        });
    }

    public void findViews() {
        formater=new MaskFormater();
        rvHomeList = findViewById(R.id.rvHomeList);
        txtAccountHome = findViewById(R.id.txtAccountHome);
        txtUserHome = findViewById(R.id.txtUserHome);
        txtSaldoHome = findViewById(R.id.txtSaldoHome);
        txtInfoHome = findViewById(R.id.txtInfoHome);
        imgLogOut=findViewById(R.id.imgLogOut);

        txtUserHome.setText(user.getName());
        txtAccountHome.setText(user.getBankAccount()+" / "+formater.formateAgency(user.getAgency()));
        txtSaldoHome.setText(formater.formatValue(user.getBalance()));
    }

    public void setAdapter(List<StatementList> pojo){
        Log.d("teste",pojo.get(1).getTitle());
        rvHomeList.setLayoutManager(new LinearLayoutManager(this));
        rvHomeList.setAdapter(new HomeAdapter(getApplicationContext(),pojo));
    }

    @Override
    public void onBackPressed() {
        presenter.logOut();
        return;

    }
}
