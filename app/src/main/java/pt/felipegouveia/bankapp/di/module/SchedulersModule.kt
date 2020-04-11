package pt.felipegouveia.bankapp.di.module

import dagger.Module
import dagger.Provides
import pt.felipegouveia.bankapp.presentation.BaseSchedulerProvider
import pt.felipegouveia.bankapp.presentation.SchedulerProvider
import javax.inject.Singleton

@Module
class SchedulersModule {

    /**
     * Provides the ScheduleProvider
     */
    @Provides
    @Singleton
    internal fun provideSchedulersProvider(): BaseSchedulerProvider {
        return SchedulerProvider()
    }

}