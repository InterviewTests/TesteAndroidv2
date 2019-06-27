package com.bilulo.androidtest04.ui.login.contract;

import com.bilulo.androidtest04.data.model.UserAccountModel;
import com.bilulo.androidtest04.data.model.response.LoginResponse;

public interface LoginContract {
    interface ActivityContract {
        void loginSuccessful(UserAccountModel userAccountModel);

        void displayError();
    }

    interface InteractorContract {
        void performLogin(String user, String password);

        void setLoginResponse(LoginResponse response, boolean isSuccessful);
    }

    interface PresenterContract {
        void setData(LoginResponse response, boolean isSuccessful);
    }

    interface WorkerContract {
        void performLogin(String user, String password);
    }

    interface RouterContract {
        void loginSuccessful(UserAccountModel userAccountModel, String s);
    }
}
