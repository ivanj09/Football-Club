package com.ivanjt.footballclub.presenter

import com.google.gson.Gson
import com.ivanjt.footballclub.api.SportDbAPI
import com.ivanjt.footballclub.model.LeagueResponse
import com.ivanjt.footballclub.model.TeamResponse
import com.ivanjt.footballclub.utility.NetworkUtil
import com.ivanjt.footballclub.view.TeamsView
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class TeamsPresenter(private val view: TeamsView, private val gson: Gson) {
    fun getTeamList(league: String?) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(NetworkUtil.doRequest(SportDbAPI.getTeams(league)), TeamResponse::class.java)

            uiThread {
                view.hideLoading()
                view.showTeamList(data.teams)
            }
        }
    }

    fun getLeagueList() {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(
                NetworkUtil.doRequest(SportDbAPI.getLeagues()),
                LeagueResponse::class.java
            )

            //Remove league if type of league is not soccer sport type
            data.leagues = data.leagues.filter { league -> league.sportType == "Soccer" }

            uiThread {
                view.hideLoading()
                view.showLeagueList(data.leagues)
            }
        }
    }
}