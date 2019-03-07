package br.com.kakobotasso.testeandroidv2.login;

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
        // here call login worker and populate loginResponse
        output.presentLoginData(loginResponse);
    }
}
