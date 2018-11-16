package com.ivanjt.footballclub.Model

import com.google.gson.annotations.SerializedName

data class Event(
    @SerializedName("idEvent")
    var id: String?,

    @SerializedName("strEvent")
    var event: String?,

    @SerializedName("strSport")
    var sportType: String?,

    @SerializedName("strLeague")
    var leagueName: String?,

    @SerializedName("strHomeTeam")
    var homeTeam: String?,

    @SerializedName("strAwayTeam")
    var awayTeam: String?,

    @SerializedName("intHomeScore")
    var homeScore: String?,

    @SerializedName("intAwayScore")
    var awayScore: String?,

    @SerializedName("dateEvent")
    var date: String?
)
