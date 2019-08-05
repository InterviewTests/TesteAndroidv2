package Helpers;

import org.json.JSONException;
import org.json.JSONStringer;

import Modelos.Usuario;

public class UsuarioJSON {
    public static String converteParaJSON(Usuario user) {
        JSONStringer js = new JSONStringer();

        try {
            js.object().
                    key("user").value(user.getLogin()).
                    key("password").value(user.getSenha()).
               endObject();
            return js.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
