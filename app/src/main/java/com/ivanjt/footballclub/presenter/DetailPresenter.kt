package com.ivanjt.footballclub.presenter

import com.google.gson.Gson
import com.ivanjt.footballclub.CoroutineContextProvider
import com.ivanjt.footballclub.api.SportDbAPI
import com.ivanjt.footballclub.model.EventResponse
import com.ivanjt.footballclub.model.TeamResponse
import com.ivanjt.footballclub.utility.NetworkUtil
import com.ivanjt.footballclub.view.DetailView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DetailPresenter(
    private val view: DetailView,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {
    fun getMatchDetail(idEvent: String) {
        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                NetworkUtil.doRequest(SportDbAPI.getMatchDetail(idEvent)).await(),
                EventResponse::class.java
            )

            view.showMatchDetail(data.events)
        }
    }

    fun getTeamDetail(idHomeTeam: String?, idAwayTeam: String?) {
        GlobalScope.launch(context.main) {
            val homeData = gson.fromJson(
                NetworkUtil.doRequest(SportDbAPI.getTeam(idHomeTeam)).await(),
                TeamResponse::class.java
            )

            val awayData = gson.fromJson(
                NetworkUtil.doRequest(SportDbAPI.getTeam(idAwayTeam)).await(),
                TeamResponse::class.java
            )

            view.showTeams(homeData.teams, awayData.teams)
        }
    }
}