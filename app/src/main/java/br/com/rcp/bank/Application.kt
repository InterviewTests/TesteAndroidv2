package br.com.rcp.bank

import android.app.Application
import br.com.rcp.bank.injection.components.DaggerGlobalComponent
import br.com.rcp.bank.injection.components.NetworkComponent
import br.com.rcp.bank.injection.modules.ContextModule

class Application : Application() {
	companion object {
		lateinit	var		component		: NetworkComponent
	}

	override fun onCreate() {
		super.onCreate()
		setComponents()
	}

	private fun setComponents() {
		component = DaggerGlobalComponent.builder().contextModule(ContextModule(this)).build().getDatabaseComponent().getNetworkComponent()
	}
}