package br.com.giovanni.testebank.Interactor;

import br.com.giovanni.testebank.Model.LoginControlValidation;
import br.com.giovanni.testebank.Model.SetLoginJson;
import br.com.giovanni.testebank.Helpers.LoginTask;
import br.com.giovanni.testebank.Presenter.IPresenterLogin;

public class LoginInteractor {

    public IPresenterLogin presenterLoginImput;

    public LoginInteractor(IPresenterLogin presenterLoginImput) {
        this.presenterLoginImput = presenterLoginImput;
    }

    public void getLoginAcces(String getUserConvert, String getPasswordConvert) {


        LoginControlValidation loginControlValidation = new LoginControlValidation(getUserConvert, getPasswordConvert);
        SetLoginJson setJs = new SetLoginJson();
        setJs.getUser(getUserConvert);
        setJs.getPassword(getPasswordConvert);

        if (loginControlValidation.loginControlValidation()) {

            try {
                LoginTask task = new LoginTask(presenterLoginImput);
                task.setParametros(setJs.setLoginJson());
                task.execute();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
