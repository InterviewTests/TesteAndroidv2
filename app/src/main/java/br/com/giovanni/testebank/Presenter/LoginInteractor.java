package br.com.giovanni.testebank.Presenter;


import br.com.giovanni.testebank.Interactor.LoginControlValidation;
import br.com.giovanni.testebank.Model.SetLoginJson;

public class LoginInteractor {

    public PresenterLoginImput presenterLoginImput;

    public LoginInteractor (PresenterLoginImput presenterLoginImput){
        this.presenterLoginImput = presenterLoginImput;
    }

    public void getLoginAcces (String getUserConvert, String getPasswordConvert){

        LoginControlValidation loginControlValidation = new LoginControlValidation(getUserConvert, getPasswordConvert);
        SetLoginJson setJs = new SetLoginJson();
        setJs.getUser(getUserConvert);
        setJs.getPassword(getPasswordConvert);

        if (loginControlValidation.loginControlValidation()){

            try {
                LoginTask task = new LoginTask(presenterLoginImput);
                task.setParametros(setJs.setLoginJson());
                task.execute();

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Retornou no else");
        }

    }
}
