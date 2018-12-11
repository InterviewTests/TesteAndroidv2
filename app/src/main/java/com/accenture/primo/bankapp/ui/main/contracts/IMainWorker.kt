package com.accenture.primo.bankapp.ui.main.contracts

import com.accenture.primo.bankapp.ui.main.MainModel
import io.reactivex.Observable

interface IMainWorker {
    fun getUserStatements(userID: Long): Observable<MainModel.MainResponse>
}