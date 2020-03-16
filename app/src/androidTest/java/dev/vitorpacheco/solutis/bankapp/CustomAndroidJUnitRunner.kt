package dev.vitorpacheco.solutis.bankapp

import android.app.KeyguardManager
import android.content.Context.KEYGUARD_SERVICE
import android.os.Bundle
import androidx.test.runner.AndroidJUnitRunner
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry
import androidx.test.runner.lifecycle.Stage

class CustomAndroidJUnitRunner : AndroidJUnitRunner() {

    override fun onCreate(arguments: Bundle?) {
        super.onCreate(arguments)

        val app = targetContext.applicationContext

        val keyguard = (app.getSystemService(KEYGUARD_SERVICE) as KeyguardManager)

        ActivityLifecycleMonitorRegistry.getInstance().addLifecycleCallback { activity, stage ->
            if (stage == Stage.PRE_ON_CREATE) {
                keyguard.requestDismissKeyguard(activity, null)
            }
        }
    }

}