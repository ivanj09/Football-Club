package com.ivanjt.footballclub.View

import com.ivanjt.footballclub.Model.League
import com.ivanjt.footballclub.Model.Team

interface MainView {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(teams: List<Team>)
    fun showLeagueList(leagues: List<League>)
}