package dev.vitorpacheco.solutis.bankapp

import android.app.Activity
import android.app.KeyguardManager
import android.content.Context
import android.view.View
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry
import androidx.test.runner.lifecycle.Stage
import com.google.android.material.textfield.TextInputLayout
import dev.vitorpacheco.solutis.bankapp.BankApp.Companion.SHARED_PREFERENCES_KEY
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher

object EspressoHelper {

    fun getCurrentActivity(): Activity? {
        var currentActivity: Activity? = null

        InstrumentationRegistry.getInstrumentation().runOnMainSync {
            run {
                currentActivity = ActivityLifecycleMonitorRegistry.getInstance()
                    .getActivitiesInStage(Stage.RESUMED).elementAtOrNull(0)
            }
        }

        return currentActivity
    }

    fun clearSharedPreferences() {
        InstrumentationRegistry.getInstrumentation().runOnMainSync {
            run {
                val currentActivity = ActivityLifecycleMonitorRegistry.getInstance()
                    .getActivitiesInStage(Stage.RESUMED).elementAtOrNull(0)

                currentActivity?.getSharedPreferences(SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE)
                    ?.edit()?.clear()?.commit()
            }
        }
    }

    fun dismissKeyguard() {
        InstrumentationRegistry.getInstrumentation().runOnMainSync {
            run {
                val currentActivity = ActivityLifecycleMonitorRegistry.getInstance()
                    .getActivitiesInStage(Stage.RESUMED).elementAtOrNull(0)

                currentActivity?.let {
                    val keyguard = (it.application.getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager)
                    keyguard.requestDismissKeyguard(it, null)
                }
            }
        }
    }

    fun hasTextInputLayoutErrorText(expectedErrorText: String?): TypeSafeMatcher<View> {
        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description?) {
            }

            override fun matchesSafely(item: View?): Boolean {
                if (item !is TextInputLayout) return false

                val error = item.error ?: return false

                val hint = error.toString()

                return expectedErrorText == hint
            }

        }
    }

}