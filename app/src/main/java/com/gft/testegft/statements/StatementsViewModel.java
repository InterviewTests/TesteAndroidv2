package com.gft.testegft.statements;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.gft.testegft.login.data.LoginResponse;
import com.gft.testegft.network.ApiRepository;
import com.gft.testegft.statements.data.Statement;
import com.gft.testegft.statements.data.StatementResponse;
import com.gft.testegft.statements.data.UserData;
import com.gft.testegft.util.GsonManager;
import com.gft.testegft.util.SharedPreferenceManager;

import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

import static com.gft.testegft.util.Constants.LOGIN_RESPONSE_FLAG;

public class StatementsViewModel extends ViewModel {

    private final ApiRepository apiRepository;
    private CompositeDisposable disposable;

    private MutableLiveData<UserData> userData = new MutableLiveData<>();

    private MutableLiveData<List<Statement>> statements = new MutableLiveData<>();

    public MutableLiveData<UserData> getUserData() {
        return userData;
    }

    public MutableLiveData<List<Statement>> getStatements() {
        return statements;
    }

    @Inject
    StatementsViewModel(ApiRepository apiRepository) {
        this.apiRepository = apiRepository;
        disposable = new CompositeDisposable();

        getUser();
        fetchStatements();
    }


    void fetchStatements() {
        disposable.add(apiRepository.get(String.valueOf(userData.getValue().getId()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<StatementResponse>() {
                    @Override
                    public void onSuccess(StatementResponse statements) {
                        Log.i("SUCESSO", GsonManager.toJson(statements));
                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.i("LASCOU", GsonManager.toJson(e));
                    }
                }));

    }

    private void getUser() {
        String loginResponseString = SharedPreferenceManager.getName(LOGIN_RESPONSE_FLAG);
        if (!loginResponseString.equals("")){
            LoginResponse loginResponse = GsonManager.fromJson(loginResponseString, LoginResponse.class);
            UserData userData = new UserData(loginResponse);

            this.userData.setValue(userData);
        }
    }

}
