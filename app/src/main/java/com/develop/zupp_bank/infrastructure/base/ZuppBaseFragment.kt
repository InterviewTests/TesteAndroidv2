package com.develop.zupp_bank.infrastructure.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner

open class ZuppBaseFragment<T : ZuppBaseActivity<*>> : Fragment() {
    lateinit var activity: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity = getActivity() as T
    }

    fun getLifeCycleView(): Lifecycle {
        return lifecycle
    }

    fun getLifeCycleOwnerView(): LifecycleOwner {
        return this
    }

    fun getActivityView(): FragmentActivity {
        return activity
    }
}