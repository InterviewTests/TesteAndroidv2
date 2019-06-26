package com.example.desafiosantander.runner

import android.app.Application
import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import androidx.test.runner.AndroidJUnitRunner
import com.example.desafiosantander.BankApplicationTest
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers

class BankTestRunner : AndroidJUnitRunner() {

    override fun onStart() {
        RxJavaPlugins.setInitComputationSchedulerHandler { Schedulers.from(AsyncTask.THREAD_POOL_EXECUTOR) }
        RxJavaPlugins.setInitIoSchedulerHandler { Schedulers.from(AsyncTask.THREAD_POOL_EXECUTOR) }
        super.onStart()
    }

    override fun onCreate(arguments: Bundle?) {
        super.onCreate(arguments)
    }

    override fun newApplication(cl: ClassLoader?, className: String?, context: Context?): Application {
        return super.newApplication(cl, BankApplicationTest::class.java.name, context)
    }

    override fun finish(resultCode: Int, results: Bundle?) {
        super.finish(resultCode, results)
    }
}