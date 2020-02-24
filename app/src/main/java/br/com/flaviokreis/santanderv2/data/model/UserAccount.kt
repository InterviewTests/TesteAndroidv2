package br.com.flaviokreis.santanderv2.data.model

import android.os.Parcelable
import android.util.Log
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserAccount(
    val userId: Long,
    val name: String,
    val bankAccount: String,
    val agency: String,
    val balance: Double
) : Parcelable {
    fun getAccount() = "$bankAccount / ${agencyFormatted()}"

    private fun agencyFormatted(): String {
        try {
            return "${agency.substring(0, 2)}.${agency.substring(2, agency.lastIndex)}-" +
                "${agency.subSequence(agency.lastIndex, agency.lastIndex + 1)}"
        } catch (ex: StringIndexOutOfBoundsException) {
            Log.e("UserAccount", ex.localizedMessage ?: "")
        }
        return agency
    }
}