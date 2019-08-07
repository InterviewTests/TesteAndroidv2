package Domain;

import org.json.JSONException;
import org.json.JSONStringer;

import Padrões.ValoresPadrao;

public class Usuario {
    private int tipo;
    private Email email;
    private Senha senha;
    private CPF cpf;

    private Usuario(Senha senha){
        this.senha = senha;
    }

    public Usuario(Email email, Senha senha){
        this(senha);
        this.email = email;
        tipo = ValoresPadrao.EMAIL;
    }

    public Usuario(CPF cpf, Senha senha) {
        this(senha);
        this.cpf = cpf;
        tipo = ValoresPadrao.CPF;
    }

    public boolean ehValido(){
        if (tipo == ValoresPadrao.CPF)
            return cpf.ehValida() && senha.ehValida();
        else
            return email.ehValido() && senha.ehValida();
    }

    public static int verificaTipoIdentificacac(String login){
        if (login.contains("@"))
            return 1;
        else
            return 0;
    }

    private String getLoginString(){
        if (tipo == ValoresPadrao.CPF)
            return cpf.getCpf();
        else
            return email.getEmail();
    }

    public String getJson() {
        JSONStringer js = new JSONStringer();

        try {
            js.object().
                    key("user").value(getLoginString()).
                    key("password").value(senha.getSenha()).
                    endObject();
            return js.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
