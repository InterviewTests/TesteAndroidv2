package br.com.flaviokreis.santanderv2

import android.app.Activity
import android.app.Application
import androidx.fragment.app.Fragment
import br.com.flaviokreis.santanderv2.di.AppInjector
import br.com.flaviokreis.santanderv2.di.components.AppComponent
import br.com.flaviokreis.santanderv2.di.components.DaggerAppComponent
import br.com.flaviokreis.santanderv2.di.modules.AppModule
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class BankApplication : Application(), HasActivityInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var mFragmentInjector: DispatchingAndroidInjector<Fragment>

    private lateinit var applicationComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        applicationComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
        applicationComponent.inject(this)

        AppInjector.init(this)
    }

    override fun activityInjector() = dispatchingAndroidInjector
}