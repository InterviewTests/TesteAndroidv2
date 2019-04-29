package br.com.alex.bankappchallenge.rules

import android.app.Activity
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ActivityScenario
import br.com.alex.bankappchallenge.di.PROPERTY_BASE_URL
import br.com.alex.bankappchallenge.di.androidModule
import br.com.alex.bankappchallenge.di.interactorModule
import br.com.alex.bankappchallenge.di.mapperModule
import br.com.alex.bankappchallenge.di.networkModule
import br.com.alex.bankappchallenge.di.reducerModule
import br.com.alex.bankappchallenge.di.repositoryModule
import br.com.alex.bankappchallenge.di.viewModelModule
import com.orhanobut.hawk.Hawk
import okhttp3.mockwebserver.MockWebServer
import org.junit.rules.ExternalResource
import org.junit.rules.RuleChain
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest

class InstrumentedTestRule<T : Activity>(
    private val activity: Class<T>
) : TestRule, KoinTest {

    val serverRule = MockWebServer()
    private lateinit var activityScenario: ActivityScenario<T>

    override fun apply(base: Statement?, description: Description?): Statement {
        return RuleChain
            .outerRule(serverRule)
            .around(StartKoinRule())
            .apply(base, description)
    }

    inner class StartKoinRule : ExternalResource() {
        override fun before() {
            super.before()
            Hawk.deleteAll()
            serverRule.start()
            startKoin {
                modules(
                    networkModule,
                    viewModelModule,
                    androidModule,
                    interactorModule,
                    reducerModule,
                    repositoryModule,
                    mapperModule
                )

                properties(mapOf(
                    PROPERTY_BASE_URL to serverRule.url("/").toString()
                ))
            }

            activityScenario = ActivityScenario.launch(activity)
        }

        override fun after() {
            super.after()
            activityScenario.close()
            serverRule.shutdown()
            stopKoin()
            Hawk.deleteAll()
        }
    }
}