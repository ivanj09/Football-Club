package com.ivanjt.footballclub.Presenter

import com.google.gson.Gson
import com.ivanjt.footballclub.API.SportDbAPI
import com.ivanjt.footballclub.Model.EventResponse
import com.ivanjt.footballclub.Model.TeamResponse
import com.ivanjt.footballclub.Utility.NetworkUtil
import com.ivanjt.footballclub.View.DetailView
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class DetailPresenter(private val view: DetailView, private val gson: Gson) {
    fun getMatchDetail(idEvent: String) {
        doAsync {
            val data = gson.fromJson(
                NetworkUtil.doRequest(SportDbAPI.getMatchDetail(idEvent)),
                EventResponse::class.java
            )

            uiThread {
                view.showMatchDetail(data.events)
            }
        }
    }

    fun getTeamDetail(idHomeTeam: String?, idAwayTeam: String?) {
        doAsync {
            val homeData = gson.fromJson(
                NetworkUtil.doRequest(SportDbAPI.getTeam(idHomeTeam)),
                TeamResponse::class.java
            )

            val awayData = gson.fromJson(
                NetworkUtil.doRequest(SportDbAPI.getTeam(idAwayTeam)),
                TeamResponse::class.java
            )

            uiThread {
                view.showTeams(homeData.teams, awayData.teams)
            }
        }
    }
}