package com.br.bankapp.ui.statements

import android.content.Context
import android.view.View
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.*
import com.br.bankapp.R
import com.br.bankapp.model.Statement
import com.br.bankapp.repository.BankRepository

class StatementsViewModel : ViewModel() {
    private var repository: BankRepository = BankRepository()

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
            if (items.isEmpty()) {
                message.set("nao há itens")
            }
            loadingVisibility.set(false)
        }, {
            message.set("item added")
            loadingVisibility.set(false)
        })
    }

}