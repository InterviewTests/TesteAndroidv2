package br.com.crmm.bankapplication

import br.com.crmm.bankapplication.infrastructure.di.module.getModulesList
import org.junit.Rule
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule

abstract class AbstractUnitTest: KoinTest {

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        modules(getModulesList())
    }

}