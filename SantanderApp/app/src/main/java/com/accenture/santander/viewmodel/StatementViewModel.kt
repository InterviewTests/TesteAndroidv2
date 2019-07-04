package com.accenture.santander.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

data class StatementViewModel(
    val statementLiveData: MutableLiveData<MutableList<Statement>> = MutableLiveData(ArrayList())
) : ViewModel()