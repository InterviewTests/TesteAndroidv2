package com.accenture.android.app.testeandroid;

import com.accenture.android.app.testeandroid.helpers.FieldHelper;

import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * Created by Denis Magno on 10/07/2020.
 * denis_magno16@hotmail.com
 */
public class FieldHelperUnitTest {
    @Test
    public void formatCpf_isCorrect() {
        assertTrue(FieldHelper.isCPF("667.625.580-46"));
        assertTrue(FieldHelper.isCPF("27174627080"));
        assertFalse(FieldHelper.isCPF(""));
        assertFalse(FieldHelper.isCPF("271746270800"));
        assertFalse(FieldHelper.isCPF("a"));
        assertFalse(FieldHelper.isCPF("27174a2708a"));
    }

    @Test
    public void formatEmail_isCorrect() {
        assertTrue(FieldHelper.isEmail("asd@email.com"));
        assertTrue(FieldHelper.isEmail("asd.fgh@email.com"));
        assertTrue(FieldHelper.isEmail("asd-fgh@email.com"));
        assertTrue(FieldHelper.isEmail("asd_fgh@email.com"));
        assertTrue(FieldHelper.isEmail("asd.123@email.com"));
        assertTrue(FieldHelper.isEmail("asd-123@email.com"));
        assertTrue(FieldHelper.isEmail("asd_123@email.com"));
        assertTrue(FieldHelper.isEmail("asd@email.com.br"));
        assertTrue(FieldHelper.isEmail("asd.fgh@email.com.br"));
        assertTrue(FieldHelper.isEmail("asd-fgh@email.com.br"));
        assertTrue(FieldHelper.isEmail("asd_fgh@email.com.br"));
        assertTrue(FieldHelper.isEmail("asd.123@email.com.br"));
        assertTrue(FieldHelper.isEmail("asd-123@email.com.br"));
        assertTrue(FieldHelper.isEmail("asd_123@email.com.br"));
        assertFalse(FieldHelper.isEmail("asd@email.com.com.br"));
        assertFalse(FieldHelper.isEmail("asd"));
        assertFalse(FieldHelper.isEmail("@"));
        assertFalse(FieldHelper.isEmail("."));
        assertFalse(FieldHelper.isEmail("asd@"));
        assertFalse(FieldHelper.isEmail("@asd"));
        assertFalse(FieldHelper.isEmail("asd."));
        assertFalse(FieldHelper.isEmail(".asd"));
        assertFalse(FieldHelper.isEmail("asd@123.com"));
        assertFalse(FieldHelper.isEmail("asd@email.123"));
    }

    @Test
    public void formatPassword_isCorrect() {
        assertTrue(FieldHelper.isPassword("A!0"));
        assertTrue(FieldHelper.isPassword("!A0"));
        assertTrue(FieldHelper.isPassword("!0A"));
        assertTrue(FieldHelper.isPassword("0!A"));
        assertTrue(FieldHelper.isPassword("c0!Ab"));
        assertTrue(FieldHelper.isPassword("c0d!1Ab"));

        assertFalse(FieldHelper.isPassword("123"));
        assertFalse(FieldHelper.isPassword("ABC"));
        assertFalse(FieldHelper.isPassword("abc"));
        assertFalse(FieldHelper.isPassword("!@#"));
        assertFalse(FieldHelper.isPassword("A1A"));
        assertFalse(FieldHelper.isPassword("1A1"));
        assertFalse(FieldHelper.isPassword("1!1"));
        assertFalse(FieldHelper.isPassword("!1!"));
        assertFalse(FieldHelper.isPassword("!A!"));
        assertFalse(FieldHelper.isPassword("A!A"));
        assertFalse(FieldHelper.isPassword(""));
        assertFalse(FieldHelper.isPassword(" "));
    }
}
