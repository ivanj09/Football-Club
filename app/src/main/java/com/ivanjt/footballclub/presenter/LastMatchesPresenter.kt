package com.ivanjt.footballclub.presenter

import com.google.gson.Gson
import com.ivanjt.footballclub.CoroutineContextProvider
import com.ivanjt.footballclub.api.SportDbAPI
import com.ivanjt.footballclub.model.EventResponse
import com.ivanjt.footballclub.model.LeagueResponse
import com.ivanjt.footballclub.utility.NetworkUtil
import com.ivanjt.footballclub.view.LastMatchesView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LastMatchesPresenter(
    private val view: LastMatchesView,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {
    fun getLastMatches(leagueId: String) {
        view.showLoading()

        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                NetworkUtil.doRequest(SportDbAPI.getLastMatches(leagueId)).await(),
                EventResponse::class.java
            )

            //Remove match if sport type of match is not soccer sport type
            data.events = data.events.filter { event -> event.sportType == "Soccer" }

            view.hideLoading()
            view.showLastMatches(data.events)
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