package resource.estagio.testesantander.login;

import android.widget.Toast;

import resource.estagio.testesantander.infra.BaseCallback;
import resource.estagio.testesantander.model.User;

public class LoginPresenter implements LoginContract.Presenter{
    public LoginContract.View view;

    public LoginPresenter(LoginContract.View view) {
        this.view = view;
    }

    @Override
    public void login(String username, String password) {
        final User user= new User(username, password);
        user.repository = new LoginAuth();
        try{
            user.login(new BaseCallback<LoginResponse>() {
                @Override
                public void onSucessful(LoginResponse value) {
                    view.navigateToList(value.getUser());
                }

                @Override
                public void onUnsucessful(String error) {
                    Toast.makeText(view.getActivity(), "Sem conexão", Toast.LENGTH_SHORT).show();
                }
            });



        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean validUsername(String user) {
        if (user.matches("[0-9]+")) {
            if (user.length() == 11) {
                view.enableButton(true);
            }else {
                view.errorUsername("CPF inválido");
                view.enableButton(false);
                return false;
            }

        } else {
            if (!user.matches("[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+")) {
                view.errorUsername("Email inválido");
                view.enableButton(false);
                return false;
            }
            view.enableButton(true);

        }

        return true;
    }

    @Override
    public boolean validPassword(String password) {
        if (password.matches("^(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d](?=.*[!@#$*_%^&+=])(?=\\S+$)(?=\\S+$).{4,}$")) {

            return true;
        }
        view.errorPassword("A senha deve ter 1 carácter maiúsculo," +
                " 1 carácter especial e 1 número");

        return false;
    }
}
