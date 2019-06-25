package br.com.fernandodutra.testeandroidv2.activities;

import br.com.fernandodutra.testeandroidv2.models.Login;
import br.com.fernandodutra.testeandroidv2.models.UserAccount;
import br.com.fernandodutra.testeandroidv2.models.UserAccountResponse;

/**
 * Created by Fernando Dutra
 * User: Fernando Dutra
 * Date: 22/06/2019
 * Time: 16:49
 * TesteAndroidv2
 */
public interface UserAccountContract {

    public interface Model {

        interface OnFinished {
            void onFinished(UserAccountResponse userAccountResponse);

            void onFailure(Throwable t);
        }

        void getUserAccount(final OnFinished onFinishedListener, Login login);

        void insert(UserAccount userAccount);

        void update(UserAccount userAccount);

        void delete(UserAccount userAccount);

        UserAccount findById(int userId);
    }

    public interface View {
        void showMessage(int response);

        void showMessage(String response);

        void navigate(Integer userId);
    }

    public interface Presenter {

        UserAccountResponse getUserAccount();
        void setUserAccount(UserAccountResponse userAccountResponse);

        void login(Login login);

        void loginApi(Login login);

        boolean validLoginApi(UserAccountResponse userAccountResponse);

        void saveLogin(UserAccountResponse userAccountResponse);

    }

}
