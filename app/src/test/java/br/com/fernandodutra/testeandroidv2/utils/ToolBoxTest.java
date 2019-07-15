package br.com.fernandodutra.testeandroidv2.utils;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Fernando Dutra
 * User: Fernando Dutra
 * Date: 30/06/2019
 * Time: 12:50
 * TesteAndroidv2_CleanCode
 */
public class ToolBoxTest {

    @Test
    public void removeAccents_IsValid() {
        //Given
        String cpf = ToolBox.removeAccents("367.464.058-93");

        //When

        //Then
        Assert.assertEquals("36746405893", cpf);
    }

    @Test
    public void validEmail_IsValid() {
        //Given
        boolean email = ToolBox.validEmail("fernando@gmail.com");

        //When

        //Then
        Assert.assertTrue(email);
    }

    @Test
    public void validEmail_IsNotValid() {
        //Given
        boolean email = ToolBox.validEmail("fernando_gmail.com");

        //When

        //Then
        Assert.assertFalse(email);
    }

    @Test
    public void validCPF_IsValid() {
        //Given
        boolean cpf = ToolBox.validCPF("367.464.058-93");

        //When

        //Then
        Assert.assertTrue(cpf);
    }

    @Test
    public void validCPF_IsNotValid() {
        //Given
        boolean cpf = ToolBox.validCPF("367.464.059-94");

        //When

        //Then
        Assert.assertFalse(cpf);
    }

    @Test
    public void validPassword_IsValid() {
        //Given
        boolean password = ToolBox.validPassword("@Fer1234");

        //When

        //Then
        Assert.assertTrue(password);
    }

    @Test
    public void validPassword_IsNotValid() {
        //Given
        boolean password = ToolBox.validPassword("367.464.059-94");

        //When

        //Then
        Assert.assertFalse(password);
    }

}