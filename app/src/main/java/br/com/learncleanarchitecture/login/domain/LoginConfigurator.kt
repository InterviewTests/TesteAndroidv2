package br.com.learncleanarchitecture.login.domain

import br.com.learncleanarchitecture.login.presentation.LoginFragment
import br.com.learncleanarchitecture.login.presentation.LoginPresenter
import br.com.learncleanarchitecture.login.presentation.LoginViewModel
import java.lang.ref.WeakReference

object LoginConfigurator {

    fun configureFragment(
        fragment: LoginFragment,
        viewModel: LoginViewModel
    ) {
        val router = LoginRouter()
        router.fragment = WeakReference(fragment)

        val presenter = LoginPresenter()
        presenter.output = WeakReference(fragment)

        val interactor = LoginInteractor()
        interactor.output = presenter
        interactor.viewModel = viewModel

        fragment.output = interactor
        fragment.router = router
    }
}