package br.com.rms.bankapp.di.module

import br.com.rms.bankapp.utils.validations.user.UserValidations
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ValidationModule {

    @Provides
    @Singleton
    internal fun userValidator(userValidations : UserValidations): UserValidations {
        return userValidations
    }
}