//package Controladores;
//
//import android.content.Context;
//
//import Helpers.LoginTask;
//import Domain.CPF;
//import Domain.Email;
//import Domain.Senha;
//import Domain.Usuario;
//
//public class ControleLogin {
//    private Usuario user;
//
//    public ControleLogin(Usuario user) {
//        this.user = user;
//    }
//
//    public boolean validaUsuario() {
//        boolean resposta = false;
//        if (user.getLogin().isEmpty() || user.getSenha().isEmpty())
//            return resposta;
//        if (user.getLogin().contains("@"))
//            resposta = testaEmail();
//        else
//            resposta = testaCPF();
//
//        return resposta && testaSenha();
//    }
//
//    private boolean testaCPF() {
//        String cpf = user.getLogin();
//        if (CPF.checkCPF(cpf))
//            return true;
//        return false;
//    }
//
//    private boolean testaEmail() {
//        return Email.checkEmail(user.getLogin());
//    }
//
//    private boolean testaSenha() {
//        return Senha.checkSenha(user.getSenha());
//    }
//}
