package com.nandoligeiro.safrando.presentation.login.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UiLoginModel(
    val id: Int,
    val name: String = "",
    val agency: String = "",
    val account: String = "",
    val balance: String = "",
): Parcelable
