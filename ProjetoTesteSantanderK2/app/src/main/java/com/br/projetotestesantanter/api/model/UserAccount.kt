package com.br.projetotestesantanter.api.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class UserAccount(

    @SerializedName("userId")
    var userId : Int,

     @SerializedName("name")
     var name : String,

    @SerializedName("bankAccount")
    var bankAccount : String,

    @SerializedName("agency")
    var agency : String,

    @SerializedName("balance")
    var balance : Float
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readFloat()
    )


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(userId)
        parcel.writeString(name)
        parcel.writeString(bankAccount)
        parcel.writeString(agency)
        parcel.writeFloat(balance)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UserAccount> {
        override fun createFromParcel(parcel: Parcel): UserAccount {
            return UserAccount(parcel)
        }

        override fun newArray(size: Int): Array<UserAccount?> {
            return arrayOfNulls(size)
        }
    }

}