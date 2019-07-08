package ssilvalucas.testeandroidv2.screen.login;

import android.content.Context;

import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ssilvalucas.testeandroidv2.data.model.ErrorMessage;
import ssilvalucas.testeandroidv2.data.model.LoginRequest;
import ssilvalucas.testeandroidv2.data.model.LoginResponse;
import ssilvalucas.testeandroidv2.data.retrofit.Retrofit;
import ssilvalucas.testeandroidv2.data.api.LoginService;
import ssilvalucas.testeandroidv2.data.storage.UserDataStorage;


interface LoginInteractorInput {
    void onLogin(String username, String password);
}

public class LoginInteractor implements LoginInteractorInput{

    public static String TAG = LoginInteractor.class.getSimpleName();
    public Context context;

    public LoginPresenterInput output;

    @Override
    public void onLogin(String username, String password) {
        if(username.isEmpty()){
            output.throwError(ErrorMessage.MESSAGE_EMPTY_USERNAME);
        } else if(password.isEmpty()){
            output.throwError(ErrorMessage.MESSAGE_EMPTY_PASSWORD);
        } else if(!isValidUsername(username)){
            output.throwError(ErrorMessage.MESSAGE_INVALID_USERNAME);
        } else if(!isValidPassword(password)){
            output.throwError(ErrorMessage.MESSAGE_INVALID_PASSWORD);
        } else{
            LoginRequest request = new LoginRequest(username, password);
            doLoginRequest(request);
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

    private void doLoginRequest(final LoginRequest request){
        LoginService loginService = Retrofit.getInstance().create(LoginService.class);

        loginService.onLogin(request.getUsername(), request.getPassword()).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if(response.isSuccessful()){
                    output.onSuccessfulLogin(response.body());
                    UserDataStorage.writeUsername(request.getUsername(), context);
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                output.throwError(t.getMessage());
            }
        });
    }
}
