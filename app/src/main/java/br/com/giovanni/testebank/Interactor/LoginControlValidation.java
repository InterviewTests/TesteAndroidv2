package br.com.giovanni.testebank.Interactor;

public class LoginControlValidation {
    public String getUser;
    public String getPassword;

    public LoginControlValidation (String getUser, String getPassword){
        this.getUser = getUser;
        this.getPassword = getPassword;
    }

    public boolean loginControlValidation (){
        UserControl userControl = new UserControl(getUser);
        PasswordControl passwordControl = new PasswordControl(getPassword);
        System.out.println(userControl.emailValidation());
        System.out.println(passwordControl.passwordValidation());

        if (userControl.emailValidation() && passwordControl.passwordValidation() == true){
            return true;
        } else {
            return false;
        }
    }

}
