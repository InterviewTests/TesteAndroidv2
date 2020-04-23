package com.tata.bank.main

import java.lang.ref.WeakReference

interface MainRouterInput {

}

class MainRouter: MainRouterInput {
    lateinit var activity: WeakReference<MainActivity>
}