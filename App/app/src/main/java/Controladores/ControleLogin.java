package Controladores;

import Modelos.Usuario;
import Padrões.ValoresPadrao;

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
        String cpf = user.getLogin()
                .replace("-","")
                .replace(".", "");
        if (cpf.matches("[0-9]{" + ValoresPadrao.TAMANHO_CPF +
                "," + ValoresPadrao.TAMANHO_CPF + "}"))
            return true;
        return false;
    }

    private boolean testaEmail() {
        return true;
    }
}
