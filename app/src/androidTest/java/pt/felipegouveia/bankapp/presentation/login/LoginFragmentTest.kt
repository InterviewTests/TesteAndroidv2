package pt.felipegouveia.bankapp.presentation.login

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import pt.felipegouveia.bankapp.R
import pt.felipegouveia.bankapp.util.ViewModelUtil

@RunWith(AndroidJUnit4::class)
class LoginFragmentTest {

    @Mock
    lateinit var navController: NavController

    private lateinit var viewModel: LoginViewModel

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        viewModel = mock(LoginViewModel::class.java)

        val scenario = launchFragmentInContainer(
            LoginFragmentArgs(1).toBundle()) {
            LoginFragment().apply {
                viewModelFactory = ViewModelUtil.createFor(viewModel)
            }
        }

        scenario.onFragment { fragment ->
            Navigation.setViewNavController(fragment.requireView(), navController)
        }
    }

    @Test
    fun giveLoginFragment_CheckViewsDisplayed() {
        onView(withId(R.id.login_txt_test)).check(matches(isDisplayed()))
        onView(withId(R.id.login_txt_test)).check(matches(isDisplayed()))
        onView(withId(R.id.login_txt_test)).check(matches(isDisplayed()))
        onView(withId(R.id.login_txt_test)).check(matches(isDisplayed()))
    }

    @Test
    fun giveLoginFragment_CheckViewsVisible() {
        onView(withId(R.id.login_img_bank)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
        onView(withId(R.id.login_img_bank)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
        onView(withId(R.id.login_img_bank)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
        onView(withId(R.id.login_img_bank)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
    }

    @Test
    fun giveLoginFragment_CheckViewsEnabled() {
        onView(withId(R.id.login_img_bank)).check(matches(isEnabled()))
        onView(withId(R.id.login_edt_user)).check(matches(isEnabled()))
        onView(withId(R.id.login_edt_password)).check(matches(isEnabled()))
        onView(withId(R.id.login_btn_login)).check(matches(isEnabled()))
    }

    @Test
    fun giveLoginFragment_CheckViewsClickable() {
        onView(withId(R.id.login_btn_login)).check(matches(isClickable()))
    }
}