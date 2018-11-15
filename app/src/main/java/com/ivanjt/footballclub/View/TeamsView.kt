package com.ivanjt.footballclub.View

import com.ivanjt.footballclub.Model.League
import com.ivanjt.footballclub.Model.Team

interface TeamsView {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(teams: List<Team>)
    fun showLeagueList(leagues: List<League>)
}