package Modelos;

import Padrões.ValoresPadrao;

public class Senha {
    public static boolean checkSenha(String email){
        byte[] caracteres = email.getBytes();
        return verificaAlgarismos(caracteres) && verificaMaiusculas(caracteres) &&
                verificaCaracteresEspeciais(caracteres);
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
        return resultado;
    }
}
