package com.gft.testegft.di.component;

import android.app.Application;

import com.gft.testegft.base.GftApplication;
import com.gft.testegft.di.module.ActivityBindingModule;
import com.gft.testegft.di.module.AppModule;
import com.gft.testegft.di.module.ContextModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        AppModule.class,
        ContextModule.class,
        AndroidSupportInjectionModule.class,
        ActivityBindingModule.class
})
public interface AppComponent extends AndroidInjector<DaggerApplication> {

    void inject(GftApplication application);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        AppComponent build();
    }
}
