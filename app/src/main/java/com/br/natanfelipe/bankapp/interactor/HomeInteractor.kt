package com.br.natanfelipe.bankapp.interactor

import com.br.natanfelipe.bankapp.interfaces.home.HomePresenterInput
import com.br.natanfelipe.bankapp.view.home.HomeRequest
import com.br.natanfelipe.bankapp.view.home.HomeResponse
import com.br.natanfelipe.bankapp.interfaces.home.HomeInteractorInput
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HomeInteractor : HomeInteractorInput {

    lateinit var output : HomePresenterInput

    override fun fetchHomeMetaData(homeRequest: HomeRequest) {

        var homeResponse = HomeResponse()
        if(homeRequest.api != null){
            homeRequest.api.loadBills()?.subscribeOn(Schedulers.io())?.observeOn(AndroidSchedulers.mainThread())
                    ?.subscribe({
                        bill ->
                        for (b in bill.statementList){
                            homeResponse.billsList.add(b)
                        }
                    },{ e -> e.printStackTrace()},{
                        output.presentHomeMetaData(homeResponse)
                    })
        }
    }
}