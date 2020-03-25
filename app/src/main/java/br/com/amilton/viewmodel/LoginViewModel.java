package br.com.amilton.viewmodel;

import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Patterns;

import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import br.com.amilton.model.Login;
import br.com.amilton.repository.LoginRepository;
import br.com.amilton.util.StringsUtils;
import br.com.amilton.util.CPFUtils;

public class LoginViewModel extends ViewModel {

    public static final String ERROR_LOGIN = "invalid login!";
    public static final String ERROR_PASSWORD = "invalid password!";
    public ObservableField<String> login = new ObservableField<>();
    public ObservableField<String> password = new ObservableField<>();

    public MutableLiveData<Boolean> loading = new MutableLiveData<>();
    private MutableLiveData<String> errorMessage = new MutableLiveData<>();

    private MutableLiveData<Login> loginMutableLiveData = new MutableLiveData<>();

    private LoginRepository loginRepository;

    public LoginViewModel() {
        loginRepository = new LoginRepository();
    }

    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }

    public void onClick() {
        String mLogin = login.get();
        String mPassword = password.get();

        if (TextUtils.isEmpty(mLogin) || (!Patterns.EMAIL_ADDRESS.matcher(mLogin).matches()) && !CPFUtils.isCPF(mLogin)) {
            errorMessage.postValue(ERROR_LOGIN);
            return;
        }

        if (TextUtils.isEmpty(mPassword) || !StringsUtils.checkPassword(mPassword)) {
            errorMessage.postValue(ERROR_PASSWORD);
            return;
        }
        authenticator();
    }

    public MutableLiveData<Login> getUser() {
        return loginMutableLiveData;
    }

    public void authenticator() {
        loading.postValue(true);
        new AsyncTask<String, Void, Login> () {

            @Override
            protected Login doInBackground(String... strings) {
                return loginRepository.getUser(strings[0], strings[1]);
            }

            @Override
            protected void onPostExecute(Login login) {
                super.onPostExecute(login);
                loginMutableLiveData.postValue(login);
                loading.postValue(false);
            }
        }.execute(login.get(), password.get());
    }
}
