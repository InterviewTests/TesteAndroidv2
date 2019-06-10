package br.com.douglas.fukuhara.bank.home.presenter

import br.com.douglas.fukuhara.bank.R
import br.com.douglas.fukuhara.bank.home.Contract
import br.com.douglas.fukuhara.bank.home.ui.HomeActivity
import br.com.douglas.fukuhara.bank.network.vo.StatementListVo
import br.com.douglas.fukuhara.bank.utils.TestUtils
import io.mockk.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.lang.ref.WeakReference
import java.math.BigDecimal

class HomePresenterTest {
    private val mActivity: HomeActivity = mockk(relaxed = true)
    private lateinit var mOutput: WeakReference<Contract.HomeActivityInput>
    private lateinit var mPresenter: HomePresenter

    @Before
    fun setUp() {
        mPresenter = HomePresenter()
        mOutput = spyk(WeakReference<Contract.HomeActivityInput>(mActivity))
        mPresenter.setOutput(mOutput)
    }

    @After
    fun tearDown() {
        mOutput.clear()
        unmockkAll()
    }

    @Test
    fun `Given An Empty User Info, Then Request Activity To Fill User Info with Dashed Value`() {
        mPresenter.noUserInfo()

        verify(exactly = 1) { mOutput.get()?.setHeaderInformation("-", "-", "-") }

        mOutput.get()?.let { confirmVerified(it) }
    }

    @Test
    fun `Given A Valid User Info, Then Presenter Should Invoke Activity With Formatted Data`() {
        val passedName = "Givenname Surname"
        val refName = passedName
        val passedAgency = "123456789"
        val passedBankAccount = "9876"
        val refAccount = "9876 / 12.345678-9"
        val passedBalance = BigDecimal(12.349)
        val refBalance = "R$12,35"
        mPresenter.setHomeHeaderInfo(passedName, passedAgency, passedBankAccount, passedBalance)

        verify(exactly = 1) { mOutput.get()?.setHeaderInformation(refName, refAccount, refBalance) }

        mOutput.get()?.let { confirmVerified(it) }
    }

    @Test
    fun `Given A Empty List Of Recent Items, Then Request Activity To Cancel Loader And Show No Data Available`() {
        mPresenter.setNoUserDataAvailable()

        verify(exactly = 1) {
            mOutput.get()?.hideContentLoader()
            mOutput.get()?.hideUserDataContainer()
            mOutput.get()?.showNoDataAvailable()
        }

        mOutput.get()?.let { confirmVerified(it) }
    }

    @Test
    fun `Given A Server Error With Message, Then Notify Activity With The Server Error Message`() {
        val messageError = "Algo deu errado, tente novamente!"
        mPresenter.showUserDataErrorMessage(messageError)

        verify(exactly = 1) {
            mOutput.get()?.hideContentLoader()
            mOutput.get()?.hideUserDataContainer()
            mOutput.get()?.showNoDataAvailable()
            mOutput.get()?.notifyErrorToUser(messageError)
        }

        mOutput.get()?.let { confirmVerified(it) }
    }

    @Test
    fun `Given A Server Error Without Message, Then Notify Activity With Generic Error Message`() {
        mPresenter.showUserDataGenericError()

        verify(exactly = 1) {
            mOutput.get()?.hideContentLoader()
            mOutput.get()?.hideUserDataContainer()
            mOutput.get()?.showNoDataAvailable()
            mOutput.get()?.notifyResourceErrorToUser(R.string.login_generic_error)
        }

        mOutput.get()?.let { confirmVerified(it) }
    }

    @Test
    fun `When Performing A Data Fetch, Then Invoke Loader In Activity`() {
        mPresenter.onDataFetch()

        verify(exactly = 1) {
            mOutput.get()?.showContentLoader()
            mOutput.get()?.hideUserDataContainer()
            mOutput.get()?.hideNoDataAvailable()
        }

        mOutput.get()?.let { confirmVerified(it) }
    }

    @Test
    fun `When Performing A Successful User Data Fetch, Then Pass The List Of Statement To Actvity `() {
        val statementListVo = TestUtils.fromJsonToObj("json/successful_statement_list.json", StatementListVo::class.java)
        val statementList = statementListVo.statementList

        mPresenter.setUserDataInfo(statementList)

        verify(exactly = 1) {
            mOutput.get()?.setRecentList(statementList)
            mOutput.get()?.hideContentLoader()
            mOutput.get()?.showUserDataContainer()
            mOutput.get()?.hideNoDataAvailable()
        }

        mOutput.get()?.let { confirmVerified(it) }
    }
}