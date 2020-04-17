package br.com.rcp.bank.injection.components

import br.com.rcp.bank.annotations.Service
import br.com.rcp.bank.injection.modules.RepositoryModule
import br.com.rcp.bank.ui.fragments.viewmodels.LoginVM
import dagger.Subcomponent

@Service
@Subcomponent(modules = [RepositoryModule::class])
interface ServiceComponent {
	fun	inject(service: LoginVM)
}