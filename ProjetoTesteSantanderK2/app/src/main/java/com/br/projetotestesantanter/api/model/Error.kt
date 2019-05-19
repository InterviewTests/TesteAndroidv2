package com.br.projetotestesantanter.api.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Error (
    @SerializedName("error")
    var error : String? = null
) : Parcelable {
    constructor(parcel: Parcel) : this(parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(error)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Error> {
        override fun createFromParcel(parcel: Parcel): Error {
            return Error(parcel)
        }

        override fun newArray(size: Int): Array<Error?> {
            return arrayOfNulls(size)
        }
    }

}
