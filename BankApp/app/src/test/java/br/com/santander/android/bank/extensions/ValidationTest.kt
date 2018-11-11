package br.com.santander.android.bank.extensions

import br.com.santander.android.bank.core.extensions.isCpf
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ValidationTest {

    @Test
    fun `Assert that value is valid cpf`() {
        val value = "54834831620"
        assertTrue(value.isCpf())
    }

    @Test
    fun `Assert that value is invalid cpf`() {
        val value = "50834831820"
        assertFalse(value.isCpf())
    }

    @Test
    fun `Assert that value less than 11 is invalid cpf`() {
        val value = "54834"
        assertFalse(value.isCpf())
    }
}