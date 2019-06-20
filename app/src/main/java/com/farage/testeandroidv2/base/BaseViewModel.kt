package com.farage.testeandroidv2.base

import android.arch.lifecycle.ViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
abstract class BaseViewModel : ViewModel() {

//    private val injector: UserComponent = DaggerUserComponent.builder().userModule(UserModule()).build()
//
//    init {
//        inject()
//    }
//
//    private fun inject() {
//        when (this) {
//            is UserViewModel -> injector.inject(this)
//        }
//    }


}