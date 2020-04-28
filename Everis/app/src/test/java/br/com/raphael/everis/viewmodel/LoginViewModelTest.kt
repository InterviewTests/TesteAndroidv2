package br.com.raphael.everis.viewmodel

import br.com.raphael.everis.BaseTest
import br.com.raphael.everis.R
import br.com.raphael.everis.model.FieldError
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class LoginViewModelTest : BaseTest() {

    lateinit var loginViewModel: LoginViewModel

    @Before
    override fun setUp() {
        super.setUp()

        loginViewModel = LoginViewModel(app)
    }

    @Test
    fun `Testar login com user (cpf) e password corretos`() = runBlocking {

        loginViewModel.setUser("04408100196")
        loginViewModel.setPassword("R@1")
        loginViewModel.postLogin()

        loginViewModel.success.observeForever {
            // conteudo
            assertEquals(1, it.userId)
            assertEquals("Jose da Silva Teste", it.name)
            assertEquals("2050", it.bankAccount)
            assertEquals("012314564", it.agency)
            assertEquals(3.3445, it.balance, 0.0)
        }
    }

    @Test
    fun `Testar login com user (email) e password corretos`() = runBlocking {

        loginViewModel.setUser("raphael_amorim@outlook.com")
        loginViewModel.setPassword("R@a")
        loginViewModel.postLogin()

        loginViewModel.success.observeForever {
            // conteudo
            assertEquals(1, it.userId)
            assertEquals("Jose da Silva Teste", it.name)
            assertEquals("2050", it.bankAccount)
            assertEquals("012314564", it.agency)
            assertEquals(3.3445, it.balance, 0.0)
        }
    }

    @Test
    fun `Testar login com user e password vazios`() = runBlocking {

        loginViewModel.setUser("")
        loginViewModel.setPassword("")
        val result = loginViewModel.postLogin()

        assertTrue(result.contains(FieldError(R.id.til_user, R.string.msg_user_obrigatorio)))
        assertTrue(result.contains(FieldError(R.id.til_password, R.string.msg_password_obrigatorio)))
    }

    @Test
    fun `Testar login com user (cpf) invalido`() = runBlocking {

        loginViewModel.setUser("11122233394")
        val result = loginViewModel.postLogin()

        assertTrue(result.contains(FieldError(R.id.til_user, R.string.msg_user_cpf_invalido)))
    }

    @Test
    fun `Testar login com user (email) invalido`() = runBlocking {

        loginViewModel.setUser("teste")
        val result = loginViewModel.postLogin()

        assertTrue(result.contains(FieldError(R.id.til_user, R.string.msg_user_email_invalido)))
    }

    @Test
    fun `Testar login com password nao contendo caracter especial`() = runBlocking {

        loginViewModel.setPassword("Ra1")
        val result = loginViewModel.postLogin()

        assertTrue(result.contains(FieldError(R.id.til_password, R.string.msg_password_invalido)))
    }

    @Test
    fun `Testar login com password nao contendo caracter maiusculo`() = runBlocking {

        loginViewModel.setPassword("@a1")
        val result = loginViewModel.postLogin()

        assertTrue(result.contains(FieldError(R.id.til_password, R.string.msg_password_invalido)))
    }

    @Test
    fun `Testar login com password nao contendo caracter minusculo ou numero`() = runBlocking {

        loginViewModel.setPassword("R@@R")
        val result = loginViewModel.postLogin()

        assertTrue(result.contains(FieldError(R.id.til_password, R.string.msg_password_invalido)))
    }
}
