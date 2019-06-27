package com.example.projectsantander.login;

import com.example.projectsantander.services.LoginResponse;
import com.example.projectsantander.services.RetrofitApi;

import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginModelImpl implements LoginContract.LoginModel {

    private LoginContract.LoginPresenter presenter;

    public LoginModelImpl(LoginContract.LoginPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void realizaLogin(String usuario, String senha) {
        if (usuario == null || usuario.trim().equals("")) {
            presenter.usuarioNaoInformado();
            return;
        }
        if (senha == null || senha.trim().equals("")) {
            presenter.senhaNaoInformada();
            return;
        }
        if (!validarUser(usuario)) {
            presenter.userInvalido();


            return;
     }if(!validarPassword(senha)){
         presenter.senhaInvalida();
           return;
        }

        presenter.loadingRequisicao();
        new RetrofitApi().getService().login(usuario, senha).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse resp = response.body();
                presenter.fechaLoading();
                if (resp.getError() == null || resp.getError().getCode() == 0) {
                    presenter.loginCompleto(resp.getUserAccount());
                } else {
                    presenter.loginIncorreto(resp.getError().getMessage());
                }

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                presenter.fechaLoading();
                presenter.erroServidor();
            }
        });
    }
    public boolean validarUser(String usuario) {
        Pattern PATTERN_GENERIC = Pattern.compile("[0-9]{3}\\.?[0-9]{3}\\.?[0-9]{3}\\-?[0-9]{2}");
        Pattern PATTERN_NUMBERS = Pattern.compile("(?=^((?!((([0]{11})|([1]{11})|([2]{11})|([3]{11})" +
                "|([4]{11})|([5]{11})|([6]{11})|([7]{11})|([8]{11})|([9]{11})))).)*$)([0-9]{11})");
        String regex = "^(.+)@(.+)$|[0-9]";

        if (usuario == regex || PATTERN_GENERIC.matcher(usuario).matches()) {
            usuario = usuario.replaceAll("-|\\.", "");
            if (usuario != null || PATTERN_NUMBERS.matcher(usuario).matches()) {
                int[] numbers = new int[11];
                for (int i = 0; i < 11; i++)
                    numbers[i] = Character.getNumericValue(usuario.charAt(i));
                int i;
                int sum = 0;
                int factor = 100;
                for (i = 0; i < 9; i++) {
                    sum += numbers[i] * factor;
                    factor -= 10;
                }
                int leftover = sum % 11;
                leftover = leftover == 10 ? 0 : leftover;
                if (leftover == numbers[9]) {
                    sum = 0;
                    factor = 110;
                    for (i = 0; i < 10; i++) {
                        sum += numbers[i] * factor;
                        factor -= 10;
                    }
                    leftover = sum % 11;
                    leftover = leftover == 10 ? 0 : leftover;
                    return leftover == numbers[10];
                }
            }
        }
        return usuario.matches(regex);
    }
    private boolean validarPassword(String senha){
        String regex = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
        return senha.matches(regex); }

}

//    public boolean validarUser(String usuario) {
//        String regex = "^.+@.+\\..+$|[0-9]{11}";
////        String regex = "/^[a-zA-Z0-9.!#$%&'+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}" +
////                "[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)$/";
//        return usuario.matches(regex);
//    }
//
//
//    private boolean validarPassword(String senha) {
//     //String regex = "^(.+)(.+)$|[0-9]";
////       String regex = "^(?=(.*?[A-Z]){1})(?=(?:.*?[0-9]){1})(?=(?:.*?[!@#$%*()_+^&}{:;?.]){1})(?!.*\\s)[0-9a-zA-Z!@#$%;*(){}_+^&]*$";
////        return senha.matches(regex);
//        return true;
//    }
