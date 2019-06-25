package br.com.fernandodutra.testeandroidv2.activities;

import br.com.fernandodutra.testeandroidv2.R;
import br.com.fernandodutra.testeandroidv2.models.Login;
import br.com.fernandodutra.testeandroidv2.models.Error;
import br.com.fernandodutra.testeandroidv2.models.UserAccount;
import br.com.fernandodutra.testeandroidv2.models.UserAccountResponse;
import br.com.fernandodutra.testeandroidv2.utils.ToolBox;

/**
 * Created by Fernando Dutra
 * User: Fernando Dutra
 * Date: 22/06/2019
 * Time: 17:02
 * TesteAndroidv2
 */
public class UserAccountPresenter implements UserAccountContract.Presenter, UserAccountContract.Model.OnFinished {

    private UserAccountContract.View view;
    private UserAccountModel userAccountModel;
    private UserAccountResponse userAccountResponse;

    public UserAccountPresenter(UserAccountContract.View view, UserAccountModel userAccountModel) {
        this.view = view;
        this.userAccountModel = userAccountModel;
    }

    @Override
    public UserAccountResponse getUserAccount() {
        return this.userAccountResponse;
    }

    @Override
    public void setUserAccount(UserAccountResponse userAccountResponse) {
        this.userAccountResponse = userAccountResponse;
    }

    @Override
    public void login(Login login) {
        boolean validaCPF = ToolBox.validCPF(login.getUser());
        boolean validEmail = ToolBox.validEmail(login.getUser());
        boolean validPassword = ToolBox.validPassword(login.getPassword());

        if ((!validaCPF) && (!validEmail)) {
            view.showMessage(R.string.username_required);
        }

        if (!validPassword) {
            view.showMessage(R.string.password_required);
        }

        if ((validaCPF) || (validEmail) && validPassword) {
            loginApi(login);
        }
    }

    @Override
    public void loginApi(Login login) {
        userAccountModel.getUserAccount(this, login);
    }

    @Override
    public boolean validLoginApi(UserAccountResponse userAccountResponse) {
        if (userAccountResponse == null) {
            view.showMessage(R.string.login_notfound);
            return false;
        } else {
            Error error;
            error = userAccountResponse.getError();

            if (error == null) {
                return true;
            } else {
                if (error.getMessage().equals("")) {
                    return true;
                } else {
                    switch (error.getCode()) {
                        case 53:
                            view.showMessage(R.string.login_notfound_code_53);
                            return false;
                        default:
                            view.showMessage(R.string.login_notfound);
                            return false;
                    }
                }
            }
        }
    }

    @Override
    public void saveLogin(UserAccountResponse userAccountResponse) {
        UserAccount userAccount = userAccountResponse.getUserAccount();
        if (userAccount == null) {
            return;
        } else {
            int userId = userAccount.getUserId();
            userAccount = userAccountModel.findById(userId);

            if (userAccount == null) {
                userAccountModel.insert(userAccountResponse.getUserAccount());
            } else {
                userAccountModel.update(userAccount);
            }
        }
    }

    @Override
    public void onFinished(UserAccountResponse userAccountResponse) {
        if (userAccountResponse != null) {
            this.setUserAccount(userAccountResponse);
            if (validLoginApi(userAccountResponse)) {
                saveLogin(userAccountResponse);
                view.navigate(userAccountResponse.getUserAccount().getUserId());
            }
        } else {
            setUserAccount(userAccountResponse);
            view.showMessage(R.string.login_notfound);
        }
    }

    @Override
    public void onFailure(Throwable t) {
        view.showMessage(t.getMessage());
    }
}
