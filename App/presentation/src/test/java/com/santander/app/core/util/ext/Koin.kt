package com.santander.app.core.util.ext

import com.santander.app.core.di.testModules
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.koin.core.KoinContext
import org.koin.core.KoinProperties
import org.koin.core.parameter.ParameterDefinition
import org.koin.core.parameter.emptyParameterDefinition
import org.koin.core.scope.Scope
import org.koin.dsl.module.Module
import org.koin.log.Logger
import org.koin.log.PrintLogger
import org.koin.standalone.StandAloneContext
import org.spekframework.spek2.dsl.Root


fun Root.applyConfig(beforeGroup: (() -> Unit)? = null, afterGroup: (() -> Unit)? = null) {

    applyRxSchedulers()
    stopKoin()
    startKoin(testModules)

    beforeGroup {
        beforeGroup?.invoke()
    }

    afterGroup {
        afterGroup?.invoke()
    }
}

fun applyRxSchedulers() {
    RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
    RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
    RxJavaPlugins.setNewThreadSchedulerHandler { Schedulers.trampoline() }
    RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
}

fun getKoin(): KoinContext = StandAloneContext.getKoin().koinContext


fun startKoin(
    list: List<Module>,
    properties: KoinProperties = KoinProperties(),
    logger: Logger = PrintLogger()
) = StandAloneContext.startKoin(list, properties, logger)


fun stopKoin() = StandAloneContext.stopKoin()


inline fun <reified T : Any> get(
    name: String = "",
    scope: Scope? = null,
    noinline parameters: ParameterDefinition = emptyParameterDefinition()
): T = getKoin().get(name, scope, parameters)


inline fun <reified T : Any> inject(
    name: String = "",
    scope: Scope? = null,
    noinline parameters: ParameterDefinition = emptyParameterDefinition()
) = lazy { getKoin().get<T>(name, scope, parameters) }