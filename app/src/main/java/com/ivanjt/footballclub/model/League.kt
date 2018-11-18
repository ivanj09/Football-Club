package com.ivanjt.footballclub.model

import com.google.gson.annotations.SerializedName

data class League(
    @SerializedName("idLeague")
    var id: String?,

    @SerializedName("strLeague")
    var name: String?,

    @SerializedName("strSport")
    var sportType: String?
)