package br.com.accenture.santander.wallacebaldenebre.model;

public class LoginModel {
    private String user;
    private String password;

    public LoginModel(){
    }

    public LoginModel(String user, String password){
        this.user = user;
        this.password = password;
    }

    public String getuser() {
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
