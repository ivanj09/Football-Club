package com.ivanjt.footballclub.api

import android.net.Uri
import com.ivanjt.footballclub.BuildConfig

object SportDbAPI {
    private const val API = "api"
    private const val VERSION = "v1"
    private const val FORMAT = "json"
    private const val QUERY_LEAGUE_PARAM = "l"
    private const val QUERY_ID_LEAGUE_PARAM = "id"
    private const val SEARCH_ALL_TEAMS_BY_LEAGUE = "search_all_teams.php"
    private const val SEARCH_ALL_LEAGUES = "all_leagues.php"
    private const val LOOKUP_EVENT = "lookupevent.php"
    private const val LOOKUP_TEAM = "lookupteam.php"
    private const val SEARCH_15_LAST_MATCHES = "eventspastleague.php"
    private const val SEARCH_15_NEXT_MATCHES = "eventsnextleague.php"

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

    fun getTeam(idTeam: String?): String {
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
            .appendPath(API)
            .appendPath(VERSION)
            .appendPath(FORMAT)
            .appendPath(BuildConfig.TSDB_API_KEY)
            .appendPath(LOOKUP_TEAM)
            .appendQueryParameter(QUERY_ID_LEAGUE_PARAM, idTeam)
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

    fun getNextMatches(leagueId: String): String {
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
            .appendPath(API)
            .appendPath(VERSION)
            .appendPath(FORMAT)
            .appendPath(BuildConfig.TSDB_API_KEY)
            .appendPath(SEARCH_15_NEXT_MATCHES)
            .appendQueryParameter(QUERY_ID_LEAGUE_PARAM, leagueId)
            .build().toString()
    }

    fun getLastMatches(leagueId: String): String {
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
            .appendPath(API)
            .appendPath(VERSION)
            .appendPath(FORMAT)
            .appendPath(BuildConfig.TSDB_API_KEY)
            .appendPath(SEARCH_15_LAST_MATCHES)
            .appendQueryParameter(QUERY_ID_LEAGUE_PARAM, leagueId)
            .build().toString()
    }

    fun getMatchDetail(idEvent: String): String {
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
            .appendPath(API)
            .appendPath(VERSION)
            .appendPath(FORMAT)
            .appendPath(BuildConfig.TSDB_API_KEY)
            .appendPath(LOOKUP_EVENT)
            .appendQueryParameter(QUERY_ID_LEAGUE_PARAM, idEvent)
            .build().toString()
    }
}