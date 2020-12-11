package br.com.testeandroidv2.model.statements.gson

import android.os.Parcelable

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

import kotlinx.android.parcel.Parcelize

@Parcelize
data class StatementList(@SerializedName("title") @Expose var title: String? = null,
                         @SerializedName("desc")  @Expose var desc: String? = null,
                         @SerializedName("date")  @Expose var date: String? = null,
                         @SerializedName("value") @Expose var value: Double? = null
) : Parcelable