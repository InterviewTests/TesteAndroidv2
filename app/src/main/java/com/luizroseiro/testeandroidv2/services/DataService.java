package com.luizroseiro.testeandroidv2.services;

import android.support.annotation.NonNull;

import com.luizroseiro.testeandroidv2.R;
import com.luizroseiro.testeandroidv2.interfaces.TAInterface;
import com.luizroseiro.testeandroidv2.models.StatementData;
import com.luizroseiro.testeandroidv2.models.Statements;
import com.luizroseiro.testeandroidv2.models.User;
import com.luizroseiro.testeandroidv2.utils.Utils;
import com.luizroseiro.testeandroidv2.views.activities.MainActivity;
import com.luizroseiro.testeandroidv2.views.fragments.LoginFragment;
import com.luizroseiro.testeandroidv2.views.fragments.MainFragment;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class DataService {

    private MainActivity mainActivity;
    private TAInterface api;

    private List<StatementData> statements = new ArrayList<>();
    public List<StatementData> getStatementsList() {
        return statements;
    }

    public DataService(MainActivity mainActivity) {
        this.mainActivity = mainActivity;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://bank-app-test.herokuapp.com")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(TAInterface.class);
    }

    public boolean isLoggedIn() {
        return AppPreferences.isLoggedIn(mainActivity);
    }

    public void loginUser(final LoginFragment fragment, String user, String password) {
        Call<User> call = api.login(user, password);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {
                if (response.isSuccessful() && response.body() != null
                        && response.body().getError().getCode() == null) {
                    AppPreferences.setLoggedIn(mainActivity);
                    AppPreferences.setUserId(mainActivity, response.body().getUserAccount()
                            .getUserId());
                    AppPreferences.setUserName(mainActivity, response.body().getUserAccount()
                            .getName());
                    AppPreferences.setUserAgency(mainActivity, response.body().getUserAccount()
                            .getAgency());
                    AppPreferences.setUserBankAccount(mainActivity, response.body().getUserAccount()
                            .getBankAccount());
                    AppPreferences.setUserBalance(mainActivity, (float) response.body()
                            .getUserAccount().getBalance());

                    Utils.replaceFragment(R.id.container_main, new MainFragment());
                }
                else {
                    Utils.createToast(mainActivity.getString(R.string.error_login));
                }
            }

            @Override
            public void onFailure(@NonNull Call<User> call, @NonNull Throwable t) {
                Utils.createToast(mainActivity.getString(R.string.error_login));
                fragment.dismissLoadingFeedback();
            }
        });
    }

    public void logoutUser() {
        AppPreferences.clearUser(mainActivity);
        Utils.replaceFragment(R.id.container_main, new LoginFragment());
    }

    public void getStatements(final MainFragment fragment) {
        Call<Statements> call = api.statements(AppPreferences.getUserId(mainActivity));
        call.enqueue(new Callback<Statements>() {
            @Override
            public void onResponse(@NonNull Call<Statements> call,
                                   @NonNull Response<Statements> response) {
                if (response.isSuccessful() && response.body() != null) {
                    statements.clear();
                    statements.addAll(response.body().getStatementList());
                    fragment.notifyAdapter();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Statements> call, @NonNull Throwable t) {
                Utils.createToast(mainActivity.getString(R.string.error_statements));
            }
        });
    }

}
