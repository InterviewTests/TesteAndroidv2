package Interactors;

import android.widget.EditText;

import Domain.CPF;
import Domain.Email;
import Domain.Senha;
import Domain.Usuario;
import Presenters.LoginPresenterInput;
import Services.LoginTask;

import static Padrões.ValoresPadrao.*;

public class LoginInteractor {
    private Usuario usuario;
    private LoginPresenterInput presenter;

    public LoginInteractor(LoginPresenterInput presenter){
        this.presenter = presenter;
    }

    public void criaUsuario(EditText etLogin, EditText etSenha) {
        int tipo = Usuario.verificaTipoIdentificacao(etLogin.getText().toString());
        String login = etLogin.getText().toString();
        Senha senha = new Senha(etSenha.getText().toString());
        switch (tipo){
            case CPF:
                CPF cpf = new CPF(login);
                this.usuario = new Usuario(cpf, senha);
                break;
            case EMAIL:
                Email email = new Email(login);
                this.usuario = new Usuario(email, senha);
                break;
        }
    }

    public Usuario getUsuario(){
        if (usuario != null)
            return usuario;
        else
            return null;
    }

    public boolean validaUsuario() {
        return usuario.ehValido();
    }

    public void logar(){
        String json = usuario.getJson();
        LoginTask lt = new LoginTask(presenter);
        lt.setParametros(json);
        lt.execute();
    }
}
