package com.solinftec.desafiosantander_rafaelpimenta.viewmodel

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.solinftec.desafiosantander_rafaelpimenta.communication.Repository
import com.solinftec.desafiosantander_rafaelpimenta.model.Statement

class StatementsViewModel : ViewModel() {
    private var repository: Repository = Repository()

    val statements = ObservableArrayList<Statement>()
    val loadingVisibility = ObservableBoolean(false)
    val message = ObservableField<String>()

    var nameText = ObservableField<String>()
    var accountText = ObservableField<String>()
    var balanceText = ObservableField<String>()



    fun load() {
        loadingVisibility.set(true)
        message.set("")
        loadingVisibility.set(true)
        message.set("")
        repository.listAll({ items ->
            statements.clear()
            statements.addAll(items)
            loadingVisibility.set(false)
        }, {
            message.set("item added")
            loadingVisibility.set(false)
        })
    }
}