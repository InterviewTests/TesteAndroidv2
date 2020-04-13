package pt.felipegouveia.bankapp.presentation.login.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserAccountPresentation(
    val userId: Int? = -1,
    val name: String? = null,
    val bankAccount: String? = null,
    val agency: String? = null,
    val balance: String? = null
): Parcelable

