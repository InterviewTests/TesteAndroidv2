package com.bank.testeandroidv2.loginScreen;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

// Runs all unit tests.
@RunWith(Suite.class)
@Suite.SuiteClasses({
        LoginActivityUnitTest.class,
        LoginInteractorUnitTest.class,
        LoginPresenterUnitTest.class,
        LoginRouterUnitTest.class
})
public class LoginUnitTestSuite {}

