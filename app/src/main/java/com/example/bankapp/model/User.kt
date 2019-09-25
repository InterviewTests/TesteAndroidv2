package com.example.bankapp.model

import android.os.Parcel
import android.os.Parcelable


class User(var userId: Int? = 0,
           var name: String? = "",
           var bankAccount: String? = "",
           var agency: String? = "",
           var balance: Double? = 0.0) : Parcelable {


    override fun writeToParcel(parcel: Parcel?, p1: Int) {
        parcel?.writeValue(userId)
        parcel?.writeString(name)
        parcel?.writeString(bankAccount)
        parcel?.writeString(agency)
        parcel?.writeValue(balance)
    }

    override fun describeContents(): Int {
        return 0
    }

    constructor(parcel: Parcel) : this() {
        userId = parcel.readValue(Int::class.java.classLoader) as Int
        name = parcel.readString()
        bankAccount = parcel.readString()
        agency = parcel.readString()
        balance = parcel.readValue(Double::class.java.classLoader) as Double
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }
}
