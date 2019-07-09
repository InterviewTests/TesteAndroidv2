package br.com.learncleanarchitecture.login.presentation

import android.os.Parcel
import android.os.Parcelable

class Login(
    userId: Int? = 0,
    name: String? = "",
    bankAccount: String? = "",
    agency: String? = "",
    balance: Float? = 0F
) : Parcelable {

    var userId: Int? = 1
    var name: String? = ""
    var bankAccount: String? = ""
    var agency: String? = ""
    var balance: Float? = 0F

    private var username: String = ""
    private var pass: String = ""

    fun getUsername(): String {
        return username
    }

    fun getPass(): String {
        return pass
    }

    override fun toString(): String {
        return "Login(userId=$userId, name='$name', bankAccount='$bankAccount', agency='$agency', balance=$balance)"
    }

    fun setUsername(user: String) {
        this.username = user
    }

    fun setPass(pass: String) {
        this.pass = pass
    }

    //region Parcelable
    constructor(parcelIn: Parcel) : this(
    ) {
        userId = parcelIn.readValue(Int::class.java.classLoader) as? Int
        name = parcelIn.readString()
        bankAccount = parcelIn.readString()
        agency = parcelIn.readString()
        balance = parcelIn.readValue(Float::class.java.classLoader) as? Float
        username = parcelIn.readString()
        pass = parcelIn.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(userId)
        parcel.writeString(name)
        parcel.writeString(bankAccount)
        parcel.writeString(agency)
        parcel.writeValue(balance)
        parcel.writeString(username)
        parcel.writeString(pass)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Login> {
        override fun createFromParcel(parcel: Parcel): Login {
            return Login(parcel)
        }

        override fun newArray(size: Int): Array<Login?> {
            return arrayOfNulls(size)
        }
    }
    //endregion
}