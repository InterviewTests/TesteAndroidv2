package dev.vitorpacheco.solutis.bankapp.loginScreen

import org.junit.Assert.assertNotNull
import org.junit.Test

class LoginConfiguratorTest {

    @Test
    fun `test if activity is configured`() {
        val activity = LoginActivity()

        LoginConfigurator.INSTANCE.configure(activity)

        assertNotNull(activity.router)
        assertNotNull(activity.output)
        assertNotNull(activity.router?.activity)
    }

}