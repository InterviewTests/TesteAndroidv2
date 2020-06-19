package com.example.testeandroidv2.loginScreen;

import java.lang.ref.WeakReference;

interface LoginPresenterInput {
    void presentLoginData(LoginResponse response);
}


public class LoginPresenter implements LoginPresenterInput {

    public WeakReference<LoginActivityInput> output;

    @Override
    public void presentLoginData(LoginResponse response) {
        if(response.userAccount != null) {
            UserModel userModel = response.userAccount;
            output.get().displayLoginData(getUserViewModel(userModel));
        }else{
            output.get().displayLoginErro(response.error);
        }
    }

    private UserViewModel getUserViewModel(UserModel userModel){
        UserViewModel userViewModel = new UserViewModel();
        userViewModel.name = userModel.name;
        userViewModel.agency = userModel.agency;
        userViewModel.bankAccount = userModel.bankAccount;
        userViewModel.balance = userModel.balance;
        return userViewModel;
    }

}
