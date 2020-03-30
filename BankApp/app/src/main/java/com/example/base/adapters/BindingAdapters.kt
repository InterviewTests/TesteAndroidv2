package com.example.base.adapters

import android.view.View
import androidx.databinding.BindingAdapter

object BindingAdapters {

    @JvmStatic
    @BindingAdapter("visible")
    fun setVisible(view: View, visibility:Boolean){
        view.visibility = if (visibility) View.VISIBLE else View.GONE
    }
}