package br.com.kakobotasso.testeandroidv2.login;

import java.util.regex.Pattern;

interface LoginInteractorInput {
    void fetchLoginData(LoginRequest request);
}

public class LoginInteractor implements LoginInteractorInput {
    public LoginPresenterInput output;
    private LoginWorkerInput loginWorkerInput;

    private LoginWorkerInput getLoginWorkerInput() {
        if (loginWorkerInput == null) {
            return new LoginWorker();
        }

        return loginWorkerInput;
    }

    public void serLoginWorkerInput(LoginWorkerInput loginWorkerInput) {
        this.loginWorkerInput = loginWorkerInput;
    }


    @Override
    public void fetchLoginData(LoginRequest request) {
        loginWorkerInput = getLoginWorkerInput();
        LoginResponse loginResponse = new LoginResponse();

        if (requestIsValid(request)) {
            // here call login worker and populate loginResponse
            output.presentLoginData(loginResponse);
        } else {
            output.presentInvalidRequestData();
        }

    }

    private boolean requestIsValid(LoginRequest request) {
        if (request.getUser().isEmpty()) {
            return false;
        }

        if (request.getPassword().isEmpty()) {
            return false;
        }

        final Pattern passwordPattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$");
        return passwordPattern.matcher(request.getPassword()).matches();
    }

}
