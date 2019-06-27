package com.example.desafiosantander.rule

import android.app.Activity
import androidx.test.core.app.ActivityScenario
import com.orhanobut.hawk.Hawk
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.rules.ExternalResource
import org.junit.rules.RuleChain
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement
import org.koin.test.KoinTest

class TestRule<T : Activity>(
    private val mActivity: Class<T>,
    private val mDeclareMock: () -> Unit = {}
) : TestRule, KoinTest {

    val serverRule = MockWebServer()
    private lateinit var activityScenario: ActivityScenario<T>

    override fun apply(base: Statement?, description: Description?): Statement {
        return RuleChain
            .outerRule(serverRule)
            .around(KoinRule())
            .apply(base, description)
    }

    inner class KoinRule : ExternalResource() {
        override fun before() {
            super.before()
            Hawk.deleteAll()
            mDeclareMock()
            activityScenario = ActivityScenario.launch(mActivity)
        }

        override fun after() {
            super.after()
            activityScenario.close()
            serverRule.shutdown()
            Hawk.deleteAll()
        }
    }

    fun mockResponse(mockJson: String) {
        serverRule.enqueue(MockResponse().setBody(mockJson))
    }
}