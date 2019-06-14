package com.accenture.santander.index

import android.content.Context
import com.accenture.santander.dataManager.repository.deviceRepository.IUserRepository
import com.accenture.santander.dataManager.repository.deviceRepository.UserRepository
import org.junit.Assert
import org.junit.Test

class IndexInteractor(
    private val context: Context,
    private val iIndexInteractorOutput: IndexContracts.IndexInteractorOutput
) : IndexContracts.IndexInteractorInput {

    private val iUserRepository: IUserRepository = UserRepository(context)

    @Test
    override fun searchAuth() {
        Assert.assertNotNull(iUserRepository)

        iIndexInteractorOutput.verifyAuth(iUserRepository.findDesc())
    }
}