package br.com.rphmelo.bankapp.currency

import br.com.rphmelo.bankapp.currency.api.CurrencyService
import br.com.rphmelo.bankapp.currency.domain.interactor.CurrencyInteractor
import br.com.rphmelo.bankapp.currency.presentation.CurrencyActivity
import br.com.rphmelo.bankapp.currency.presentation.CurrencyPresenter
import br.com.rphmelo.bankapp.currency.repository.CurrencyRepository
import br.com.rphmelo.bankapp.login.domain.models.UserAccount
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito
import org.mockito.Mockito.*

@RunWith(JUnit4::class)
class CurrencyInteractorTest {

    private lateinit var currencyInteractor: CurrencyInteractor
    private lateinit var currencyRepository: CurrencyRepository
    private lateinit var currencyLoginService: CurrencyService
    private lateinit var currencyPresenter: CurrencyPresenter
    private lateinit var currencyActivity: CurrencyActivity

    @Before
    fun setup() {
        currencyLoginService = Mockito.mock(CurrencyService::class.java)
        currencyRepository = Mockito.mock(CurrencyRepository::class.java)
        currencyInteractor = spy(CurrencyInteractor(currencyRepository))
        currencyActivity = Mockito.mock(CurrencyActivity::class.java)
        currencyPresenter = spy(CurrencyPresenter(currencyActivity, currencyInteractor))
    }

    @Test
    fun should_verify_if_tollbar_was_setted_up() {
        currencyInteractor.setupToolbar(currencyPresenter)

        verify(currencyPresenter, times(1)).onSetupToolbar()
        verify(currencyActivity, times(1)).setupToolbar()
    }

    @Test
    fun should_verify_if_tollbar_data_was_setted() {
        val account = UserAccount(
                1,
                "Raphael",
                "123456789",
                "1234",
                500.00
        )

        currencyInteractor.setToolbarData(account, currencyPresenter)

        verify(currencyPresenter, times(1)).onSetToolbarData(account)
        verify(currencyActivity, times(1)).setToolbarData(account)
        verify(currencyActivity, times(1)).hideProgress()
    }
}