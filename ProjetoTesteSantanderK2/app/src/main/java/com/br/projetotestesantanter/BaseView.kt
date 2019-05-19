package com.br.projetotestesantanter

import android.content.Context

interface BaseView  {

    fun getContext() : Context
    fun showErroMsg(msg : String)
    fun showProgressBar()
    fun hiddenProgressBar()

}