package pt.felipegouveia.bankapp.presentation.statements

import androidx.annotation.StringRes
import androidx.fragment.app.testing.FragmentScenario
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mock
import org.mockito.Mockito.*
import pt.felipegouveia.bankapp.R
import pt.felipegouveia.bankapp.presentation.MainActivity
import pt.felipegouveia.bankapp.presentation.entity.Error
import pt.felipegouveia.bankapp.presentation.entity.Response
import pt.felipegouveia.bankapp.presentation.login.LoginFragment
import pt.felipegouveia.bankapp.presentation.statements.entity.StatementsPresentation
import pt.felipegouveia.bankapp.util.EspressoIdlingResource
import pt.felipegouveia.bankapp.util.ViewModelUtil

@RunWith(AndroidJUnit4::class)
class StatementsFragmentTest {
    @Rule
    @JvmField
    val activityRule = ActivityTestRule(MainActivity::class.java, true, true)

    @Mock
    lateinit var navController: NavController

    private val statementsLiveData = MutableLiveData<Response<StatementsPresentation>>()

    private lateinit var viewModel: StatementsViewModel

    private lateinit var scenario: FragmentScenario<LoginFragment>

    private val statementsFragment = TestRepoFragment().apply {
        arguments = StatementsFragmentArgs.Builder("a", "b").build().toBundle()
    }

    @Before
    fun init() {
        viewModel = mock(StatementsViewModel::class.java)
        doNothing().`when`(viewModel).setUserId(anyInt())
        `when`(viewModel.statements).thenReturn(statementsLiveData)

        statementsFragment.viewModelFactory = ViewModelUtil.createFor(viewModel)

        scenario.onFragment { fragment ->
            Navigation.setViewNavController(fragment.requireView(), navController)
        }

        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)

        EspressoTestUtil.disableProgressBarAnimations(activityRule)
    }

    @Test
    fun testLoading() {
        repoLiveData.postValue(Resource.loading(null))
        onView(withId(R.id.progress_bar)).check(matches(isDisplayed()))
        onView(withId(R.id.retry)).check(matches(not(isDisplayed())))
    }

    @Test
    fun testValueWhileLoading() {
        val repo = TestUtil.createRepo("yigit", "foo", "foo-bar")
        statementsLiveData.postValue(Resource.loading(repo))
        onView(withId(R.id.progress_bar)).check(matches(not(isDisplayed())))
        onView(withId(R.id.name)).check(
            matches(
                withText(getString(R.string.repo_full_name, "yigit", "foo"))
            )
        )
        onView(withId(R.id.description)).check(matches(withText("foo-bar")))
    }

    @Test
    fun testLoaded() {
        val repo = TestUtil.createRepo("foo", "bar", "buzz")
        repoLiveData.postValue(Resource.success(repo))
        onView(withId(R.id.progress_bar)).check(matches(not(isDisplayed())))
        onView(withId(R.id.name)).check(
            matches(
                withText(getString(R.string.repo_full_name, "foo", "bar"))
            )
        )
        onView(withId(R.id.description)).check(matches(withText("buzz")))
    }

    @Test
    fun testError() {
        statementsLiveData.postValue(Response(data = , error = Error(message = "error")))
        onView(withId(R.id.progress_bar)).check(matches(not(isDisplayed())))
        onView(withId(R.id.retry)).check(matches(isDisplayed()))
        onView(withId(R.id.retry)).perform(click())
        verify(viewModel).retry()
        repoLiveData.postValue(Resource.loading(null))

        onView(withId(R.id.progress_bar)).check(matches(isDisplayed()))
        onView(withId(R.id.retry)).check(matches(not(isDisplayed())))
        val repo = TestUtil.createRepo("owner", "name", "desc")
        repoLiveData.postValue(Resource.success(repo))

        onView(withId(R.id.progress_bar)).check(matches(not(isDisplayed())))
        onView(withId(R.id.retry)).check(matches(not(isDisplayed())))
        onView(withId(R.id.name)).check(
            matches(
                withText(getString(R.string.repo_full_name, "owner", "name"))
            )
        )
        onView(withId(R.id.description)).check(matches(withText("desc")))
    }

    @Test
    fun testContributors() {
        setStatements("aa", "bb")
        onView(listMatcher().atPosition(0))
            .check(matches(hasDescendant(withText("aa"))))
        onView(listMatcher().atPosition(1))
            .check(matches(hasDescendant(withText("bb"))))
    }

    private fun listMatcher(): RecyclerViewMatcher {
        return RecyclerViewMatcher(R.id.contributor_list)
    }

    @Test
    fun testContributorClick() {
        setStatements("aa", "bb", "cc")
        onView(withText("cc")).perform(click())
        verify(repoFragment.navController).navigate(
            RepoFragmentDirections.showUser("cc").matcher()
        )
    }

    @Test
    fun nullRepo() {
        statementsLiveData.postValue(null)
        onView(withId(R.id.name)).check(matches(not(isDisplayed())))
    }

    @Test
    fun nullContributors() {
        setStatements("a", "b", "c")
        onView(listMatcher().atPosition(0)).check(matches(hasDescendant(withText("a"))))
        contributorsLiveData.postValue(null)
        onView(listMatcher().atPosition(0)).check(doesNotExist())
    }

    private fun setStatements(vararg names: String) {
        val repo = TestUtil.createRepo("foo", "bar", "desc")
        val statements = names.mapIndexed { index, name ->
            TestUtil.createContributor(
                repo = repo,
                login = name,
                contributions = 100 - index
            )
        }
        statementsLiveData.postValue(Resource.success(statements))
    }

    private fun getString(@StringRes id: Int, vararg args: Any): String {
        return InstrumentationRegistry.targetContext.getString(id, *args)
    }
}