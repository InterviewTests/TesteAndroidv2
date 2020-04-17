package br.com.rcp.bank.injection.components

import br.com.rcp.bank.annotations.Persistence
import br.com.rcp.bank.injection.modules.DatabaseModule
import dagger.Subcomponent

@Persistence
@Subcomponent(modules = [DatabaseModule::class])
interface DatabaseComponent {
	fun getNetworkComponent(): NetworkComponent
}