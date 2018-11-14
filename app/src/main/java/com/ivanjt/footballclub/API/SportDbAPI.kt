package com.ivanjt.footballclub.API

import android.net.Uri
import com.ivanjt.footballclub.BuildConfig

object SportDbAPI {
    private val API = "api"
    private val VERSION = "v1"
    private val FORMAT = "json"
    private val SEARCH_ALL_BY_LEAGUE = "search_all_teams.php"

    fun getTeams(league: String?): String {
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
            .appendPath(API)
            .appendPath(VERSION)
            .appendPath(FORMAT)
            .appendPath(BuildConfig.TSDB_API_KEY)
            .appendQueryParameter(SEARCH_ALL_BY_LEAGUE, league)
            .build().toString()
    }
}