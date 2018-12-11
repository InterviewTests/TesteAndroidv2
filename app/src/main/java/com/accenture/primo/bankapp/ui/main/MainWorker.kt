package com.accenture.primo.bankapp.ui.main

import com.accenture.primo.bankapp.data.network.RemoteDataSource
import com.accenture.primo.bankapp.ui.main.contracts.IMainWorker
import io.reactivex.Observable

class MainWorker: IMainWorker {
    override fun getUserStatements(userID: Long): Observable<MainModel.MainResponse> {
        return RemoteDataSource.getService().getUserStatements(userID);
    }
}