package br.com.rcp.bank

import org.junit.Assert
import org.junit.Test

class LoginUnitTest {
	@Test
	fun login_isCorrect() {
		Assert.assertEquals(Utils.validatePassword("Test@1"), true)
		Assert.assertEquals(Utils.validatePassword("Test@a"), false)
		Assert.assertEquals(Utils.validateLogin("user_test"), true)
		Assert.assertEquals(Utils.validateLogin("a"), true)
	}
}