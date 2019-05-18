package com.br.projetotestesantanter.api.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Statement (
    @SerializedName("title")
    var title : String,

    @SerializedName("desc")
    var desc : String,

    @SerializedName("date")
    var date : String,

    @SerializedName("value")
    var value : Float
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readFloat()
    )


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(desc)
        parcel.writeString(date)
        parcel.writeFloat(value)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Statement> {
        override fun createFromParcel(parcel: Parcel): Statement {
            return Statement(parcel)
        }

        override fun newArray(size: Int): Array<Statement?> {
            return arrayOfNulls(size)
        }
    }
}




