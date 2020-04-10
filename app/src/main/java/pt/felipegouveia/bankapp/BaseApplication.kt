package pt.felipegouveia.bankapp

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import pt.felipegouveia.bankapp.di.component.DaggerAppComponent

class BaseApplication: DaggerApplication() {

    /**
     * Injects application context
     * */
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }

}