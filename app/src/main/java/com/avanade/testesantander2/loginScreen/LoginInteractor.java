package com.avanade.testesantander2.loginScreen;


import android.content.Context;
import android.util.Log;

import com.avanade.testesantander2.APIRetrofitService;
import com.avanade.testesantander2.ApiPostLoginResponse;
import com.avanade.testesantander2.util.ApiClient;
import com.avanade.testesantander2.util.CpfUtil;
import com.avanade.testesantander2.util.MailUtil;
import com.avanade.testesantander2.util.SenhaUtil;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

interface LoginInteractorInput {

    void checkUsuarioSalvo(Context context); // checa se já tem Login e Password gravada -> vai chamar setUser ao Presenter

    void postLogin(Context context, LoginRequest loginRequest); // Consumo da API -> vai chamar Response ao Presenter
}

public class LoginInteractor implements LoginInteractorInput {
    private static String TAG = LoginInteractor.class.getSimpleName();

    public static final String USUARIO = "user";
    public static final String SENHA = "pass";

    ArrayList<String> erros;
    LoginPresenterInput output;

    /**
     * Método responsável por realizar a gravação segura de informação in Device
     * @param usuario   String conteúdo usuario
     * @param senha     String conteúdo senha
     * @param context   Context contexto do app, para usar Internal Access
     */
    private void gravarUsuario(String usuario, String senha, Context context) {
        Hawk.init(context).build();
        Log.i(TAG, "HAWK GRAVANDO ---------------------------------- CONTADOR = " + Hawk.count());

        Hawk.deleteAll();
        Log.i(TAG, "HAWK APAGA TUDO ---------------------------------- CONTADOR = " + Hawk.count());

        Hawk.put(USUARIO, usuario);
        Hawk.put(SENHA, senha);
        Log.i(TAG, "HAWK GRAVOU ---------------------------------- CONTADOR = " + Hawk.count());
    }


    @Override
    public void checkUsuarioSalvo(Context context) {
        Hawk.init(context).build();
        if (Hawk.contains(USUARIO) && Hawk.contains(SENHA) /* TODO Biometria do Usuário */)
            output.setUsuario(Hawk.get(USUARIO), Hawk.get(SENHA));
        else
            output.setUsuario("", "");
    }

    @Override
    public void postLogin(Context context, LoginRequest loginRequest) {

        erros = new ArrayList<>();
        erros = errosValidacaoLogin(erros, loginRequest);

        // Validação Login e senha sem erros -> OK executa POST na API
        if (erros.isEmpty()) {

            APIRetrofitService apiRetrofit = ApiClient.getClient(ApiClient.BASE_URL).create(APIRetrofitService.class);
            Call<ApiPostLoginResponse> call = apiRetrofit.postLogin(loginRequest.user, loginRequest.password);

            call.enqueue(
                    new Callback<ApiPostLoginResponse>() {

                        @Override
                        public void onResponse(Call<ApiPostLoginResponse> call, Response<ApiPostLoginResponse> response) {
                            Log.d(TAG, "-------------------- RESPONSE: " + response.body());

                            if (response.isSuccessful()) {
                                Log.i(TAG, "-------------------- RESPONSE SUCESSFUL");
                                LoginViewModel viewModel = new LoginViewModel();
                                viewModel.userAccount = response.body().getUserAccount();

                                // User ID default é ZERO -> logo não retornou usuário corretamente
                                if (response.body().getUserAccount().getUserId() == 0) {
                                    erros.add("ERRO DE RESPONSE DA API --- { ID Usuario == 0 } ---  USUÁRIO NÃO ENCONTRADO -> SOLICITAR TELA DE CADASTRO");

                                } else {
                                    // User ID encontrado, logo vamos gravar último logon 100%
                                    gravarUsuario(loginRequest.user, loginRequest.password, context);

                                    // UserAccount completa
                                    output.setReponseOK(viewModel.userAccount);
                                }
                            } else {
                                Log.d(TAG, "Erro: " + response.errorBody().toString());
                                ArrayList<String> erros = new ArrayList<>();
                                erros.add(response.body().getError().getMessage());

                                output.setErros(erros);
                            }
                        }

                        @Override
                        public void onFailure(Call<ApiPostLoginResponse> call, Throwable t) {
                            // Log error here since homeRequest failed
                            Log.e(TAG, "RESPONSE FAILURE - Erro: " + t.toString());

                            final String erro = "Erro ao realizar login.\nTente novamente";
                            ArrayList<String> erros = new ArrayList<>();
                            erros.add(erro);
                        }
                    }
            );
        }
        output.setErros(erros);
    }


    /**
     * Método que efetua a validação das String Usuario e Senha
     * e caso encontrar erros, ele acrescenta ao ArrayList que lhe foi dado
     *
     * @param listaDeErros  Lista de Erros da Classe
     * @param loginRequest  Model contendo 2 Strings (user + password)
     * @return  Lista de Erros modificada, caso houver algum erro
     */
    public ArrayList<String> errosValidacaoLogin(ArrayList<String> listaDeErros, LoginRequest loginRequest) {

        ArrayList<String> errosDeValidacao = listaDeErros;

        boolean emailInvalido = !MailUtil.isValid(loginRequest.user);
        boolean cpfInvalido = !CpfUtil.isValid(loginRequest.user);
        boolean senhaInvalida = !SenhaUtil.isValid(loginRequest.password);

        // valida usuario
        if (emailInvalido && cpfInvalido)
            errosDeValidacao.add("Usuário inválido.\nDigite um e-mail ou CPF válido");

        // valida senha
        if (senhaInvalida)
            errosDeValidacao.add("Senha inválida.\nSenha deve conter pelo menos 1 letra, 1 número e 1 caractere especial");

        for (String erro : errosDeValidacao)
            Log.e(TAG, erro);

        return errosDeValidacao;
    }


}
