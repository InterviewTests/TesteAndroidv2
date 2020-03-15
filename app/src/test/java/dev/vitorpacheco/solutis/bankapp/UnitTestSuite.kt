package dev.vitorpacheco.solutis.bankapp

import dev.vitorpacheco.solutis.bankapp.loginScreen.LoginPresenterUnitTest
import dev.vitorpacheco.solutis.bankapp.statementsScreen.StatementsPresenterUnitTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    LoginPresenterUnitTest::class,
    StatementsPresenterUnitTest::class
)
class UnitTestSuite