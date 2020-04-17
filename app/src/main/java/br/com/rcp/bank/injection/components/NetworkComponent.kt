package br.com.rcp.bank.injection.components

import br.com.rcp.bank.annotations.Network
import br.com.rcp.bank.injection.modules.RetrofitModule
import br.com.rcp.bank.repositories.base.Repository
import dagger.Subcomponent

@Network
@Subcomponent(modules = [RetrofitModule::class])
interface NetworkComponent {
	fun getServiceComponent(): ServiceComponent
	fun inject(repository: Repository)
}