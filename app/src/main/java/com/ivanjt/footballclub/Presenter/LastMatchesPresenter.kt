package com.ivanjt.footballclub.Presenter

import com.google.gson.Gson
import com.ivanjt.footballclub.API.SportDbAPI
import com.ivanjt.footballclub.Model.EventResponse
import com.ivanjt.footballclub.Utility.NetworkUtil
import com.ivanjt.footballclub.View.LastMatchesView
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class LastMatchesPresenter(private val view: LastMatchesView, private val gson: Gson) {
    fun getLastMatches(leagueId: String) {
        view.showLoading()

        doAsync {
            val data = gson.fromJson(
                NetworkUtil.doRequest(SportDbAPI.getLastMatches(leagueId)),
                EventResponse::class.java
            )

            //Remove match if sport type of match is not soccer sport type
            data.events = data.events.filter { event -> event.sportType == "Soccer" }

            uiThread {
                view.hideLoading()
                view.showLastMatches(data.events)
            }
        }
    }
}