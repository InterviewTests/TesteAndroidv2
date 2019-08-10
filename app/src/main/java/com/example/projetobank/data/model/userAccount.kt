package com.example.projetobank.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class userAccount(
    @SerializedName("agency")
    val agency: String?,
    @SerializedName("balance")
    val balance: Double,
    @SerializedName("bankAccount")
    val bankAccount: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("userId")
    val userId: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readDouble(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(agency)
        parcel.writeDouble(balance)
        parcel.writeString(bankAccount)
        parcel.writeString(name)
        parcel.writeInt(userId)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<userAccount> {
        override fun createFromParcel(parcel: Parcel): userAccount {
            return userAccount(parcel)
        }

        override fun newArray(size: Int): Array<userAccount?> {
            return arrayOfNulls(size)
        }
    }
}