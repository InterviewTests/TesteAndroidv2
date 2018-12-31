package com.home.project.testeandroidv2;


import com.home.project.testeandroidv2.loginScreen.LoginActivity;

import org.junit.Assert;
import org.junit.Test;

public class LoginActivityTest {

    @Test
    public void testeCampoSenha(){
        LoginActivity loginActivity = new LoginActivity();
        //testa uma senha só com números
        String senha = "7878";
        boolean result = loginActivity.formatEdtPassWord(senha);
        /*
        resultado deve ser falso, pois a senha deve ter no mínimo um caracter especial, um em letra maiúscula
        e um alfanumérico
         */
        Assert.assertFalse(result);
    }

    @Test
    public void testeCampoSenha2(){
        LoginActivity loginActivity = new LoginActivity();
        //testa uma senha só com letras
        String senha = "abcdepoop";
        boolean result = loginActivity.formatEdtPassWord(senha);
         /*
        resultado deve ser falso, pois a senha deve ter no mínimo um caracter especial, um em letra maiúscula
        e um alfanumérico
         */
        Assert.assertFalse(result);
    }

    @Test
    public void testeCampoSenha3(){
        LoginActivity loginActivity = new LoginActivity();
        //testa uma senha com caracter especial e um alfanumérico
        String senha = "#8aad";
        boolean result = loginActivity.formatEdtPassWord(senha);
         /*
        resultado deve ser falso, pois a senha deve ter no mínimo um caracter especial, um em letra maiúscula
        e um alfanumérico
         */
        Assert.assertFalse(result);
    }

    @Test
    public void testeCampoSenha4(){
        LoginActivity loginActivity = new LoginActivity();
        //testa uma senha que atende as regras
        String senha = "#E789s";
        boolean result = loginActivity.formatEdtPassWord(senha);
         /*
        resultado deve ser true, pois a senha deve ter no mínimo um caracter especial, um em letra maiúscula
        e um alfanumérico, conforme valor da String senha
         */
        Assert.assertTrue(result);
    }

    @Test
    public void testeCampoUsuario(){
        LoginActivity loginActivity = new LoginActivity();
        //testa um usuario que não possui @ ou .com, que não é um email
        String user = "#android.teste";
        boolean result = loginActivity.formatEdtUser(user);
         /*
        resultado deve ser false, pois na string deve conter @ ou .com para identificar que é um email
         */
        Assert.assertFalse(result);
    }

    @Test
    public void testeCampoUsuario2(){
        LoginActivity loginActivity = new LoginActivity();
        //testa uma entrada de cpf com traços e pontos
        String user = "111.222.333-56";
        boolean result = loginActivity.formatEdtUser(user);
         /*
        resultado deve ser false, pois cpf deve conter somente os digitos
         */
        Assert.assertFalse(result);
    }

    @Test
    public void testeCampoUsuario3(){
        LoginActivity loginActivity = new LoginActivity();
        //testa uma entrada de cpf com mais de onze digitos
        String user = "11122233344456";
        boolean result = loginActivity.formatEdtUser(user);
         /*
        resultado deve ser false, pois cpf deve conter somente onze digitos
         */
        Assert.assertFalse(result);
    }

    @Test
    public void testeCampoUsuario4(){
        LoginActivity loginActivity = new LoginActivity();
        //testa uma entrada de cpf com onze digitos
        String user = "11122233344";
        boolean result = loginActivity.formatEdtUser(user);
         /*
        resultado deve ser true, pois cpf deve conter onze digitos e nenhum caracter especial
         */
        Assert.assertTrue(result);
    }

}
