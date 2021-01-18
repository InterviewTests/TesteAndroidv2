package com.jeanjnap.data.mapper

import com.jeanjnap.data.di.MapperModules
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.test.AutoCloseKoinTest
import org.koin.test.inject

abstract class BaseMapperTest<CLASS_IN, CLASS_OUT>(name: String) : AutoCloseKoinTest() {

    open val mapper: Mapper<CLASS_IN, CLASS_OUT> by inject(named(name))

    @Before
    fun setUpKoin() {
        startKoin {
            modules(MapperModules.mapperModulesItems)
        }
    }

    @Test
    open fun mapClassInToClassOut() {
        assertEquals(mapper.transform(mockClassIn()), mockClassOut())
    }

    abstract fun mockClassIn(): CLASS_IN

    abstract fun mockClassOut(): CLASS_OUT
}