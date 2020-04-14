package pt.felipegouveia.bankapp.presentation.login

import android.content.Context
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.hamcrest.Matchers.isEmptyString
import org.hamcrest.Matchers.not
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import pt.felipegouveia.bankapp.R
import pt.felipegouveia.bankapp.presentation.login.entity.UserAccountPresentation
import pt.felipegouveia.bankapp.util.EspressoIdlingResource
import pt.felipegouveia.bankapp.util.ToastMatcher
import pt.felipegouveia.bankapp.util.ViewModelUtil
import pt.felipegouveia.bankapp.util.persistence.BankSharedPreferences
import pt.felipegouveia.bankapp.util.security.CipherWrapper
import pt.felipegouveia.bankapp.util.security.KeyStoreWrapper

@RunWith(AndroidJUnit4::class)
class LoginFragmentTest {

    lateinit var context: Context

    @Mock
    lateinit var navController: NavController

    private val cipherWrapper = CipherWrapper(CipherWrapper.TRANSFORMATION_ASYMMETRIC)

    lateinit var keyStoreWrapper: KeyStoreWrapper

    private lateinit var sharedPreferences: BankSharedPreferences

    private lateinit var viewModel: LoginViewModel

    private lateinit var scenario: FragmentScenario<LoginFragment>

    private val instrumentationRegistry = InstrumentationRegistry.getInstrumentation()

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        context = instrumentationRegistry.targetContext
        keyStoreWrapper = KeyStoreWrapper(context)
        sharedPreferences = BankSharedPreferences(
            context,
            cipherWrapper,
            keyStoreWrapper
        )
        viewModel = mock(LoginViewModel::class.java)

        scenario = launchFragmentInContainer {
            LoginFragment().apply {
                viewModelFactory = ViewModelUtil.createFor(viewModel)
            }
        }

        clearSharedPreferences()

