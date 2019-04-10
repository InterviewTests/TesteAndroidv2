package com.desafiosantander.Home;

import android.service.autofill.RegexValidator;
import android.text.TextUtils;

import com.desafiosantander.LoggedUser;

import java.lang.ref.WeakReference;
import java.util.InputMismatchException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

interface HomePresenterInput {
    public void validadeUser(String user);
    public void validadePassword(String Password);
    public void login(Boolean success, LoggedUser loggedUser);
}


public class HomePresenter implements HomePresenterInput {

    public static String TAG = HomePresenter.class.getSimpleName();

    //weak var output: HomePresenterOutput!
    public WeakReference<HomeActivityInput> output;


    @Override
    public void validadeUser(String user) {
        HomeViewModel homeViewModel = new HomeViewModel();

        if(!(user.length() > 0)){
            homeViewModel.userValidation = "Insira um endereço de email ou CPF";
            output.get().validadeUser(homeViewModel);
        }
        else if(!isValidEmail(user) && !isCPF(user)){
            homeViewModel.userValidation = "Insira um endereço de email ou CPF";
            output.get().validadeUser(homeViewModel);
        }else{
            output.get().enableLogin();
        }

    }

    @Override
    public void validadePassword(String Password) {
        HomeViewModel homeViewModel = new HomeViewModel();
        String erro = "";
        if(!(Password.length() > 0)){
            erro += "\n Preencha o campo de password";
        }
        if(!checkCapitalLetter(Password)){
            erro += "\n Deve conter uma letra maiúscula";
        }
        if(!checkSpecial(Password)){
            erro += "\n Deve conter um caracter especial";
        }
        if(!checkNumber(Password)){
            erro += "\n Deve conter um caracter alfanumérico";
        }
        if(erro.length() == 0){
            output.get().enableLogin();
        }else{
            homeViewModel.passwordValidation = erro;
            output.get().validadePassword(homeViewModel);
        }



    }

    @Override
    public void login(Boolean success, LoggedUser loggedUser) {
        HomeViewModel homeViewModel = new HomeViewModel();
        if(success){
            //homeViewModel.doLogin();
            output.get().doLogin(loggedUser);
        }else{
            homeViewModel.passwordValidation = "User ou senhas invalidos";
        }
    }

    public boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
    public boolean isCPF(String CPF) {
        // considera-se erro CPF's formados por uma sequencia de numeros iguais
        if (CPF.equals("00000000000") ||
                CPF.equals("11111111111") ||
                CPF.equals("22222222222") || CPF.equals("33333333333") ||
                CPF.equals("44444444444") || CPF.equals("55555555555") ||
                CPF.equals("66666666666") || CPF.equals("77777777777") ||
                CPF.equals("88888888888") || CPF.equals("99999999999") ||
                (CPF.length() != 11))
            return(false);

        char dig10, dig11;
        int sm, i, r, num, peso;

        // "try" - protege o codigo para eventuais erros de conversao de tipo (int)
        try {
            // Calculo do 1o. Digito Verificador
            sm = 0;
            peso = 10;
            for (i=0; i<9; i++) {
                // converte o i-esimo caractere do CPF em um numero:
                // por exemplo, transforma o caractere '0' no inteiro 0
                // (48 eh a posicao de '0' na tabela ASCII)
                num = (int)(CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig10 = '0';
            else dig10 = (char)(r + 48); // converte no respectivo caractere numerico

            // Calculo do 2o. Digito Verificador
            sm = 0;
            peso = 11;
            for(i=0; i<10; i++) {
                num = (int)(CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig11 = '0';
            else dig11 = (char)(r + 48);

            // Verifica se os digitos calculados conferem com os digitos informados.
            if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)))
                return(true);
            else return(false);
        } catch (InputMismatchException erro) {
            return(false);
        }
    }

    public boolean checkCapitalLetter(String text){

        Pattern p = Pattern.compile("[A-Z]");
        Matcher m = p.matcher(text);
        while (m.find()){
            return true;
        }
        return false;

    }

    public boolean checkSpecial(String text){

        Pattern p = Pattern.compile("[^0-9A-Za-z]");
        Matcher m = p.matcher(text);
        while (m.find()){
            return true;
        }
        return false;

    }
    public boolean checkNumber(String text){
        Pattern p = Pattern.compile("[A-Za-z0-9]");
        Matcher m = p.matcher(text);
        while (m.find()){
            return true;
        }
        return false;
    }
}
