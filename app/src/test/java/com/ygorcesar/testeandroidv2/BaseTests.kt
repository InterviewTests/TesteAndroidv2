package com.ygorcesar.testeandroidv2

import com.ygorcesar.testeandroidv2.base.common.network.NetworkHandler
import org.junit.Rule
import org.junit.rules.ExpectedException
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.junit.MockitoRule

@RunWith(MockitoJUnitRunner.Silent::class)
abstract class BaseTests {

    @Rule
    @JvmField
    val mockitoRule: MockitoRule = MockitoJUnit.rule().silent()

    @Rule
    @JvmField
    val schedulersRule = RxSchedulerOverrideRule()

    @Rule
    @JvmField
    val expectedException: ExpectedException = ExpectedException.none()

    @Mock
    lateinit var networkHandler: NetworkHandler
}