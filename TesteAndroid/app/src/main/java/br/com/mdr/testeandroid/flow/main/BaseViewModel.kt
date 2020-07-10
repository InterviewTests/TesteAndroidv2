package br.com.mdr.testeandroid.flow.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * @author Marlon D. Rocha
 * @since 10/07/20
 */
open class BaseViewModel: ViewModel() {
    var isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
}