package br.com.fernandodutra.testeandroidv2.activities.login;

import android.content.Context;

import java.util.ArrayList;

import br.com.fernandodutra.testeandroidv2.R;
import br.com.fernandodutra.testeandroidv2.models.Error;
import br.com.fernandodutra.testeandroidv2.models.ErrorMessage;
import br.com.fernandodutra.testeandroidv2.models.UserAccount;
import br.com.fernandodutra.testeandroidv2.models.UserAccountWorker;
import br.com.fernandodutra.testeandroidv2.utils.ToolBox;

/**
 * Created by Fernando Dutra
 * User: Fernando Dutra
 * Date: 26/06/2019
 * Time: 21:13
 * TesteAndroidv2_CleanCode
 */

interface LoginInteractorInput {
    void fetchLoginMetaData(Context context, LoginRequest request);
}

interface OnFinished {
    void onFinished(UserAccountWorker userAccountWorker);

    void onFailure(Throwable t);
}

public class LoginInteractor implements LoginInteractorInput, OnFinished {

    public LoginPresenterInput loginPresenterInput;
    public LoginApiWorkerInput loginApiWorkerInput;
    public LoginResponse loginResponse;
    //
    public LoginSQliteWorker loginSQliteWorker;

    public static String TAG = LoginInteractor.class.getSimpleName();

    public LoginApiWorkerInput getLoginApiInput() {
        if (loginApiWorkerInput == null) return new LoginApiWorker();
        return loginApiWorkerInput;
    }

    public LoginSQliteWorker getLoginSQlite(Context context) {
        if (loginSQliteWorker == null) return new LoginSQliteWorker(context);
        return loginSQliteWorker;
    }

    @Override
    public void fetchLoginMetaData(Context context, LoginRequest loginRequest) {
        loginApiWorkerInput = getLoginApiInput();
        loginSQliteWorker = getLoginSQlite(context);
        loginResponse = new LoginResponse();

        this.login(loginRequest);
    }

    public boolean login(LoginRequest loginRequest) {
        boolean validaCPF = ToolBox.validCPF(loginRequest.getLogin().getUser());
        boolean validEmail = ToolBox.validEmail(loginRequest.getLogin().getUser());
        boolean validPassword = ToolBox.validPassword(loginRequest.getLogin().getPassword());

        if ((!validaCPF) && (!validEmail)) {
            Integer id = R.string.username_required;
            String message = "";

            loginResponse.errorMessage.add(new ErrorMessage(id, message));
            loginPresenterInput.presentLoginMetaData(loginResponse);
            return false;
        }

        if (!validPassword) {
            Integer id = R.string.password_required;
            String message = "";

            loginResponse.errorMessage.add(new ErrorMessage(id, message));
            loginPresenterInput.presentLoginMetaData(loginResponse);
            return false;
        }

        if ((validaCPF) || (validEmail) && validPassword) {
            loginApi(loginRequest);
            return true;
        }
        loginPresenterInput.presentLoginMetaData(loginResponse);
        return false;
    }

    public void loginApi(LoginRequest loginRequest) {
        loginApiWorkerInput.getUserAccountResponse(this, loginRequest);
    }

    public boolean validLoginApi(UserAccountWorker userAccountWorker) {
        Integer id = -1;
        String message = "";

        if (userAccountWorker == null) {
            id = R.string.login_notfound;
            loginResponse.errorMessage.add(new ErrorMessage(id, message));
            return false;
        } else {
            Error error;
            error = userAccountWorker.getError();

            if (error == null) {
                return true;
            } else {
                if (error.getMessage().equals("")) {
                    return true;
                } else {
                    switch (error.getCode()) {
                        case 53:
                            id = R.string.login_notfound_code_53;
                            loginResponse.errorMessage.add(new ErrorMessage(id, message));
                            return false;
                        default:
                            id = R.string.login_notfound;
                            loginResponse.errorMessage.add(new ErrorMessage(id, message));
                            return false;
                    }
                }
            }
        }
    }

    public void saveLogin(UserAccountWorker userAccountWorker) {
        UserAccount userAccount = userAccountWorker.getUserAccount();
        if (userAccount == null) {
            return;
        } else {
            int userId = userAccount.getUserId();
            userAccount = loginSQliteWorker.findById(userId);

            if (userAccount == null) {
                loginSQliteWorker.insert(userAccountWorker.getUserAccount());
            } else {
                loginSQliteWorker.update(userAccount);
            }
        }
    }

    @Override
    public void onFinished(UserAccountWorker userAccountWorker) {
        if (userAccountWorker != null) {
            loginResponse.userAccountWorker = userAccountWorker;
            loginResponse.errorMessage = new ArrayList<>();

            if (validLoginApi(loginResponse.userAccountWorker)) {
                saveLogin(loginResponse.userAccountWorker);
            }
        } else {
            loginResponse.userAccountWorker = new UserAccountWorker();

            Integer id = -1;
            String message = "";

            id = R.string.login_notfound;
            loginResponse.errorMessage.add(new ErrorMessage(id, message));
        }
        loginPresenterInput.presentLoginMetaData(loginResponse);
    }

    @Override
    public void onFailure(Throwable t) {
        Integer id = -1;
        String message = t.getMessage();

        loginResponse.userAccountWorker = new UserAccountWorker();
        loginResponse.errorMessage.add(new ErrorMessage(id, message));

        loginPresenterInput.presentLoginMetaData(loginResponse);
    }
}
