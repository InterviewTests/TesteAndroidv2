package com.accenture.santander.viewmodel

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.accenture.santander.BR
import com.accenture.santander.entity.UserAccount
import com.accenture.santander.interector.dataManager.entity.UserEntity

class Account : BaseObservable() {

    @Bindable
    var name: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.name)
        }

    @Bindable
    var bankAccount: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.bankAccount)
        }

    @Bindable
    var agency: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.agency)
        }

    @Bindable
    var balance: Double = 0.0
        set(value) {
            field = value
            notifyPropertyChanged(BR.balance)
        }

    fun mapper(user: UserEntity): Account {
        this.name = user.name
        this.bankAccount = user.bankAccount
        this.agency = user.agency
        this.balance = user.balance
        return this
    }

    fun mapper(user: UserAccount): Account {
        this.name = user.name
        this.bankAccount = user.bankAccount
        this.agency = user.agency
        this.balance = user.balance
        return this
    }
}