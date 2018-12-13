package com.hkdevelopment.bankapp.home.presenter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.hkdevelopment.bankapp.home.model.Statement;
import com.hkdevelopment.bankapp.home.model.StatementList;
import com.hkdevelopment.bankapp.home.view.HomeViewInt;
import com.hkdevelopment.bankapp.login.view.LoginView;
import com.hkdevelopment.bankapp.retrofit.RetrofitInstance;
import com.hkdevelopment.bankapp.retrofit.services.Services;
import com.hkdevelopment.bankapp.utils.ProgressManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePresenter implements HomePresenterInt {

    private HomeViewInt view;
    private Context context;
    private ProgressManager pgManger;

    public HomePresenter(Context context, HomeViewInt view) {
        this.context = context;
        this.view = view;
        pgManger=new ProgressManager(context);
    }

    @Override
    public void getStatements(String id) {
        pgManger.showDialog("Carregando dados...");
        Services service = RetrofitInstance.getRetrofitInstance().create(Services.class);
        Call<Statement> call = service.getStatements(id);
        Log.wtf("URL Called", call.request().url() + "");

        call.enqueue(new Callback<Statement>() {
            @Override
            public void onResponse(@NonNull Call<Statement> call, @NonNull Response<Statement> response) {
                List<StatementList> objectList = response.body().getStatementList();
                Log.d("teste",objectList.get(1).getTitle());
                view.setAdapter(objectList);
                pgManger.purgeDialog();
            }

            @Override
            public void onFailure(Call<Statement> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_LONG).show();
                pgManger.purgeDialog();
            }
        });
    }

    @Override
    public void logOut() {
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(context);
        builder.setMessage("Tem certeza que deseja sair?");
        builder.setCancelable(false);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id)
            {
                dialog.cancel();
                Intent i=new Intent(context,LoginView.class);
                context.startActivity(i);
            }
        });
        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();

    }
}
