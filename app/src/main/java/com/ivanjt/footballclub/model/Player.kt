package com.ivanjt.footballclub.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Player(
    @SerializedName("idPlayer")
    var idPlayer: String?,

    @SerializedName("strPlayer")
    var name: String?,

    @SerializedName("strDescriptionEN")
    var desc: String?,

    @SerializedName("strPosition")
    var position: String?,

    @SerializedName("strCutout")
    var cutOut: String?,

    @SerializedName("strThumb")
    var thumb: String?,

    @SerializedName("strHeight")
    var height: String?,

    @SerializedName("strWeight")
    var weight: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(idPlayer)
        parcel.writeString(name)
        parcel.writeString(desc)
        parcel.writeString(position)
        parcel.writeString(cutOut)
        parcel.writeString(thumb)
        parcel.writeString(height)
        parcel.writeString(weight)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Player> {
        override fun createFromParcel(parcel: Parcel): Player {
            return Player(parcel)
        }

        override fun newArray(size: Int): Array<Player?> {
            return arrayOfNulls(size)
        }
    }
}
