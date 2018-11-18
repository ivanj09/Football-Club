package com.ivanjt.footballclub.database.contract

data class Favourite(
    val id: Long?,
    val homeTeamName: String?,
    val homeTeamBadge: String?,
    val homeScore: String?,
    val awayTeamName: String?,
    val awayTeamBadge: String?,
    val awayScore: String?,
    val date: String?
) {
    companion object {
        const val TABLE_NAME = "favourites"
        const val ID = "id"
        const val HOME_TEAM_NAME = "home_team_name"
        const val HOME_TEAM_BADGE = "home_team_badge"
        const val HOME_SCORE = "home_score"
        const val AWAY_TEAM_NAME = "away_team_name"
        const val AWAY_TEAM_BADGE = "away_team_badge"
        const val AWAY_SCORE = "away_score"
        const val DATE = "date"
    }
}