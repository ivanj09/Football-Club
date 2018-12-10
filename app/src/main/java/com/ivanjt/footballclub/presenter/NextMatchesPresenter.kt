package com.ivanjt.footballclub.presenter

import com.google.gson.Gson
import com.ivanjt.footballclub.CoroutineContextProvider
import com.ivanjt.footballclub.api.SportDbAPI
import com.ivanjt.footballclub.model.EventResponse
import com.ivanjt.footballclub.model.LeagueResponse
import com.ivanjt.footballclub.utility.NetworkUtil
import com.ivanjt.footballclub.view.NextMatchesView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class NextMatchesPresenter(
    private val view: NextMatchesView,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {
    fun getNextMatches(leagueId: String) {
        view.showLoading()

        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                NetworkUtil.doRequest(SportDbAPI.getNextMatches(leagueId)).await(),
                EventResponse::class.java
            )

            //Remove match if sport type of match is not soccer sport type
            data.events = data.events.filter { event -> event.sportType == "Soccer" }

            view.hideLoading()
            view.showNextMatches(data.events)
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