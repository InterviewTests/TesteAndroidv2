package ssilvalucas.testeandroidv2.screen.login;

import java.util.regex.Pattern;


interface LoginInteractorInput {
    void onLogin(String username, String password);
}

public class LoginInteractor implements LoginInteractorInput{

    public static String TAG = LoginInteractor.class.getSimpleName();

    public LoginPresenterInput output;

    @Override
    public void onLogin(String username, String password) {
        if(username.isEmpty()){
            output.isEmptyUsername();
            return;
        } else if(password.isEmpty()){
            output.isEmptyPassword();
            return;
        } else if(!isValidUsername(username)){
            output.invalidUsername();
            return;
        } else if(!isValidPassword(password)){
            output.invalidPassword();
            return;
        }

    }

    private boolean isValidUsername(String username){
        /* Regex de validação do email */
        String emailRegex = "^[a-z](?:[0-9a-z\\.\\-_])*?@[0-9a-z][0-9a-z-_]*(?:\\.[0-9a-z-_]+)*(?:\\.[0-9a-z]+)+$";
        /* Regex de validação do CPF */
        String cpfRegex   = "^(?:[0-9]{11}|[0-9]{3}(?:\\.[0-9]{3}){2}\\-[0-9]{2})$";

        if(Pattern.matches(emailRegex, username)){
            return true;
        } else if(Pattern.matches(cpfRegex, username)){
            /*
             * Caso torne-se necessário fazer o calculo do cpf, deve-se implementar a função comentada a seguir.
             * Nesta situação, não há necessidade, visto que a entrada será comparada com um cpf já cadastrado.
            */
            //ValidationUtils.isValidCpf(username);
            return true;
        }
        return false;
    }

    private boolean isValidPassword(String password){
        /* Deve ter pelo menos uma letra uma letra maiuscula, um caracter especial e um caracter alfanumérico.*/
        String regex = "(?=.*[A-Z])(?=.*[+×÷=/_€£¥₩!@#$%^&*:;,?\\|])(?=.*[0-9a-z])[0-9a-zA-Z+×÷=/_€£¥₩!@#$%^&*:;,?\\|]+";
        return Pattern.matches(regex, password);
    }
}
