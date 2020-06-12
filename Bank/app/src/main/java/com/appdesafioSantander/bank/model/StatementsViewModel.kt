package com.appdesafioSantander.bank.model

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.*
import com.appdesafioSantander.bank.repository.Repository

class StatementsViewModel : ViewModel() {
    private var repository: Repository = Repository()

    val statements = ObservableArrayList<Statement>()
    val loadingVisibility = ObservableBoolean(false)
    val message = ObservableField<String>()

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