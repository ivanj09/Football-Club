package com.ivanjt.footballclub.model

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

    @SerializedName("strHomeGoalDetails")
    var homeGoalDetails: String?,

    @SerializedName("strHomeLineupGoalkeeper")
    var homeLineupGoalkeeper: String?,

    @SerializedName("strHomeLineupDefense")
    var homeLineupDefense: String?,

    @SerializedName("strHomeLineupMidfield")
    var homeLineupMidfield: String?,

    @SerializedName("strHomeLineupForward")
    var homeLineupForward: String?,

    @SerializedName("strHomeLineupSubstitutes")
    var homeLineupSubstitutes: String?,

    @SerializedName("strAwayGoalDetails")
    var awayGoalDetails: String?,

    @SerializedName("strAwayLineupGoalkeeper")
    var awayLineupGoalkeeper: String?,

    @SerializedName("strAwayLineupDefense")
    var awayLineupDefense: String?,

    @SerializedName("strAwayLineupMidfield")
    var awayLineupMidfield: String?,

    @SerializedName("strAwayLineupForward")
    var awayLineupForward: String?,

    @SerializedName("strAwayLineupSubstitutes")
    var awayLineupSubstitutes: String?,

    @SerializedName("intHomeShots")
    var homeShots: String?,

    @SerializedName("intAwayShots")
    var awayShots: String?,

    @SerializedName("dateEvent")
    var date: String?,

    @SerializedName("idHomeTeam")
    var homeTeamId: String?,

    @SerializedName("idAwayTeam")
    var awayTeamId: String?,

    @SerializedName("strTime")
    var time: String?
)
