package com.example.testeandroideveris.feature.login.data

import android.os.Parcel
import android.os.Parcelable
import com.example.testeandroideveris.feature.util.BankUtil.formatAccountNumber
import com.example.testeandroideveris.feature.util.BankUtil.formatAmountValue

data class UserAccount (val userId: Int,
                    val name: String,
                    val bankAccount: String,
                    val agency: String,
                    val balance: Double): Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readDouble()
    ) {
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        with(dest) {
            writeInt(userId)
            writeString(name)
            writeString(bankAccount)
            writeString(agency)
            writeDouble(balance)
        }
    }

    fun getFormattedBankAccount() = formatAccountNumber(agency, bankAccount)

    fun getFormattedBalance() = formatAmountValue(balance)

    override fun describeContents() = 0

    companion object CREATOR : Parcelable.Creator<UserAccount> {
        override fun createFromParcel(parcel: Parcel): UserAccount {
            return UserAccount(parcel)
        }

        override fun newArray(size: Int): Array<UserAccount?> {
            return arrayOfNulls(size)
        }
    }
}