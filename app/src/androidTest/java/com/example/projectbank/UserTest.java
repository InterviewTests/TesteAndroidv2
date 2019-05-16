package com.example.projectbank;

import android.util.Log;

import com.example.projectbank.Controller.UserController;
import com.example.projectbank.Model.User;

import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Test;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.projectbank.Controller.UserController.validaSenha;
import static com.example.projectbank.Controller.UserController.validateEmailFormat;

public class UserTest {

    @Test
    public void testValidarCpfSemPontuacaoTamanhoIgualOnze()
    {
        UserController userController = new UserController("46408310800", "Teste123");
        boolean result = userController.validaCPF(userController.getCpfOrEmail());
        Assert.assertTrue(result);
    }

    @Test
    public void testValidarCpfSemPontuacaoTamanhoMenorOnze()
    {
        UserController userController = new UserController("464083108", "Teste123");
        boolean result = userController.validaCPF(userController.getCpfOrEmail());
        Assert.assertFalse(result);
    }

    @Test
    public void testValidarCpfSemPontuacaoTamanhoMaiorOnze()
    {
        UserController userController = new UserController("4640831080000", "Teste123");
        boolean result = userController.validaCPF(userController.getCpfOrEmail());
        Assert.assertFalse(result);
    }

    @Test
    public void testValidarEmailFormatoUm()
    {
        UserController userController = new UserController("teste@teste.com", "Teste123");
        boolean result = validateEmailFormat(userController.getCpfOrEmail());
        Assert.assertTrue(result);
    }

    @Test
    public void testValidarEmailFormatoDois()
    {
        UserController userController = new UserController("teste@teste.com.br", "Teste123");
        boolean result = validateEmailFormat(userController.getCpfOrEmail());
        Assert.assertTrue(result);
    }

    @Test
    public void testValidarEmailFormatoInvalidoUm()
    {
        UserController userController = new UserController("teste@teste.", "Teste123");
        boolean result = validateEmailFormat(userController.getCpfOrEmail());
        Assert.assertFalse(result);
    }

    @Test
    public void testValidarEmailFormatoInvalidoDois()
    {
        UserController userController = new UserController("testeteste.com", "Teste123");
        boolean result = validateEmailFormat(userController.getCpfOrEmail());
        Assert.assertFalse(result);
    }

    @Test
    public void testValidarEmailFormatoInvalidoTres()
    {
        UserController userController = new UserController("teste.teste.com", "Teste123");
        boolean result = validateEmailFormat(userController.getCpfOrEmail());
        Assert.assertFalse(result);
    }

    @Test
    public void testValidarSenhaCorreta()
    {
        UserController userController = new UserController("teste@teste.com.br", "Teste@123");
        boolean result = validaSenha(userController.getPassword());
        Assert.assertTrue(result);
    }

    @Test
    public void testValidarSenhaInvalidaSemCaracterEspecial()
    {
        UserController userController = new UserController("teste@teste.com.br", "Teste123");
        boolean result = validaSenha(userController.getPassword());
        Assert.assertFalse(result);
    }

    @Test
    public void testValidarSenhaInvalidaSemLetraMaiuscula()
    {
        UserController userController = new UserController("teste@teste.com.br", "teste@123");
        boolean result = validaSenha(userController.getPassword());
        Assert.assertFalse(result);
    }

    @Test
    public void testValidarSenhaInvalidaSemLetraMinuscula()
    {
        UserController userController = new UserController("teste@teste.com.br", "TESTE@123");
        boolean result = validaSenha(userController.getPassword());
        Assert.assertFalse(result);
    }

    @Test
    public void testValidarSenhaInvalidaSemNumero()
    {
        UserController userController = new UserController("teste@teste.com.br", "Tetse@fwqf");
        boolean result = validaSenha(userController.getPassword());
        Assert.assertFalse(result);
    }

    @Test
    public void testeRetrofitSucess()
    {
        new UserController("teste@teste.com.br", "Teste@123")
                .getUserService()
                .buscarUser("teste@teste.com.br", "Teste@123")
                .enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        User user = response.body();
                        int code = user.getError().getCode();
                        String message = user.getError().getMessage();
                        if(code == 0 && message == "")
                        {
                            boolean result = true;
                            Assert.assertTrue(result);
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Log.e("UserService   ", "Erro ao buscar o user:" + t.getMessage());
                    }
                });
    }

    @Test
    public void testeRetrofitError()
    {
        new UserController("teste@teste.com.br", "Teste@123")
                .getUserService()
                .buscarUser("teste@teste.com.br", "Teste@123")
                .enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        User user = response.body();
                        int code = 53;
                        String message = "Usuário ou senha inválida";
                        if(code != 0 || message != "")
                        {
                            boolean result = false;
                            Assert.assertFalse(result);
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Log.e("UserService   ", "Erro ao buscar o user:" + t.getMessage());
                    }
                });
    }
}