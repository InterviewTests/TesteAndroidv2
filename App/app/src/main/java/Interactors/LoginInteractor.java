package Interactors;

import android.content.Context;
import android.widget.EditText;

import Domain.CPF;
import Domain.Email;
import Domain.Senha;
import Domain.Usuario;
import Helpers.LoginTask;

import static Padrões.ValoresPadrao.*;

public class LoginInteractor {
    private Usuario usuario;

    public LoginInteractor(EditText etLogin, EditText etSenha){
        int tipo = Usuario.verificaTipoIdentificacac(etLogin.getText().toString());
        criaUsuario(etLogin, etSenha, tipo);
    }

    private void criaUsuario(EditText etLogin, EditText etSenha, int tipo) {
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

    public boolean validaUsuario() {
        return usuario.ehValido();
    }

    public void logar(Context ctx){
        String json = usuario.getJson();
        LoginTask lt = new LoginTask(json);
        lt.setContext(ctx);
        lt.execute();
    }
}
