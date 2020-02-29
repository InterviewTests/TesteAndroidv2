package com.lucianogiardino.bankapp.login

interface LoginContract {

    interface View{

    }

    interface Presenter{
        fun validate()
        fun login()
    }
}