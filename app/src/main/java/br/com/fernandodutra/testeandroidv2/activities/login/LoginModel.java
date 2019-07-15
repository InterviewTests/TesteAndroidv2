package br.com.fernandodutra.testeandroidv2.activities.login;

import java.util.ArrayList;
import java.util.List;

import br.com.fernandodutra.testeandroidv2.models.ErrorMessage;
import br.com.fernandodutra.testeandroidv2.models.Login;
import br.com.fernandodutra.testeandroidv2.models.UserAccountWorker;

/**
 * Created by Fernando Dutra
 * User: Fernando Dutra
 * Date: 26/06/2019
 * Time: 21:11
 * TesteAndroidv2_CleanCode
 */
public class LoginModel {

}

class LoginViewModel {
    public UserAccountWorker userAccountWorker;
    public List<ErrorMessage> errorMessage;
}

class LoginRequest {
    private Login login;

    public LoginRequest() {
    }

    public LoginRequest(Login login) {
        this.login = login;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }
}

class LoginResponse {
    public UserAccountWorker userAccountWorker;
    public List<ErrorMessage> errorMessage;

    public LoginResponse() {
        this.errorMessage = new ArrayList<>();
    }
}
