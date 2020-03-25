package br.com.amilton.repository;

import android.util.Log;

import java.io.IOException;

import br.com.amilton.model.Login;
import br.com.amilton.retrofit.RetrofitInstance;
import br.com.amilton.retrofit.WebService;
import retrofit2.Response;

public class LoginRepository {
    private final String TAG = LoginRepository.class.getSimpleName();
    private final WebService webService = RetrofitInstance.geWebService();

    public Login getUser(String login, String password) {

        Login loginUser = Login.EMPTY_LOGIN;
        try {
            Response<Login> response = webService.login(login, password).execute();
            if (response.isSuccessful() && response.errorBody() == null) {
                loginUser = response.body();
            }
        } catch (IOException e) {
            Log.e(TAG, e.getMessage(), e);
        }

        return loginUser;
    }

}
