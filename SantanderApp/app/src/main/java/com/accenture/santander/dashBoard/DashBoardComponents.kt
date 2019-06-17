package com.accenture.santander.dashBoard

import dagger.Component
import javax.inject.Singleton

@Component(modules = [DashBoardModulo::class])
interface DashBoardComponents {
    fun inject(fragment: DashBoardFragment)
    fun inject(presenter: DashBoardPresenter)
    fun inject(interactor: DashBoardInteractor)
}