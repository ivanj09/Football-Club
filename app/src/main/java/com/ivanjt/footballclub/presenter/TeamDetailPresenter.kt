package com.ivanjt.footballclub.presenter

import com.google.gson.Gson
import com.ivanjt.footballclub.CoroutineContextProvider
import com.ivanjt.footballclub.api.SportDbAPI
import com.ivanjt.footballclub.model.TeamResponse
import com.ivanjt.footballclub.utility.NetworkUtil
import com.ivanjt.footballclub.view.TeamDetailView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TeamDetailPresenter(
    private val view: TeamDetailView,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {
    fun getTeamDetail(id: String?) {
        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                NetworkUtil.doRequest(SportDbAPI.getTeam(id)).await(),
                TeamResponse::class.java
            )

            view.showDetail(data.teams[0])
        }
    }
}