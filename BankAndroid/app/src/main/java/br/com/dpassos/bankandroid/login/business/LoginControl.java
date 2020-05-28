package br.com.dpassos.bankandroid.login.business;

import br.com.dpassos.bankandroid.login.data.Factory;
import br.com.dpassos.bankandroid.login.data.LoginRepository;
import br.com.dpassos.bankandroid.login.data.LoginRequest;

public class LoginControl {
    public enum LoginStatus{
        INVALID_PASS, INVALID_USER, USER_NO_LOGED
    }
    public static class LoginException extends Exception{
        public LoginStatus status;

        LoginException(LoginStatus loginStatus) {
            super(loginStatus.name());
            this.status = loginStatus;
        }
    }
    public UserAccount login(String user, String password) throws LoginException{
        UserAccount userAccount;
        boolean isEmail = isEmail(user);
        boolean isCPF = isCPF(user);
        boolean isPassOk = isPasswordOk(password);

        if(!isCPF && !isEmail) {
            throw new LoginException(LoginStatus.INVALID_USER);
        }else if(!isPassOk) {
            throw new LoginException(LoginStatus.INVALID_PASS);
        }

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.user = user;
        loginRequest.password = password;

        LoginRepository loginRepository = new Factory().getRepositoryImpl();
        userAccount = loginRepository.login(loginRequest);

        if(userAccount == null) throw new LoginException(LoginStatus.USER_NO_LOGED);

        Login login = new Login();
        login.user = user;
        loginRepository.saveLogin(login);

        return userAccount;
    }

    public Login lastLogin() {
        LoginRepository loginRepository = new Factory().getRepositoryImpl();
        Login login = loginRepository.currentLogin();
        return login;
    }

    private static boolean isPasswordOk(String txt) {
        return txt!= null
                && txt.matches(".{0,}[a-z]{1,}.{0,}")
                && txt.matches(".{0,}[A-Z]{1,}.{0,}")
                && txt.matches(".{0,}[0-9]{1,}.{0,}")
                && txt.matches(".{0,}[!@#$%&*()]{1,}.{0,}");
    }

    private static boolean isEmail(String txt) {
        return txt!= null && txt.toLowerCase().matches("[a-z0-9.]{1,}@[a-z0-9.]{1,}");
    }

    private static boolean isCPF(String txt) {
        return txt!= null && txt.matches("[0-9]{3}[.][0-9]{3}[.][0-9]{3}-[0-9]{2}");
    }
}
