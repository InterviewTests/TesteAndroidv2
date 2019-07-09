package br.com.learncleanarchitecture.home.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.learncleanarchitecture.home.data.HomeRepository
import br.com.learncleanarchitecture.login.data.api.Error
import br.com.learncleanarchitecture.util.DataResponse

class HomeViewModel(homeRepository: HomeRepository? = null) : ViewModel() {

    private var homeRepository: HomeRepository? = homeRepository

    private var callback = object : DataResponse<List<Statment>>() {
        override fun onSuccess(response: List<Statment>) {
            listStatment.postValue(response)
        }

        override fun onError(response: Error) {
            error.postValue(response)
        }
    }

    var listStatment: MutableLiveData<List<Statment>> = MutableLiveData()
    var error: MutableLiveData<Error> = MutableLiveData()

    fun loadStatments(homeRequest: HomeRequest) {

        if (homeRepository == null) {
            homeRepository = HomeRepository.getInstance()
        }

        homeRepository?.getStatments(homeRequest, callback)
    }
}

data class HomeRequest(var id: Int)
