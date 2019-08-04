package Interadores;

import android.widget.EditText;

import Modelos.Usuario;

public class AdaptadorUsuarioViewModel {
    public static Usuario createUserFromEdittext(EditText etUser, EditText etPassword){
        return new Usuario(etUser.getText().toString(), etPassword.getText().toString());
    }
}
