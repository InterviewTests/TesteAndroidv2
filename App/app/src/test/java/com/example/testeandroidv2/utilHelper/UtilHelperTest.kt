package com.example.testeandroidv2.utilHelper

import org.junit.Test

import org.junit.Assert.*

class UtilHelperTest {

    @Test
    fun `cpf invalid`() {

        // Arrange
        val input = "711.708.140-96"
        val utilHelper = UtilHelper()

        // Act
        val output = utilHelper.validateCPF(input)

        // Assert
        assertFalse(output)
    }

    @Test
    fun `cpf invalid sequence`() {

        // Arrange
        val input = "000.000.000-00"
        val utilHelper = UtilHelper()

        // Act
        val output = utilHelper.validateCPF(input)

        // Assert
        assertFalse(output)
    }

    @Test
    fun `cpf with letters`() {

        // Arrange
        val input = "000.000.000-AA"
        val utilHelper = UtilHelper()

        // Act
        val output = utilHelper.validateCPF(input)

        // Assert
        assertFalse(output)
    }

    @Test
    fun `cpf empty`() {

        // Arrange
        val input = ""
        val utilHelper = UtilHelper()

        // Act
        val output = utilHelper.validateCPF(input)

        // Assert
        assertFalse(output)
    }

    @Test
    fun `cpf valid`() {

        // Arrange
        val input = "711.708.140-69"
        val utilHelper = UtilHelper()

        // Act
        val output = utilHelper.validateCPF(input)

        // Assert
        assertTrue(output)
    }

    @Test
    fun `date format invalid`() {

        // Arrange
        val input = "teste"
        val utilHelper = UtilHelper()

        // Act
        val output = utilHelper.dateFormat(input)

        // Assert
        assertEquals("error", output)
    }

    @Test
    fun `date format empty`() {

        // Arrange
        val input = ""
        val utilHelper = UtilHelper()

        // Act
        val output = utilHelper.dateFormat(input)

        // Assert
        assertEquals("error", output)
    }

    @Test
    fun `date format valid`() {

        // Arrange
        val input = "2018-08-15"
        val utilHelper = UtilHelper()

        // Act
        val output = utilHelper.dateFormat(input)

        // Assert
        assertEquals("15/08/2018", output)
    }

    @Test
    fun `password valid`() {

        // Arrange
        val input = "QA-1#"
        val utilHelper = UtilHelper()

        // Act
        val output = utilHelper.checkPassword(input)

        // Assert
        assertTrue(output)
    }

    @Test
    fun `password invalid`() {

        // Arrange
        val input = "aaa"
        val utilHelper = UtilHelper()

        // Act
        val output = utilHelper.checkPassword(input)

        // Assert
        assertFalse(output)
    }

    @Test
    fun `password empty`() {

        // Arrange
        val input = ""
        val utilHelper = UtilHelper()

        // Act
        val output = utilHelper.checkPassword(input)

        // Assert
        assertFalse(output)
    }

    @Test
    fun `account valid format`() {

        // Arrange
        val input = "012314564"
        val utilHelper = UtilHelper()

        // Act
        val output = utilHelper.formatAccount(input)

        // Assert
        assertEquals("01.231456-4", output)
    }

    @Test
    fun `account invalid format`() {

        // Arrange
        val input = "01231456"
        val utilHelper = UtilHelper()

        // Act
        val output = utilHelper.formatAccount(input)

        // Assert
        assertEquals("error", output)
    }

    @Test
    fun `currency value valid`(){

        // Arrange
        val input = 90.0
        val utilHelper = UtilHelper()

        // Act
        val output = utilHelper.currency(input)

        // Assert
        assertEquals("R$ 90,00", output)
    }

    @Test
    fun `user account with valid email`(){

        // Arrange
        val input = "usuario@gmail.com"
        val utilHelper = UtilHelper()

        // Act
        val output = utilHelper.checkUser(input)

        // Assert
        assertTrue(output)
    }

    @Test
    fun `user account with valid cpf`(){

        // Arrange
        val input = "816.900.440-39"
        val utilHelper = UtilHelper()

        // Act
        val output = utilHelper.checkUser(input)

        // Assert
        assertTrue(output)
    }

    @Test
    fun `user account with invalid email`(){

        // Arrange
        val input = "usuario@gmail."
        val utilHelper = UtilHelper()

        // Act
        val output = utilHelper.checkUser(input)

        // Assert
        assertFalse(output)
    }

    @Test
    fun `user account with invalid cpf`(){

        // Arrange
        val input = "816.900.440-29"
        val utilHelper = UtilHelper()

        // Act
        val output = utilHelper.checkUser(input)

        // Assert
        assertFalse(output)
    }
}