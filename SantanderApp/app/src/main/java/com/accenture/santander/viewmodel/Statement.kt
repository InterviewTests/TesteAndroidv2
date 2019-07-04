package com.accenture.santander.viewmodel

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.accenture.santander.BR
import com.accenture.santander.entity.Statement
import com.accenture.santander.utils.DateTime
import com.accenture.santander.utils.MoneyFormact

class Statement : BaseObservable() {

    @Bindable
    var title: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.name)
        }

    @Bindable
    var desc: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.bankAccount)
        }

    @Bindable
    var date: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.agency)
        }

    @Bindable
    var value: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.balance)
        }

    fun mapper(state: Statement): com.accenture.santander.viewmodel.Statement {
        this.title = state.title
        this.date = DateTime.mask(state.date)
        this.desc = state.desc
        this.value = MoneyFormact.mask(state.value)
        return this
    }
}