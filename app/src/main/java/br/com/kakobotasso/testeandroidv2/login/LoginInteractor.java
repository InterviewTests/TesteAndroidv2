package br.com.kakobotasso.testeandroidv2.login;

import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        final LoginResponse loginResponse = new LoginResponse();

        if (requestIsValid(request)) {
            loginWorkerInput.sendLoginInfo(request, new Callback<LoginModel>() {
                @Override
                public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                    if (response.isSuccessful()) {
                        if (response.body() != null) {
                            LoginModel body = response.body();
                            populateLoginResponse(loginResponse, body);
                            output.presentLoginData(loginResponse);
                        }
                    }
                }

                @Override
                public void onFailure(Call<LoginModel> call, Throwable t) {

                }
            });
        } else {
            output.presentInvalidRequestData();
        }

    }

    private void populateLoginResponse(LoginResponse loginResponse, LoginModel body) {
        if (body.getErrors() != null) {
            LoginResponse.Errors errors = loginResponse.getErrors();
            LoginModel.Errors bodyErrors = body.getErrors();
            errors.setMsg(bodyErrors.getMessage());

            loginResponse.setErrors(errors);
        }

        if (body.getUserAccount() != null) {
            LoginModel.UserAccount userAccount = body.getUserAccount();

            loginResponse.setName(userAccount.getName());
            loginResponse.setAgency(userAccount.getAgency());
            loginResponse.setBalance(userAccount.getBalance());
            loginResponse.setBankAccount(userAccount.getBankAccount());
            loginResponse.setUserId(userAccount.getUserId());
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
