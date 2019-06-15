package com.accenture.santander.viewmodel

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.accenture.santander.BR

class User : BaseObservable(){

    @Bindable
    var login: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.login)
        }

    @Bindable
    var password: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.password)
        }
}