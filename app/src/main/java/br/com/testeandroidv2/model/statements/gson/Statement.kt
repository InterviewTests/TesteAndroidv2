package br.com.testeandroidv2.model.statements.gson

import android.os.Parcelable

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

import kotlinx.android.parcel.Parcelize

@Parcelize
data class Statement(@SerializedName("statementList") @Expose var statementList: MutableList<StatementList>? = null,
                     @SerializedName("error")         @Expose var error: Error? = null
) : Parcelable