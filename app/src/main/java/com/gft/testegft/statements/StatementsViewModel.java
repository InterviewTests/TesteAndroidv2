package com.gft.testegft.statements;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.gft.testegft.login.data.LoginResponse;
import com.gft.testegft.network.ApiRepository;
import com.gft.testegft.util.GsonManager;
import com.gft.testegft.util.SharedPreferenceManager;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

import static com.gft.testegft.util.Constants.LOGIN_RESPONSE_FLAG;

public class StatementsViewModel extends ViewModel {

    private final ApiRepository apiRepository;
    private CompositeDisposable disposable;

    @Inject
    StatementsViewModel(ApiRepository apiRepository) {
        this.apiRepository = apiRepository;
        disposable = new CompositeDisposable();

        fetchStatements();
        getUser();
    }


    void fetchStatements() {

    }

    private void getUser() {
        String userDataAsString = SharedPreferenceManager.getName(LOGIN_RESPONSE_FLAG);
        if (!userDataAsString.equals("")){
            LoginResponse userData = GsonManager.fromJson(userDataAsString, LoginResponse.class);
        }
    }

}
