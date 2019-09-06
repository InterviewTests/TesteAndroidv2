package com.example.testeandroidv2.helper;

import com.example.testeandroidv2.model.Usuario;

import org.json.JSONException;

/*
SÓ PARA DEFINIR QUAIS OS METODOS A SEREM USADOS
 */
public interface IUsuarioDAO {

    public boolean create(Usuario usuario);
    public boolean update(Usuario usuario);
    public boolean delete(Usuario usuario);
    public Object buscar(Integer userId) throws JSONException;

}
