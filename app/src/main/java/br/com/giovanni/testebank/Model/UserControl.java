package br.com.giovanni.testebank.Model;

public class UserControl {

    private String user;

    public UserControl(String user) {
        this.user = user;
        System.out.println(this.user);
    }

    public boolean isValid() {
        if (user.contains("@")) {
            return emailValidation();
        } else {
            return CPFValid.testCPF(user.replace(".", "").replace("-", ""));
        }
    }

    private boolean emailValidation() {
        if (user.contains("@")) {
            return true;
        } else {
            return false;
        }
    }

}
