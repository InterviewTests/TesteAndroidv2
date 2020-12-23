package com.solinftec.desafiosantander_rafaelpimenta.viewmodel

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import java.sql.Statement

class StatementsViewModel : ViewModel() {
     val statements = ObservableArrayList<Statement>()
    val loadingVisibility = ObservableBoolean(false)
    val message = ObservableField<String>()
}