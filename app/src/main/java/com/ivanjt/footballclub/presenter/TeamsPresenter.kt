package com.ivanjt.footballclub.presenter

import com.google.gson.Gson
import com.ivanjt.footballclub.CoroutineContextProvider
import com.ivanjt.footballclub.api.SportDbAPI
import com.ivanjt.footballclub.model.LeagueResponse
import com.ivanjt.footballclub.model.TeamResponse
import com.ivanjt.footballclub.utility.NetworkUtil
import com.ivanjt.footballclub.view.TeamsView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TeamsPresenter(
    private val view: TeamsView,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {
    fun getTeamList(league: String?) {
        view.showLoading()
        GlobalScope.launch(context.main) {
            val data =
                gson.fromJson(NetworkUtil.doRequest(SportDbAPI.getTeams(league)).await(), TeamResponse::class.java)

            view.hideLoading()
            view.showTeamList(data.teams)
        }
    }

    fun getLeagueList() {
        view.showLoading()
        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                NetworkUtil.doRequest(SportDbAPI.getLeagues()).await(),
                LeagueResponse::class.java
            )

            //Remove league if type of league is not soccer sport type
            data.leagues = data.leagues.filter { league -> league.sportType == "Soccer" }

            view.hideLoading()
            view.showLeagueList(data.leagues)
        }
    }
}