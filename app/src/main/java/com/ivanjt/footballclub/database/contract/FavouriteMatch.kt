package com.ivanjt.footballclub.database.contract

data class FavouriteMatch(
    val id: Long?,
    val eventId: String?,
    val homeTeamName: String?,
    val homeTeamBadge: String?,
    val homeScore: String?,
    val awayTeamName: String?,
    val awayTeamBadge: String?,
    val awayScore: String?,
    val date: String?,
    val time: String?
) {
    companion object {
        const val TABLE_NAME = "favourite_match"
        const val ID = "id"
        const val EVENT_ID = "event_id"
        const val HOME_TEAM_NAME = "home_team_name"
        const val HOME_TEAM_BADGE = "home_team_badge"
        const val HOME_SCORE = "home_score"
        const val AWAY_TEAM_NAME = "away_team_name"
        const val AWAY_TEAM_BADGE = "away_team_badge"
        const val AWAY_SCORE = "away_score"
        const val DATE = "date"
        const val TIME = "time"
    }
}