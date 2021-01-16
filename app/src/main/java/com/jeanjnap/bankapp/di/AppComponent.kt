package com.jeanjnap.bankapp.di

import com.jeanjnap.bankapp.di.AppModules.utilsModules
import com.jeanjnap.bankapp.di.AppModules.viewModelModules
import com.jeanjnap.bankapp.di.DataModules.dataModulesItems
import com.jeanjnap.bankapp.di.DataModules.serviceModulesItems
import com.jeanjnap.bankapp.di.DomainModules.domainModulesItems
import com.jeanjnap.bankapp.di.InfrastructureModules.infrastructureModulesItems

object AppComponent {

    fun getAllModules() = listOf(
        utilsModules, viewModelModules, domainModulesItems, serviceModulesItems, dataModulesItems,
        infrastructureModulesItems
    )
}
