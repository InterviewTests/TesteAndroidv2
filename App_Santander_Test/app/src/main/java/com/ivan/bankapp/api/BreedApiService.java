package com.ivan.bankapp.api;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.ivan.bankapp.App;
import com.ivan.bankapp.model.StatementList;
import com.ivan.bankapp.model.User;
import com.ivan.bankapp.view.presentation.Login;
import com.ivan.bankapp.view.presentation.MainActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BreedApiService {

    Login loginPage;
    MainActivity mainPage;

    public void getStatement(final NetworkResponseListener<StatementList> listener, Api api) {

        int userID = mainPage.getUserID();

        Call<StatementList> call = api.getStatements(userID);
        call.enqueue(new Callback<StatementList>() {
            @Override
            public void onResponse(@NonNull Call<StatementList> call, @NonNull Response<StatementList> response) {
                if (listener != null) {
                    listener.onResponseReceived(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<StatementList> call, @NonNull Throwable t) {
                if (App.getInstance() != null) {
                    Toast.makeText(
                            App.getInstance(),
                            "An error occurred: " + t.getMessage(),
                            Toast.LENGTH_SHORT
                    ).show();
                }

                if (listener != null) {
                    listener.onError();
                }
            }
        });
    }

    public void login(final NetworkResponseListener<User> listener, Api api) {

        String user = loginPage.getUsername();
        String password = loginPage.getPassword();


        Call<User> call = api.login(user, password);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {
                if (listener != null) {
                    listener.onResponseReceived(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<User> call, @NonNull Throwable t) {
                if (App.getInstance() != null) {
                    Toast.makeText(
                            App.getInstance(),
                            "An error occurred: " + t.getMessage(),
                            Toast.LENGTH_SHORT
                    ).show();
                }

                if (listener != null) {
                    listener.onError();
                }
            }
        });
    }
}