        scenario.onFragment { fragment ->
            Navigation.setViewNavController(fragment.requireView(), navController)
        }

        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
    }

    @After
    fun destroy(){
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
        clearSharedPreferences()
    }

    @Test
    fun givenLoginFragment_checkViewsDisplayed() {
        onView(withId(R.id.login_img_bank)).check(matches(isDisplayed()))
        onView(withId(R.id.login_edt_user)).check(matches(isDisplayed()))
        onView(withId(R.id.login_edt_password)).check(matches(isDisplayed()))
        onView(withId(R.id.login_btn_login)).check(matches(isDisplayed()))
        onView(withId(R.id.login_progress)).check(matches(not(isDisplayed())))
    }

    @Test
    fun givenLoginFragment_checkViewsVisible() {
        onView(withId(R.id.login_img_bank)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
        onView(withId(R.id.login_edt_user)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
        onView(withId(R.id.login_edt_password)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
        onView(withId(R.id.login_btn_login)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
        onView(withId(R.id.login_progress)).check(matches(not(withEffectiveVisibility(Visibility.VISIBLE))))
    }

    @Test
    fun givenLoginFragment_checkViewsEnabled() {
        onView(withId(R.id.login_edt_user)).check(matches(isEnabled()))
        onView(withId(R.id.login_edt_password)).check(matches(isEnabled()))
        onView(withId(R.id.login_btn_login)).check(matches(isEnabled()))
    }

    @Test
    fun givenLoginFragment_checkViewsClickable() {
        onView(withId(R.id.login_btn_login)).check(matches(isClickable()))
    }

    @Test
    fun givenLoginClicked_whenNetworkAvailableAndValidEmail_thenNavigateToStatements() {
        scenario.onFragment {
            it.networkAvailable = true
        }
        onView(withId(R.id.login_edt_user)).perform(typeText("felipegouveia3@gmail.com"), closeSoftKeyboard())
        onView(withId(R.id.login_edt_password)).perform(typeText("Test@1"), closeSoftKeyboard())
        closeSoftKeyboard()
        onView(withId(R.id.login_btn_login)).perform(click())
        verify(navController).navigate(
            LoginFragmentDirections.actionLoginFragmentToStatementsFragment(UserAccountPresentation(1))
        )
    }

    @Test
    fun givenLoginClicked_whenNetworkAvailableAndValidCpf_thenNavigateToStatements() {
        scenario.onFragment {
            it.networkAvailable = true
        }
        onView(withId(R.id.login_edt_user)).perform(typeText("002.169.232-71"), closeSoftKeyboard())
        onView(withId(R.id.login_edt_password)).perform(typeText("Test@1"), closeSoftKeyboard())
        closeSoftKeyboard()
        onView(withId(R.id.login_btn_login)).perform(click())
        verify(navController).navigate(
            LoginFragmentDirections.actionLoginFragmentToStatementsFragment(UserAccountPresentation(1))
        )
    }

    @Test
    fun givenAppOpened_whenLastUserExists_thenShowLastUser() {
        val lastUser = "José da Silva Teste"
        sharedPreferences.saveEncryptedString(LoginFragment.PREFS_LOGIN_KEY, lastUser)
        scenario.onFragment {
            it.retrieveLastUser()
        }
        onView(withId(R.id.login_txt_last_user)).check(matches(withText(context.getString(R.string.login_last_user, lastUser))))
    }

    @Test
    fun givenAppOpened_whenLastUserNotExists_thenDontShowLastUser() {
        onView(withId(R.id.login_txt_last_user)).check(matches(withText(isEmptyString())))
    }


    @Test
    fun givenLoginClicked_whenNetworkUnavailable_thenShowErrorMessage() {
        scenario.onFragment {
            it.networkAvailable = false
        }
        onView(withId(R.id.login_edt_user)).perform(typeText("felipegouveia3@gmail.com") , closeSoftKeyboard())
        onView(withId(R.id.login_edt_password)).perform(typeText("Test@1"), closeSoftKeyboard())
        onView(withId(R.id.login_btn_login)).perform(click())
        onView(withText(R.string.login_error_network_unavailable)).inRoot(ToastMatcher()).check(matches(isDisplayed()))
    }

    @Test
    fun givenLoginClicked_whenBadUserCredential_thenShowErrorMessage() {
        scenario.onFragment {
            it.networkAvailable = false
        }
        onView(withId(R.id.login_edt_user)).perform(typeText("felipegouveia3") , closeSoftKeyboard())
        onView(withId(R.id.login_edt_password)).perform(typeText("Test@1"), closeSoftKeyboard())
        onView(withId(R.id.login_btn_login)).perform(click())
        onView(withText(R.string.login_error_bad_user)).inRoot(ToastMatcher()).check(matches(isDisplayed()))
    }

    @Test
    fun givenLoginClicked_whenBadPasswordCredential_thenShowErrorMessage() {
        scenario.onFragment {
            it.networkAvailable = false
        }
        onView(withId(R.id.login_edt_user)).perform(typeText("felipegouveia3@gmail.com") , closeSoftKeyboard())
        onView(withId(R.id.login_edt_password)).perform(typeText("Tes"), closeSoftKeyboard())
        onView(withId(R.id.login_btn_login)).perform(click())
        onView(withText(R.string.login_error_bad_password)).inRoot(ToastMatcher()).check(matches(isDisplayed()))
    }

    @Test
    fun givenLoginClickedWithEmptyCredentials_thenDoNothing() {
        onView(withId(R.id.login_edt_user)).perform(replaceText(""))
        onView(withId(R.id.login_edt_password)).perform(replaceText(""))
        onView(withId(R.id.login_btn_login)).perform(click())
    }

    private fun clearSharedPreferences(){
        context
            .getSharedPreferences(BankSharedPreferences.BANK_PREFERENCES, Context.MODE_PRIVATE)
            .edit()
            .clear()
            .commit()
    }

}