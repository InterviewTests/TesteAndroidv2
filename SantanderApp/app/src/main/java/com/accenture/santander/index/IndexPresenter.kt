package com.accenture.santander.index

import android.content.Context
import com.accenture.santander.dataManager.entity.UserEntity
import org.junit.Test
import org.junit.Assert.*

class IndexPresenter(
    private val context: Context,
    private val iIndexPresenterOutput: IndexContracts.IndexPresenterOutput
) : IndexContracts.IndexPresenterInput, IndexContracts.IndexInteractorOutput {

    private val iIndexInteractorInput: IndexContracts.IndexInteractorInput = IndexInteractor(context, this)

    @Test
    override fun auth() {
        assertNotNull(iIndexInteractorInput)

        iIndexInteractorInput.searchAuth()
    }

    override fun verifyAuth(user: UserEntity?) {
        user?.let {
            iIndexPresenterOutput.next()
        }
    }
}