package com.ivanjt.footballclub.database.contract

data class FavouriteTeam(
    val id: Long?,
    val teamId: String?,
    val name: String?,
    val badge: String?,
    val year: String?,
    val stadium: String?,
    val description: String?
) {
    companion object {
        const val TABLE_NAME = "favourite_team"
        const val ID = "id"
        const val TEAM_ID = "team_id"
        const val NAME = "name"
        const val BADGE = "badge"
        const val YEAR = "year"
        const val STADIUM = "stadium"
        const val DESCRIPTION = "description"
    }
}