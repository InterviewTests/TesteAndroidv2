package com.tata.bank.main

import java.lang.ref.WeakReference

interface MainPresenterInput {

}

class MainPresenter: MainPresenterInput {
    lateinit var output: WeakReference<MainActivityInput>
    lateinit var router: MainRouterInput

}