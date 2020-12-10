package com.ivan.bankapp.view.presentation;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ivan.bankapp.R;
import com.ivan.bankapp.adapter.BillAdapter;
import com.ivan.bankapp.database.UserDB;
import com.ivan.bankapp.view.fragment.FragmentBills;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity implements ViewContract.IView {

    TextView nomeUsuario, numeroConta, valorSaldo;
    public FragmentBills billsCard;
    public RecyclerView billsRecycler;
    public BillAdapter billAdapter;
    public ArrayList<FragmentBills> billsList = new ArrayList<>();
    public ImageView logout;
    private ViewContract.IPresenter presenter;
    public static int userID;
    public RealmConfiguration realmConfig;
    public static Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        realm.init(getApplicationContext());
        realmConfig = new RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build();
        realm = Realm.getInstance(realmConfig);

        presenter = new Presenter(this);

        nomeUsuario = findViewById(R.id.nomeUsuario);
        numeroConta = findViewById(R.id.numeroConta);
        valorSaldo = findViewById(R.id.valorConta);
        billsRecycler = findViewById(R.id.billRecycler);
        logout = findViewById(R.id.logout);

        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        billsRecycler.setLayoutManager(llm);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Login.class);
                startActivity(i);
            }
        });

        if (presenter != null) {
            presenter.onHomeLoaded();
            setUserData();
        }
    }

    private <ViewType extends View> ViewType find(@IdRes int viewId) {
        return (ViewType) findViewById(viewId);
    }

    @Override
    public void getStatements(String title, String desc, String data, Double value) {

        billsCard = new FragmentBills();

        billsCard.setTitle(title);
        billsCard.setBillDate(data);
        billsCard.setBillDescription(desc);
        billsCard.setBillValue(value);
        billsList.add(billsCard);

        billAdapter = new BillAdapter(billsList);
        billsRecycler.setAdapter(billAdapter);
        billAdapter.notifyDataSetChanged();
    }

    @Override
    public void login(Integer id, String name, String account, String agency, Double value) {

    }

    public void setUserData() {

        String userName = "";
        String account = "";
        String agency = "";
        Double value = 0.0;

        RealmResults<UserDB> results =
                realm.where(UserDB.class).findAll();
        if(results.size() > 0){
            assert results.get(0) != null;

            userName = results.get(0).getName();
            account = results.get(0).getBankAccount();
            agency = results.get(0).getAgency();
            value = results.get(0).getBalance();

            nomeUsuario.setText(userName);
            numeroConta.setText(account + "/" + agency);
            valorSaldo.setText("R$ " + String.valueOf(value));
        }


    }

    public static int getUserID(){

        int userID = 0;

        RealmResults<UserDB> results =
                realm.where(UserDB.class).findAll();
        if(results.size() > 0){
            assert results.get(0) != null;
            userID = results.get(0).getId();
        }

        return userID;
    }
}