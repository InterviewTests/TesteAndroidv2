package br.com.giovanni.testebank.Interactor;

public class UserControl {

    private String user;


    public UserControl(String user){
        this.user = user;
        System.out.println(this.user);
    }

    public boolean emailValidation (){
        if (user.contains("@")){
            return true;
        }else {
            return false;
        }
    }




}
