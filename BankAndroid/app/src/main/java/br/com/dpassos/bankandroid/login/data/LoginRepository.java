package br.com.dpassos.bankandroid.login.data;

import br.com.dpassos.bankandroid.login.business.Login;
import br.com.dpassos.bankandroid.login.business.UserAccount;

public interface LoginRepository {

    UserAccount login(LoginRequest request);
    Login currentLogin();
    void saveLogin(Login login);
    void clearAccount();
    UserAccount getCurrentAccount();
}
