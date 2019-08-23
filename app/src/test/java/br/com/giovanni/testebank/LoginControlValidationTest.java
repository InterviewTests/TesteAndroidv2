package br.com.giovanni.testebank;

import org.junit.Test;

import br.com.giovanni.testebank.Model.LoginControlValidation;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class LoginControlValidationTest {

    public String getUserCPF = "365.769.258-40";                  //CPF utilizado somente para testes, não é real.
    public String getUSerEmail = "test_email@mail.com";
    public String getPassword = "Abcd@1";

    public String getUserCPFInvalid = "365.769.258-42";           //Ultimo digito invalido
    public String getUSerEmailInvalid = "test_emailmail.com";     //Email sem @
    public String getPasswordInvalid = "Abcde1";                  //Senha sem caractere especial

    LoginControlValidation loginControlValidationCPF = new LoginControlValidation(getUserCPF,getPassword);
    LoginControlValidation loginControlValidationEmail = new LoginControlValidation(getUSerEmail, getPassword);

    LoginControlValidation loginControlValidationCPFInvalid = new LoginControlValidation(getUserCPFInvalid,getPasswordInvalid);
    LoginControlValidation loginControlValidationEmailInvalid = new LoginControlValidation(getUSerEmailInvalid, getPasswordInvalid);

    @Test
    public void shouldValidateIfLoginIsValidCPF(){
        boolean isValid = loginControlValidationCPF.loginControlValidation();
        assertTrue(isValid);
    }

    @Test
    public void shouldValidateIfLoginIsValidEmail(){
        boolean isValid = loginControlValidationEmail.loginControlValidation();
        assertTrue(isValid);
    }

    @Test
    public void shouldValidateIfLoginIsInvalidCPF(){
        boolean isValid = loginControlValidationCPFInvalid.loginControlValidation();
        assertFalse(isValid);
    }

    @Test
    public void shouldValidateIfLoginIsInvalidEmail(){
        boolean isValid = loginControlValidationEmailInvalid.loginControlValidation();
        assertFalse(isValid);
    }

}
