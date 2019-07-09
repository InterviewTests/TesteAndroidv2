package br.com.learncleanarchitecture.home.data

import br.com.learncleanarchitecture.home.data.api.HomeApi
import br.com.learncleanarchitecture.home.presentation.HomeRequest
import br.com.learncleanarchitecture.home.presentation.Statment
import br.com.learncleanarchitecture.login.data.api.Error
import br.com.learncleanarchitecture.util.DataResponse
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class HomeRepository {

    companion object {
        val api = HomeApi()

        private var repo: HomeRepository? = null

        fun getInstance(): HomeRepository {
            if (repo == null) {
                repo = HomeRepository()
            }

            return repo!!
        }
    }

    fun getStatments(
        request: HomeRequest,
        output: DataResponse<List<Statment>>
    ) {

        val requestApi = HomeApi.HomeRequestApi(request.id)
        var list = ArrayList<Statment>()
        api?.getStatments(requestApi)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe({ it ->
                val statment = Statment()
                statment.title = it.title!!
                statment.desc = it.desc!!
                statment.date = it.date!!
                statment.value = it.value!!
                statment

                list.add(statment)
            }, { e ->
                output.onError(Error(message = e.localizedMessage))
            }, {
                output.onSuccess(list)
            })
    }

}