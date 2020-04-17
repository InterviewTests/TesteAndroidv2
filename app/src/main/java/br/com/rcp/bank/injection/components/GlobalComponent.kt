package br.com.rcp.bank.injection.components

import br.com.rcp.bank.annotations.Global
import br.com.rcp.bank.injection.modules.ContextModule
import dagger.Component

@Global
@Component(modules = [ContextModule::class])
interface GlobalComponent {
	fun getDatabaseComponent(): DatabaseComponent
}