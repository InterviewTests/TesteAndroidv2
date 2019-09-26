package com.gustavo.bankandroid.extensions

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.verticalLinearLayout(context: Context) {
    val linearLayoutManager = LinearLayoutManager(context)
    linearLayoutManager.orientation = RecyclerView.VERTICAL
    this.layoutManager = linearLayoutManager
}
