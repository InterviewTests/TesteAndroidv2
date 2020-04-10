package pt.felipegouveia.bankapp.presentation.login

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingResource
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.Matchers.empty
import org.hamcrest.Matchers.not
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import pt.felipegouveia.bankapp.R
import pt.felipegouveia.bankapp.util.ViewModelUtil
import java.lang.Thread.sleep

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
            LoginFragmentArgs(1).toBundle()
        ) {
            LoginFragment().apply {
                viewModelFactory = ViewModelUtil.createFor(viewModel)
            }
        }

        scenario.onFragment { fragment ->
            Navigation.setViewNavController(fragment.requireView(), navController)
        }
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
    fun givenLoginClicked_thenNavigateToStatements() {
        onView(withId(R.id.login_edt_user)).perform(replaceText("felipegouveia3@gmail.com"))
        onView(withId(R.id.login_edt_password)).perform(replaceText("Test@1"))
        onView(withId(R.id.login_btn_login)).perform(click())
        sleep(1000)
        onView(withId(R.id.login_progress)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
        sleep(5000)
        onView(withId(R.id.login_progress)).check(matches(withEffectiveVisibility(Visibility.GONE)))
        verify(navController).navigate(
            LoginFragmentDirections.actionLoginFragmentToStatementsFragment(1)
        )
    }

    @Test
    fun givenLoginClickedWithEmptyCredentials_thenDoNothing() {
        onView(withId(R.id.login_edt_user)).perform(replaceText(""))
        onView(withId(R.id.login_edt_password)).perform(replaceText(""))
        onView(withId(R.id.login_btn_login)).perform(click())
        //verifyNoMoreInteractions()
    }
}