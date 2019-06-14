package com.accenture.santander.index

import com.accenture.santander.dataManager.entity.UserEntity

class IndexContracts {

    interface IndexPresenterInput {
        fun auth()
    }

    interface IndexInteractorInput {
        fun searchAuth()
    }

    interface IndexPresenterOutput {
        fun next()
    }

    interface IndexInteractorOutput {
        fun verifyAuth(user: UserEntity?)
    }
}