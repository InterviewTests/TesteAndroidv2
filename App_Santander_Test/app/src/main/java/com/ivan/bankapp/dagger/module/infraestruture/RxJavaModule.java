package com.ivan.bankapp.dagger.module.infraestruture;

import com.ivan.bankapp.infraestruture.operator.WorkerOperator;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RxJavaModule {

    @Singleton
    @Provides
    WorkerOperator providesWorkerOperator() {
        return new WorkerOperator();
    }
}
