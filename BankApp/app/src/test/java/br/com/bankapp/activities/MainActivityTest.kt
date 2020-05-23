package br.com.bankapp.activities

import android.os.Build
import android.view.View
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import androidx.paging.PagedList
import androidx.test.ext.junit.runners.AndroidJUnit4
import asPagedList
import br.com.bankapp.R
import br.com.bankapp.commons.Error
import br.com.bankapp.commons.Loading
import br.com.bankapp.commons.UiState
import br.com.bankapp.data.utils.convertStringToDate
import br.com.bankapp.domain.models.Statement
import br.com.bankapp.domain.models.UserAccount
import br.com.bankapp.ui.main.MainActivity
import br.com.bankapp.ui.main.MainViewModel
import br.com.bankapp.utils.CoroutineTestRule
import br.com.fortes.appcolaborador.getOrAwaitValue
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers.*
import org.hamcrest.MatcherAssert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.ArgumentMatchers
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import org.robolectric.Robolectric
import org.robolectric.android.controller.ActivityController
import org.robolectric.annotation.Config
import org.robolectric.shadows.ShadowToast
import java.net.UnknownHostException

@RunWith(AndroidJUnit4::class)
@Config(sdk = [Build.VERSION_CODES.P])
class MainActivityTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @Rule
    @JvmField
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    private lateinit var activityController: ActivityController<MainActivity>

    private lateinit var activity: MainActivity
    @Mock
    private lateinit var viewModel: MainViewModel

    @Mock
    private lateinit var uiStateLiveData: LiveData<UiState>

    @Mock
    private lateinit var userAccountLiveData: LiveData<UserAccount>

    @Mock
    private lateinit var statementsLiveData: LiveData<PagedList<Statement>>


    @Captor
    private lateinit var uiStateObserverCaptor: ArgumentCaptor<Observer<UiState>>

    @Captor
    private lateinit var userAccountObserverCaptor: ArgumentCaptor<Observer<UserAccount>>

    @Captor
    private lateinit var statementsObserverCaptor: ArgumentCaptor<Observer<PagedList<Statement>>>


    @Before
    fun setup() {
        `when`(viewModel.uiState).thenReturn(uiStateLiveData)
        `when`(viewModel.getUserAccount(1)).thenReturn(userAccountLiveData)
        `when`(viewModel.getStatements(1)).thenReturn(statementsLiveData)

        activityController = Robolectric.buildActivity(MainActivity::class.java)
        activity = activityController.get()

        activityController.create()
        activity.setTestViewModel(viewModel)

        activityController.start()

        verify(uiStateLiveData).observe(ArgumentMatchers.any(LifecycleOwner::class.java),
            uiStateObserverCaptor.capture())
        verify(userAccountLiveData).observe(ArgumentMatchers.any(LifecycleOwner::class.java),
            userAccountObserverCaptor.capture())
        verify(statementsLiveData).observe(ArgumentMatchers.any(LifecycleOwner::class.java),
            statementsObserverCaptor.capture())
    }

    @Test
    fun `has visible loading view and hidden recycler view`() {
        uiStateObserverCaptor.value.onChanged(Loading)

        assertThat(activity.main_progress_bar.visibility, equalTo(View.VISIBLE))
        assertThat(activity.statements_recyclerview.visibility, equalTo(View.GONE))
    }

    @Test
    fun `has set user account information`() {
        userAccountObserverCaptor.value.onChanged(
            UserAccount(
                userId = 1,
                name = "User",
                bankAccount = "2050",
                agency = "012314564",
                balance = 3.3445
            )
        )

        assertThat(activity.name_text.text.toString(), equalTo("User"))
        assertThat(activity.account_text.text.toString(), equalTo("2050 / 01.231456-4"))
        assertThat(activity.balance_text.text.toString(), equalTo("R$ 3,34"))
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `has set statements information`() {
        runBlockingTest {
            val liveData = liveData {
                emit(
                    listOf(
                        Statement(
                            id = 1,
                            title = "Pagamento",
                            desc = "Conta de luz",
                            date = convertStringToDate("2018-08-15"),
                            value = -50.0,
                            userId = 1
                        ),

                        Statement(
                            id = 2,
                            title = "TED Recebida",
                            desc = "Joao Alfredo",
                            date = convertStringToDate("2018-07-25"),
                            value = -745.03,
                            userId = 1
                        )
                    ).asPagedList()
                )
            }
            val statements = liveData.getOrAwaitValue()
            statementsObserverCaptor.value.onChanged(statements)

            assertThat(activity.statements_recyclerview.visibility, equalTo(View.VISIBLE))
            assertThat(activity.statements_recyclerview.adapter!!.itemCount, equalTo(2))
        }
    }

    @Test
    fun `has visible toast message`() {
        uiStateObserverCaptor.value.onChanged(Error(UnknownHostException("test")))

        assertThat(activity.getString(R.string.text_connection_unavailable),
            equalTo(ShadowToast.getTextOfLatestToast()))
    }
}