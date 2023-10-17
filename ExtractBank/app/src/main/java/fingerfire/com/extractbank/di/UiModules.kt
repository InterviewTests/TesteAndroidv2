package fingerfire.com.extractbank.di

import fingerfire.com.extractbank.features.login.ui.LoginViewModel
import fingerfire.com.extractbank.features.statements.ui.StatementViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

class UiModules {
    fun getViewModules() = module {
        viewModel {
            LoginViewModel(get())
        }
        viewModel {
            StatementViewModel(get())
        }
    }
}