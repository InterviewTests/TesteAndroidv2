package br.com.bankapp.di

import br.com.bankapp.di.login.LoginComponent
import br.com.bankapp.di.main.MainComponent
import dagger.Module

@Module(
    subcomponents = [
        LoginComponent::class,
        MainComponent::class
    ]
)
class SubComponentsModule