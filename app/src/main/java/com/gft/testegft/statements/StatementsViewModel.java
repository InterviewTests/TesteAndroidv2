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

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

import static com.gft.testegft.util.Constants.LOGIN_RESPONSE_FLAG;
import static com.gft.testegft.util.Constants.REQUEST_STATEMENTS_ERROR;
import static com.gft.testegft.util.Constants.USER_ERROR;

public class StatementsViewModel extends ViewModel {

    private final ApiRepository apiRepository;
    private CompositeDisposable disposable;

    private MutableLiveData<UserData> userData = new MutableLiveData<>();

    private MutableLiveData<List<Statement>> statements = new MutableLiveData<>();
    private MutableLiveData<String> errorMessage = new MutableLiveData<>();

    public MutableLiveData<UserData> getUserData() {
        return userData;
    }

    public MutableLiveData<List<Statement>> getStatements() {
        return statements;
    }

    MutableLiveData<String> getErrorMessage() {
        return errorMessage;
    }

    @Inject
    StatementsViewModel(ApiRepository apiRepository) {
        this.apiRepository = apiRepository;
        disposable = new CompositeDisposable();

        getUser();
        fetchStatements();
    }


    private void fetchStatements() {
        if (userData.getValue() != null)
            disposable.add(apiRepository.get(String.valueOf(userData.getValue().getId()))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(new DisposableSingleObserver<StatementResponse>() {
                        @Override
                        public void onSuccess(StatementResponse statementResponse) {
                            statements.setValue(statementResponse.getStatementList());
                        }

                        @Override
                        public void onError(Throwable e) {
                            errorMessage.setValue(REQUEST_STATEMENTS_ERROR);
                        }
                    }));
        else
            errorMessage.setValue(USER_ERROR);
    }

    private void getUser() {
        String loginResponseString = SharedPreferenceManager.getName(LOGIN_RESPONSE_FLAG);
        if (!loginResponseString.equals("")) {
            LoginResponse loginResponse = GsonManager.fromJson(loginResponseString, LoginResponse.class);
            UserData userData = new UserData(loginResponse);

            this.userData.setValue(userData);
        }
    }

}
