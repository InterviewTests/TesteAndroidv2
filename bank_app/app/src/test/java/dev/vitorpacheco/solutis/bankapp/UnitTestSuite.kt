package dev.vitorpacheco.solutis.bankapp

import dev.vitorpacheco.solutis.bankapp.extensions.BigDecimalExtensionKtTest
import dev.vitorpacheco.solutis.bankapp.extensions.DateExtensionKtTest
import dev.vitorpacheco.solutis.bankapp.extensions.StringExtensionsKtTest
import dev.vitorpacheco.solutis.bankapp.loginScreen.LoginConfiguratorTest
import dev.vitorpacheco.solutis.bankapp.loginScreen.LoginInteractorTest
import dev.vitorpacheco.solutis.bankapp.loginScreen.LoginPresenterUnitTest
import dev.vitorpacheco.solutis.bankapp.statementsScreen.StatementsConfiguratorTest
import dev.vitorpacheco.solutis.bankapp.statementsScreen.StatementsInteractorTest
import dev.vitorpacheco.solutis.bankapp.statementsScreen.StatementsPresenterUnitTest
import dev.vitorpacheco.solutis.bankapp.workers.SharedPreferencesWorkerTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    LoginPresenterUnitTest::class,
    LoginInteractorTest::class,
    LoginConfiguratorTest::class,

    StatementsPresenterUnitTest::class,
    StatementsInteractorTest::class,
    StatementsConfiguratorTest::class,

    SharedPreferencesWorkerTest::class,

    BigDecimalExtensionKtTest::class,
    DateExtensionKtTest::class,
    StringExtensionsKtTest::class
)
class UnitTestSuite
