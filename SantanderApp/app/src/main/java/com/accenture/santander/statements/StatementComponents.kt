package com.accenture.santander.statements

import dagger.Component

@Component(modules = [StatementModulo::class])
interface StatementComponents {
    fun inject(fragment: StatementFragment)
    fun inject(presenter: StatementPresenter)
    fun inject(interactor: StatementInteractor)
}