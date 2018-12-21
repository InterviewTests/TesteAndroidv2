package teste.claudio.com.testsantander;

import android.widget.Toast;
import android.content.Context;

public class Util {

    //Exibe a mensagem na tela
    public static void msg(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }

    //Validar a senha digitada
    public static boolean ValidarSenha(String senha) {
        if (senha.length() < 6) return false;

        boolean achouNumero = false;
        boolean achouMaiuscula = false;
        boolean achouMinuscula = false;
        boolean achouSimbolo = false;

        for (char c : senha.toCharArray()) {
            if (c >= '0' && c <= '9') {
                achouNumero = true;
            } else if (c >= 'A' && c <= 'Z') {
                achouMaiuscula = true;
            } else if (c >= 'a' && c <= 'z') {
                achouMinuscula = true;
            } else {
                achouSimbolo = true;
            }
        }
        return achouNumero && achouMaiuscula && achouMinuscula && achouSimbolo;
    }

    //Validar se o user digitou somente numeros para o cpf
    public static boolean ValidarNumeros(String numeros) {
        if (numeros.length() < 6) return false;

        boolean achouNumero = false;

        for (char c : numeros.toCharArray()) {
            if (c >= '0' && c <= '9') {
                achouNumero = true;
            }
        }
        return achouNumero;
    }


}
