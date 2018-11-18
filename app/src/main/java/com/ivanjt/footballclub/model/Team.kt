package com.ivanjt.footballclub.model

import com.google.gson.annotations.SerializedName

data class Team(
    @SerializedName("idTeam")
    var id: String?,

    @SerializedName("strTeam")
    var name: String?,

    @SerializedName("strTeamBadge")
    var badge: String?
)