package com.jeanjnap.bankapp.di

import com.jeanjnap.infrastructure.network.Network
import com.jeanjnap.infrastructure.network.NetworkImpl
import org.koin.dsl.module

object InfrastructureModules {
    val infrastructureModulesItems = module {
        single<Network> { NetworkImpl(get()) }
    }
}
