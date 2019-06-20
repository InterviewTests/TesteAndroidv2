package br.com.vinicius.bankapp.internal

import android.app.Application
import android.content.SharedPreferences
import br.com.vinicius.bankapp.data.repository.StatementRepository
import com.jakewharton.threetenabp.AndroidThreeTen
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

class App : Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidXModule(this@App))

        bind() from singleton { Preferences(instance()) }
        bind() from singleton { StatementRepository() }
    }


    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)

    }
}