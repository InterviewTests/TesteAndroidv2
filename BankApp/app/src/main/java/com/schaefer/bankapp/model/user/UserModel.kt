package com.schaefer.bankapp.model.user

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class UserModel(
    @Expose
    @SerializedName("balance")
    var balance: Float,
    @Expose
    @SerializedName("userId")
    val userId: Int,
    @Expose
    @SerializedName("bankAccount")
    var bankAccount: String,
    @Expose
    @SerializedName("name")
    var name: String,
    @Expose
    @SerializedName("agency")
    var agency: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readFloat(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeFloat(balance)
        parcel.writeInt(userId)
        parcel.writeString(bankAccount)
        parcel.writeString(name)
        parcel.writeString(agency)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UserModel> {
        override fun createFromParcel(parcel: Parcel): UserModel {
            return UserModel(parcel)
        }

        override fun newArray(size: Int): Array<UserModel?> {
            return arrayOfNulls(size)
        }
    }


}