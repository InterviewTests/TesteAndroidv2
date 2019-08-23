package br.com.giovanni.testebank;

import org.junit.Test;

import br.com.giovanni.testebank.Model.LoginControlValidation;

import static junit.framework.TestCase.assertTrue;

public class LoginControlValidationTest {
    public String getUserCPF = "365.769.258-40";                //CPF utilizado somente para testes, não é real.
    public String getUSerEmail = "test_email@mail.com";
    public String getPassword = "Abcd@1";

    LoginControlValidation loginControlValidationCPF = new LoginControlValidation(getUserCPF,getPassword);
    LoginControlValidation loginControlValidationEmail = new LoginControlValidation(getUSerEmail, getPassword);

    @Test
    public void shouldValidateIfLoginIsValid(){
        boolean isValid = loginControlValidationCPF.loginControlValidation();
        assertTrue(isValid);
    }

    @Test
    public void shouldValidateIfLoginIsValidEmail(){
        boolean isValid = loginControlValidationEmail.loginControlValidation();
        assertTrue(isValid);
    }
}
