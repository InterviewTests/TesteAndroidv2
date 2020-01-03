package resource.estagio.testesantander.login;

import android.widget.Toast;

import resource.estagio.testesantander.infra.BaseCallback;
import resource.estagio.testesantander.domain.User;

public class LoginPresenter implements LoginContract.Presenter {
    public LoginContract.View view;

    public LoginPresenter(LoginContract.View view) {
        this.view = view;
    }

    @Override
    public void login(String username, String password) {
        final User user = new User(username, password);
        user.repository = new LoginAuth();
        try {
            view.showProgress(true); // durante o a requisição da api, ele habilita o progress
            user.login(new BaseCallback<LoginResponse>() {
                @Override
                public void onSuccessful(LoginResponse value) {
                    view.navigateToList(value.getUser());// aqui leva pra intent da tela de Statement
                    view.showProgress(false);//quando a requisição der "OK", ele desabilita o progress
                }

                @Override
                public void onUnsucessful(String error) {
                    view.showProgress(false);//se a requisição deu falha, ele desabilita o progress
                    Toast.makeText(view.getActivity(), "Sem conexão",
                            Toast.LENGTH_SHORT).show(); //se o smartphone estiver sem conexão,
                    // esse Toast aparecerá na tela para o usuario
                }
            });


        } catch (Exception e) {
            view.showProgress(false);//se a requisição falhar, ele desabilita o progress bar
            e.printStackTrace();
        }
    }

    @Override
    public boolean validUsername(String user) {
        if (user.matches("[0-9]+")) { // regex para a validação de CPF
            if (user.length() == 11) {
                view.enableButton(true);//se a validação for OK, o botão de "Login" é habilitado
            } else {
                view.errorUsername("CPF inválido");
                view.enableButton(false);//se a validação falhar, o botão de "Login" é desabilitado
                return false;
            }

        } else {
            if (!user.matches("[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" + // regex para a validação de email
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+")) {
                view.errorUsername("Email inválido");//se a validação falhar, essa mensagem de erro aparecerá para o usuário

                view.enableButton(false);//se a validação falhar, o botão de "Login" é desabilitado
                return false;
            }
            view.enableButton(true);//se a validação for OK, o botão de "Login" é habilitado

        }

        return true;
    }

    @Override
    public boolean validPassword(String password) {
        if (password.matches("^(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]" +
                "(?=.*[!@#$*_%^&+=])(?=\\S+$)(?=\\S+$).{3,}$")) { // regex para validação de senha

            return true;
        }
        view.errorPassword("A senha deve no minímo 4 caracteres, 1 caractere maiúsculo," +
                " 1 caractere especial e 1 número");//se a validação falhar, essa mensagem de erro aparecerá para o usuário
        return false;
    }
}
