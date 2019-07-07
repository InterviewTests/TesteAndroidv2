package com.projeto.testedevandroidsantander.ui.loginScreen;


import com.projeto.testedevandroidsantander.model.UsuarioDTO;
import com.projeto.testedevandroidsantander.util.Uteis;

interface LoginInteractorInput {
    void fetchLoginMetaData(LoginRequest request);
    void presentLoginMetaData(UsuarioDTO usuarioDTO);
    void getUserSharedPreferences();
}

public class LoginInteractor implements LoginInteractorInput {

    public LoginPresenterInput output;

    public UsuarioWorkerInput usuarioWorkerInput;

    public static String TAG = LoginInteractor.class.getSimpleName();

    @Override
    public void fetchLoginMetaData(LoginRequest request) {
        if (isLoginValido(request)) {
            logar(request);
        }
    }

    private void logar(final LoginRequest request) {
        output.visibleProgressBar();
        usuarioWorkerInput.getUsuarioAccount(request);
    }

    @Override
    public void presentLoginMetaData(UsuarioDTO usuarioDTO){
        LoginResponse response = new LoginResponse();
        response.usuarioModel =  usuarioDTO.getUserAccount();
        output.presentLoginMetaData(response);
        output.hideProgressBar();
    }

    @Override
    public void getUserSharedPreferences() {
        output.setLoginSharedPreferences(usuarioWorkerInput.getUserSharedPreferences());
    }


    private boolean isLoginValido(LoginRequest request){

        if (!Uteis.isCPF(request.getLogin()) && !Uteis.isEmailValido(request.getLogin())) {
            output.hideProgressBar();
            output.showMessageLoginError(output.getMessageCpfError());
            return false;
        }

        if (!Uteis.isSenhaValida(request.getPassword())) {
            output.hideProgressBar();
            output.showMessageLoginError(output.getMessageSenhaError());
            return false;
        }

        return true;

    }
}
