package com.qintess.santanderapp.model

import android.annotation.SuppressLint
import android.os.Parcel
import android.os.Parcelable

@SuppressLint("ParcelCreator")
data class UserModel (val userId: Int,
                      val name: String,
                      val bankAccount: String,
                      val agency: String,
                      val balance: Double): Parcelable {
    override fun writeToParcel(dest: Parcel?, flags: Int) { }
    override fun describeContents(): Int { return 0 }
}