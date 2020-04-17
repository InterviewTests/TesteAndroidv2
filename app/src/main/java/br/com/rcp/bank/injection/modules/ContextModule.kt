package br.com.rcp.bank.injection.modules

import android.content.Context
import br.com.rcp.bank.annotations.Global
import dagger.Module
import dagger.Provides

@Module
class ContextModule(val context: Context) {
	@Global
	@Provides
	fun provideContext(): Context {
		return context
	}
}