package br.com.vinicius.bankapp.internal

import android.annotation.SuppressLint
import android.app.Application
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import br.com.vinicius.bankapp.R
import br.com.vinicius.bankapp.data.repository.StatementRepository
import br.com.vinicius.bankapp.data.repository.UserRepository
import com.google.android.material.snackbar.Snackbar
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
        bind() from singleton { UserRepository() }
        bind() from singleton { StatementRepository() }
    }


    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)

    }

}