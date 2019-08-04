package Interadores;

import org.json.JSONException;
import org.json.JSONStringer;

import Modelos.Usuario;

public class LoginRequest {
    private Usuario usuario;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String realizaRequisicao(){
        return buildJson();
    }

    private String buildJson(){
        JSONStringer js = new JSONStringer();

        try {
            js.object().key("login").object().
                    key("user").value(usuario.getLogin()).
                    key("password").value(usuario.getSenha()).
                    endObject().
               endObject();
            return js.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "";
    }
}