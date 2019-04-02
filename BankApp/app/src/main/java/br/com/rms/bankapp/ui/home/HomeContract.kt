package br.com.rms.bankapp.ui.home

import br.com.rms.bankapp.base.mvp.BaseContract
import br.com.rms.bankapp.ui.login.LoginContract

interface HomeContract : BaseContract.View {
    interface View : BaseContract.View{

    }

    interface Presenter : BaseContract.Presenter<View>{

    }
}
