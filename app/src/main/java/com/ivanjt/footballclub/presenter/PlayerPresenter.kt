package com.ivanjt.footballclub.presenter

import com.google.gson.Gson
import com.ivanjt.footballclub.CoroutineContextProvider
import com.ivanjt.footballclub.api.SportDbAPI
import com.ivanjt.footballclub.model.PlayerResponse
import com.ivanjt.footballclub.utility.NetworkUtil
import com.ivanjt.footballclub.view.PlayerView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PlayerPresenter(
    private val view: PlayerView,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {
    fun getPlayers(teamId: String) {
        view.showLoading()
        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                NetworkUtil.doRequest(SportDbAPI.getPlayers(teamId)).await(),
                PlayerResponse::class.java
            )

            view.showPlayers(data.player)
        }
        view.hideLoading()
    }
}