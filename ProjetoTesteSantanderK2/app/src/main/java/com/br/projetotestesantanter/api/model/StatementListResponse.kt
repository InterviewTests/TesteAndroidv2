package com.br.projetotestesantanter.api.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class StatementListResponse (
    @SerializedName("statementList")
    var statementListResponse: List<Statement>
) : Parcelable {
    constructor(parcel: Parcel) : this(parcel.createTypedArrayList(Statement))


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeTypedList(statementListResponse)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<StatementListResponse> {
        override fun createFromParcel(parcel: Parcel): StatementListResponse {
            return StatementListResponse(parcel)
        }

        override fun newArray(size: Int): Array<StatementListResponse?> {
            return arrayOfNulls(size)
        }
    }
}
