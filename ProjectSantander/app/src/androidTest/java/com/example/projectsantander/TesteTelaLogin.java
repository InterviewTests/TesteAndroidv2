package com.example.projectsantander;

import android.support.test.runner.AndroidJUnit4;

import com.example.projectsantander.login.LoginModelImpl;
import com.example.projectsantander.login.LoginPresenterImpl;

import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class TesteTelaLogin extends TestCase {

    @Test
    public void testValidarEmailSucesso(){
        LoginModelImpl model = new LoginModelImpl(new LoginPresenterImpl(null));
        boolean b = (boolean) model.validarUser("teste@teste.com");
        Assert.assertEquals(true,b);
    }

    @Test
    public void testValidarEmailInvalidoSemCaractereAposEspecial(){
        LoginModelImpl model = new LoginModelImpl(new LoginPresenterImpl(null));
        boolean b = model.validarUser("teste@");
        Assert.assertEquals(false,b);
    }
    @Test
    public void testValidarEmailInvalidoApenasComNumeros(){
        LoginModelImpl model = new LoginModelImpl(new LoginPresenterImpl(null));
        boolean b = model.validarUser("111111");
        Assert.assertEquals(false,b);
    }
    @Test
    public void testValidarEmailInvalidoApenasComLetrasMinusculas(){
        LoginModelImpl model = new LoginModelImpl(new LoginPresenterImpl(null));
        boolean b = model.validarUser("aaaaaa");
        Assert.assertEquals(false,b);
    }
    @Test
    public void testValidarEmailInvalidoApenasComLetrasMaisculas(){
        LoginModelImpl model = new LoginModelImpl(new LoginPresenterImpl(null));
        boolean b = model.validarUser("AAAAAA");
        Assert.assertEquals(false,b);
    }
    @Test
    public void testValidarCpfValido(){
        LoginModelImpl model = new LoginModelImpl(new LoginPresenterImpl(null));
        boolean b = model.validarUser("45297975700");
        Assert.assertEquals(true,b);
    }
    @Test
    public void testValidarCpfComNumerosAleatorios(){
        LoginModelImpl model = new LoginModelImpl(new LoginPresenterImpl(null));
        boolean b = model.validarUser("12345678901");
        Assert.assertEquals(false,b);
    }
    @Test
    public void testValidarCpfComMenosDeOnzeCaracteres(){
        LoginModelImpl model = new LoginModelImpl(new LoginPresenterImpl(null));
        boolean b = model.validarUser("4529797570");
        Assert.assertEquals(false,b);
    }
    @Test
    public void testValidarCpfComCaractereEspecial(){
        LoginModelImpl model = new LoginModelImpl(new LoginPresenterImpl(null));
        boolean b = model.validarUser("45297975700@");
        Assert.assertEquals(false,b);
    }
    @Test
    public void testValidarPasswordValido(){
        LoginModelImpl model = new LoginModelImpl(new LoginPresenterImpl(null));
        boolean b = model.validarUser("tEst5@mn");
        Assert.assertEquals(true,b);
    }
    @Test
    public void testValidarPasswordSemCaractereEspecial(){
        LoginModelImpl model = new LoginModelImpl(new LoginPresenterImpl(null));
        boolean b = model.validarUser("tEst5mn");
        Assert.assertEquals(true,b);
    }
    @Test
    public void testValidarPasswordSemCaractereEspecialFaltandoUmCaractereMaisculo(){
        LoginModelImpl model = new LoginModelImpl(new LoginPresenterImpl(null));
        boolean b = model.validarUser("tst5@mn");
        Assert.assertEquals(true,b);
    }


}
