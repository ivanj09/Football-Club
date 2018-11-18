package com.ivanjt.footballclub.view

import com.ivanjt.footballclub.model.League
import com.ivanjt.footballclub.model.Team

interface TeamsView {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(teams: List<Team>)
    fun showLeagueList(leagues: List<League>)
}