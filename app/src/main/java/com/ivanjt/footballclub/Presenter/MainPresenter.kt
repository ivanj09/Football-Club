package com.ivanjt.footballclub.Presenter

import com.google.gson.Gson
import com.ivanjt.footballclub.API.SportDbAPI
import com.ivanjt.footballclub.Model.TeamResponse
import com.ivanjt.footballclub.Utility.NetworkUtil
import com.ivanjt.footballclub.View.MainView
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainPresenter(private val view: MainView, private val gson: Gson) {
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
}