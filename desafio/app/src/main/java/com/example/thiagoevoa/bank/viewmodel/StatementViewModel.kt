package com.example.thiagoevoa.bank.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.thiagoevoa.bank.model.Statement

class StatementViewModel: ViewModel() {
    var statementsLiveData = MutableLiveData<MutableList<Statement>>()
}