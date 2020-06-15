package com.qintess.santanderapp.model

import android.os.Parcel
import android.os.Parcelable

data class UserModel (val userId: Int,
                      val name: String,
                      val bankAccount: String,
                      val agency: String,
                      val balance: Double): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readDouble()
    ) {
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeInt(userId)
        dest?.writeString(name)
        dest?.writeString(bankAccount)
        dest?.writeString(agency)
        dest?.writeDouble(balance)
    }
    override fun describeContents(): Int { return 0 }

    companion object CREATOR : Parcelable.Creator<UserModel> {
        override fun createFromParcel(parcel: Parcel): UserModel {
            return UserModel(parcel)
        }

        override fun newArray(size: Int): Array<UserModel?> {
            return arrayOfNulls(size)
        }
    }
}