package Modelos;

import Padrões.ValoresPadrao;

public class Email {
    public static boolean checkEmail(String email){
        byte[] caracteres = email.getBytes();
        return verificaMaiusculas(caracteres) && verificaAlgarismos(caracteres) &&
                verificaCaracteresEspeciais(caracteres) &&
                caractereUnico(caracteres, ValoresPadrao.VALOR_ARROBA) &&
                email.contains(".com");
    }

    private static boolean verificaMaiusculas(byte[] caracteres){
        boolean resultado = false;
        for(int i = 0; i < caracteres.length; i++)
            if (caracteres[i] >= ValoresPadrao.A_MAIUSCULO && caracteres[i] <= ValoresPadrao.Z_MAIUSCULO)
                resultado = true;
        return resultado;
    }

    private static boolean verificaAlgarismos(byte[] caracteres){
        boolean resultado = false;
        for(int i = 0; i < caracteres.length; i++) {
            if (caracteres[i] >= ValoresPadrao.CARACTERE_0 && caracteres[i] <= ValoresPadrao.CARACTERE_9)
                resultado = true;
        }
        return resultado;
    }

    private static boolean verificaCaracteresEspeciais(byte[] caracteres){
        boolean resultado = false;
        for(int i = 0; i < caracteres.length; i++)
            for(int j = 0; j < ValoresPadrao.CARACTERES_ESPECIAIS.length; j++)
                if (caracteres[i] == ValoresPadrao.CARACTERES_ESPECIAIS[j])
                    resultado = true;
        if (!resultado)
            resultado = !caractereUnico(caracteres, ValoresPadrao.CARACTERE_PONTO);
        return resultado;
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
