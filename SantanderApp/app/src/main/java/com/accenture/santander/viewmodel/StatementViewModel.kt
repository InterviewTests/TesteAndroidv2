package com.accenture.santander.viewmodel

import androidx.lifecycle.ViewModel

data class StatementViewModel(
    val statements: MutableList<Statement> = ArrayList()
) : ViewModel()