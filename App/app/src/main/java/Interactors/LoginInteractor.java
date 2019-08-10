package Interactors;

import Models.CPF;
import Models.Email;
import Models.Senha;
import Models.Usuario;
import Presenters.LoginPresenterInput;
import Services.LoginTask;

import static Padrões.ValoresPadrao.*;

public class LoginInteractor {
    private Usuario usuario = null;
    private LoginPresenterInput presenter;

    public LoginInteractor(LoginPresenterInput presenter){
        this.presenter = presenter;
    }

    public void criaUsuario(String etLogin, String etSenha) {
        int tipo = Usuario.verificaTipoIdentificacao(etLogin);
        String login = etLogin;
        Senha senha = new Senha(etSenha);
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
        return usuario;
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
