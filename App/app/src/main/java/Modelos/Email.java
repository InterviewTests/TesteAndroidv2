package Modelos;

import Padrões.ValoresPadrao;

public class Email {
    public static boolean checkEmail(String email){
        return email.matches("^[A-Za-z0-9][A-Za-z0-9#$%&*+\\-_\\.]+@[A-Za-z0-9]+\\.com");
    }



    private static boolean caractereUnico(byte[] caracteres, int caractere){
        boolean resultado = true;
        int cont = 0;
        for(int i = 0; i < caracteres.length; i++)
            if (caracteres[i] == caractere)
                cont++;
        if (cont > 1) resultado = false;
        return resultado;
    }
}
