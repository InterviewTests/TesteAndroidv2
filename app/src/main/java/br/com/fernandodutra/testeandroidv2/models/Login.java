package br.com.fernandodutra.testeandroidv2.models;

/**
 * Created by Fernando Dutra
 * User: Fernando Dutra
 * Date: 19/06/2019
 * Time: 17:12
 * TesteAndroidv2
 */
public class Login  {

    private String user;
    private String password;

    public Login() {
    }

    public Login(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
