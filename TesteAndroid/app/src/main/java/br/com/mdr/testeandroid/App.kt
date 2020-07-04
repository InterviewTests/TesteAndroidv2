package br.com.mdr.testeandroid

import android.app.Application
import br.com.mdr.testeandroid.di.activityModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * @author Marlon D. Rocha
 * @since 04/07/20
 */
class App: Application() {

    override fun onCreate() {
        super.onCreate()
        setupKoin()
    }

    private fun setupKoin() {
        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    activityModule
                )
            )
        }
    }
}