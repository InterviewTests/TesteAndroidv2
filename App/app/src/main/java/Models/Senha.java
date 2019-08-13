package Models;

import Padrões.ValoresPadrao;

public class Senha {
    private String senha;

    public Senha(String senha){
        this.senha = senha;
    }

    public boolean ehValida(){
        return checkSenha(getSenha());
    }

    public static boolean checkSenha(String senha){
        if (senha.isEmpty())
            return false;
        byte[] caracteres = senha.getBytes();
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

    public String getSenha() {
        return senha;
    }
}
