package dev.vitorpacheco.solutis.bankapp

import dev.vitorpacheco.solutis.bankapp.loginScreen.LoginActivityTest
import dev.vitorpacheco.solutis.bankapp.statementsScreen.StatementsActivityTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    LoginActivityTest::class,
    StatementsActivityTest::class
)
class ActivityTestSuite