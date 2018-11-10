package br.com.santander.android.bank.core.extensions

import android.app.Activity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE
import br.com.santander.android.bank.core.progress.LoadingFragment

fun Activity.showLoadingFragment(container: Int, manager: FragmentManager) {
    manager.beginTransaction()
        .add(container, LoadingFragment.start(), LoadingFragment.TAG)
        .addToBackStack(LoadingFragment.TAG)
        .commit()
}

fun Activity.hideLoadingFragment(manager: FragmentManager) {
    manager.popBackStack(LoadingFragment.TAG, POP_BACK_STACK_INCLUSIVE)
}