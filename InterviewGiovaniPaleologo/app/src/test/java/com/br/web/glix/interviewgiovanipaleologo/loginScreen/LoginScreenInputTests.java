package com.br.web.glix.interviewgiovanipaleologo.loginScreen;

import com.br.web.glix.interviewgiovanipaleologo.utils.Utils;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;


@RunWith(RobolectricTestRunner.class)
public class LoginScreenInputTests {
    @Test
    public void LoginScreenActivity_NOT_Null(){
        //Given
        LoginScreenActivity activity = Robolectric.setupActivity(LoginScreenActivity.class);
        // Then
        Assert.assertNotNull(activity);
    }

    @Test
    public void LoginScreenActivivity_Empty_Username(){
        //Given
        String ExpectedText = "false";
        String ActualText = String.valueOf(Utils.validarEmail(""));
        Assert.assertEquals("Campo usuário vazio", ExpectedText, ActualText);
    }

    @Test
    public void LoginScreenActivivity_Empty_Password(){
        //Given
        String ExpectedText = "false";
        String ActualText = String.valueOf(Utils.validarSenha(""));
        Assert.assertEquals("Campo senha vazio", ExpectedText, ActualText);
    }

    @Test
    public void LoginScreenActivivity_Invalid_Username(){
        //Given
        String ExpectedText = "false";
        String ActualText = String.valueOf(Utils.validarEmail("Nome de Usuário"));
        Assert.assertEquals("Usuário inválido", ExpectedText, ActualText);
    }

    @Test
    public void LoginScreenActivivity_Invalid_Password(){
        //Given
        String ExpectedText = "false";
        String ActualText = String.valueOf(Utils.validarSenha("abc"));
        Assert.assertEquals("Senha Inválida", ExpectedText, ActualText);
    }

    @Test
    public void LoginScreenActivivity_Invalid_Username_With_Number(){
        //Given
        String ExpectedText = "false";
        String ActualText = String.valueOf(Utils.validarEmail("123456"));
        Assert.assertEquals("Usuário inválido", ExpectedText, ActualText);
    }

    @Test
    public void LoginScreenActivivity_Invalid_Password_With_Number(){
        //Given
        String ExpectedText = "false";
        String ActualText = String.valueOf(Utils.validarSenha("abc45"));
        Assert.assertEquals("Senha Inválida", ExpectedText, ActualText);
    }

    @Test
    public void LoginScreenActivivity_Valid_Username(){
        //Given
        String ExpectedText = "true";
        String ActualText = String.valueOf(Utils.validarEmail("usuario@provedor.com"));
        Assert.assertEquals("Usuário inválido", ExpectedText, ActualText);
    }

    @Test
    public void LoginScreenActivivity_Valid_Password(){
        //Given
        String ExpectedText = "true";
        String ActualText = String.valueOf(Utils.validarSenha("aBc#45"));
        Assert.assertEquals("Senha Inválida", ExpectedText, ActualText);
    }

    @Test
    public void LoginScreenActivivity_Invalid_Username_CPF(){
        //Given
        String ExpectedText = "false";
        String ActualText = String.valueOf(Utils.validarCPF("73645876462"));
        Assert.assertEquals("Usuário inválido", ExpectedText, ActualText);
    }

    @Test
    public void LoginScreenActivivity_Valid_Username_CPF(){
        //Given
        String ExpectedText = "true";
        String ActualText = String.valueOf(Utils.validarCPF("09982941879"));
        Assert.assertEquals("Usuário inválido", ExpectedText, ActualText);
    }
}
