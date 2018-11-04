package com.ygorcesar.testeandroidv2

import android.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Rule

abstract class BaseViewModelTests : BaseTests() {

    @Rule
    @JvmField
    val archComponentsRule = InstantTaskExecutorRule()

}