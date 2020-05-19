package br.com.bankapp.di

import br.com.bankapp.di.login.LoginComponent
import dagger.Module

@Module(
    subcomponents = [
        LoginComponent::class
    ]
)
class SubComponentsModule