package br.com.bankapp

import br.com.bankapp.di.AppComponent
import br.com.bankapp.di.DaggerTestAppComponent


class BankAppTestApplication : BaseApplication() {

    override val appComponent: AppComponent by lazy {
        initializeComponent()
    }

    private fun initializeComponent(): AppComponent {
        // Creates an instance of AppComponent using its Factory constructor
        // We pass the applicationContext that will be used as Context in the graph
        return DaggerTestAppComponent.builder()
            .application(this)
            .build()
    }

    override fun onCreate() {
        super.onCreate()
    }
}