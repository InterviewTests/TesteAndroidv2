package com.example.ibm_test.clean_code.home.presenter

import com.example.ibm_test.clean_code.home.ui.HomeActivityInput

interface HomePresenterInput{
    fun attachHomeInput(homeActivityInput: HomeActivityInput)
}