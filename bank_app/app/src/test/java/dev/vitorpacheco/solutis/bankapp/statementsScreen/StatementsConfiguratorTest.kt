package dev.vitorpacheco.solutis.bankapp.statementsScreen

import org.junit.Assert.*
import org.junit.Test

class StatementsConfiguratorTest {

    @Test
    fun `test if activity is configured`() {
        val activity = StatementsActivity()

        StatementsConfigurator.INSTANCE.configure(activity)

        assertNotNull(activity.router)
        assertNotNull(activity.output)
        assertNotNull(activity.router?.activity)
    }

}