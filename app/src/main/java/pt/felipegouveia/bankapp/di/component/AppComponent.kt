package pt.felipegouveia.bankapp.di.component

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import pt.felipegouveia.bankapp.BaseApplication
import pt.felipegouveia.bankapp.di.module.*
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
    AndroidSupportInjectionModule::class,
    NetworkModule::class,
    ServiceModule::class,
    SchedulersModule::class,
    CoreModule::class,
    ViewModelModule::class,
    FragmentInjectorsModule::class))
interface AppComponent : AndroidInjector<BaseApplication> {

    override fun inject(baseApplication: BaseApplication)

    /**
     * Component builder.
     */
    @Component.Builder
    interface Builder {

        /**
         * Builder for the component.
         * @return the component instance.
         */
        fun build(): AppComponent

        /**
         * Module dependency that need to be created manually and passed in.
         * @param application instance of the Application.
         * @return the builder instance so that the call can be chained.
         */
        @BindsInstance
        fun application(application: Application): Builder
    }

}