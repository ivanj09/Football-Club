package com.ivanjt.footballclub.view

import com.ivanjt.footballclub.model.Player

interface PlayerView {
    fun showLoading()
    fun hideLoading()
    fun showPlayers(players: List<Player>)
}
