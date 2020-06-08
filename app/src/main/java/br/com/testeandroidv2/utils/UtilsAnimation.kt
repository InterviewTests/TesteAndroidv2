package br.com.testeandroidv2.utils

import androidx.appcompat.app.AppCompatActivity

import br.com.testeandroidv2.R

object UtilsAnimation {
    /**
     * Method to animate activity Left to Right
     */
    fun leftToRight(activity: AppCompatActivity) {
        activity.overridePendingTransition(R.anim.lefttoright, R.anim.stable)
    }

    /**
     * Method to animate activity Right to Left
     */
    fun rightToLeft(activity: AppCompatActivity) {
        activity.overridePendingTransition(R.anim.righttoleft, R.anim.stable)
    }
}