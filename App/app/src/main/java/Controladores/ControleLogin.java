package Controladores;

import Interadores.LoginRequest;
import Modelos.CPF;
import Modelos.Email;
import Modelos.Senha;
import Modelos.Usuario;

public class ControleLogin {
    private Usuario user;
    private LoginRequest request;

    public ControleLogin(Usuario user) {
        this.user = user;
    }

    public boolean validaUsuario() {
        boolean resposta = false;
        if (user.getLogin().isEmpty() || user.getSenha().isEmpty())
            return resposta;
        if (user.getLogin().contains("@"))
            resposta = testaEmail();
        else
            resposta = testaCPF();

        return resposta && testaSenha();
    }

    private boolean testaCPF() {
        String cpf = user.getLogin();
        if (CPF.checkCPF(cpf))
            return true;
        return false;
    }

    private boolean testaEmail() { //mudar verificacao para senha
        return Email.checkEmail(user.getLogin());
    }

    private boolean testaSenha() {
        return Senha.checkSenha(user.getSenha());
    }

    public void logar(){

    }
}
