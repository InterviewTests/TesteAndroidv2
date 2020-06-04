package com.gft.testegft.base;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        AppModule.class,
})
public interface AppComponent {
    void inject(GftApplication gftApplication);
}
