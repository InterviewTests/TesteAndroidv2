package br.com.rcp.bank

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import br.com.rcp.bank.repositories.LoginRepository
import br.com.rcp.bank.repositories.base.Repository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
	@Test
	fun useAppContext() {
		// Context of the app under test.
		val appContext = InstrumentationRegistry.getInstrumentation().targetContext
		assertEquals("br.com.rcp.bank", appContext.packageName)
	}

	@Test
	fun request_login() {
		val	repository	= LoginRepository()
		assertNotEquals(repository, null)
		val	success		= runBlocking { repository.login("user_test", "Test@1") }
		assertEquals(success is Repository.Result.Success, true)
	}
}
