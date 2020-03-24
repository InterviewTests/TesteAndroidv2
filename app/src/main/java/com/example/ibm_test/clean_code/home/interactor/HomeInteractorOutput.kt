package com.example.ibm_test.clean_code.home.interactor

import com.example.ibm_test.clean_code.home.presenter.HomePresenterOutput
import com.example.ibm_test.service.UserService
import javax.inject.Inject

class HomeInteractorOutput @Inject constructor(private val homePresenterInput: HomePresenterOutput, private val userService: UserService): HomeInteractorInput {

}