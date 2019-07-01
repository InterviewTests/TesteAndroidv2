package io.github.maikotrindade.bankapp.login.model

import android.os.Parcel
import android.os.Parcelable

data class LoginResponse(var userAccount: UserData?, var error: Error?)

data class UserData(
    var userId: Int,
    var name: String,
    var bankAccount: String,
    var agency: String,
    var balance: Double
) : Parcelable {
    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<UserData> = object : Parcelable.Creator<UserData> {
            override fun createFromParcel(source: Parcel): UserData = UserData(source)
            override fun newArray(size: Int): Array<UserData?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel) : this(
        source.readInt(),
        source.readString(),
        source.readString(),
        source.readString(),
        source.readDouble()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt(userId)
        writeString(name)
        writeString(bankAccount)
        writeString(agency)
        writeDouble(balance)
    }
}

data class Error(
    var code: Int,
    var message: String
)
