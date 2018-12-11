package com.accenture.primo.bankapp.ui.main.contracts

import com.accenture.primo.bankapp.model.User

interface IMainInteractor {
    fun fetchData(user: User)
}