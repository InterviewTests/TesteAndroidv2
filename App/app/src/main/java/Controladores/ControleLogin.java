package Controladores;

import Modelos.CPF;
import Modelos.Email;
import Modelos.Usuario;

public class ControleLogin {
    private Usuario user;

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

        return resposta;
    }

    private boolean testaCPF() {
        String cpf = user.getLogin();
        if (CPF.checkCPF(cpf))
            return true;
        return false;
    }

    private boolean testaEmail() {
        return Email.checkEmail(user.getLogin());
    }
}
