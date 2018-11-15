package com.ivanjt.footballclub.API

import android.net.Uri
import com.ivanjt.footballclub.BuildConfig

object SportDbAPI {
    private val API = "api"
    private val VERSION = "v1"
    private val FORMAT = "json"
    private val QUERY_LEAGUE_PARAM = "l"
    private val SEARCH_ALL_TEAMS_BY_LEAGUE = "search_all_teams.php"
    private val SEARCH_ALL_LEAGUES = "all_leagues.php"

    fun getTeams(league: String?): String {
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
            .appendPath(API)
            .appendPath(VERSION)
            .appendPath(FORMAT)
            .appendPath(BuildConfig.TSDB_API_KEY)
            .appendPath(SEARCH_ALL_TEAMS_BY_LEAGUE)
            .appendQueryParameter(QUERY_LEAGUE_PARAM, league)
            .build().toString()
    }

    fun getLeagues(): String {
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
            .appendPath(API)
            .appendPath(VERSION)
            .appendPath(FORMAT)
            .appendPath(BuildConfig.TSDB_API_KEY)
            .appendPath(SEARCH_ALL_LEAGUES)
            .build().toString()
    }
}