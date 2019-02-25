package com.brunorfreitas.teste_ibm_santander.helper;

import android.content.Context;
import android.content.SharedPreferences;

public class Preferencias {

    private Context context;
    public SharedPreferences preferences;
    private String NOME_ARQUIVO = "teste_ibm_santander.preferencias";
    private int MODE = 0;
    private SharedPreferences.Editor editor;

    private final String CHAVE_USUARIO = "NomeUsuarioSalvo";


    public Preferencias(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences(NOME_ARQUIVO, MODE);

        editor = preferences.edit();
    }

    public void salvarUsuarioPreferencias(String nomeUsuarioSalvo) {
        editor.putString(CHAVE_USUARIO, nomeUsuarioSalvo);
        editor.commit();
    }

    public String getNomeUsuarioSalvo() {

        return preferences.getString(CHAVE_USUARIO, null);
    }
}