package com.ivanjt.footballclub.View

import com.ivanjt.footballclub.Model.Event
import com.ivanjt.footballclub.Model.Team

interface DetailView {
    fun showMatchDetail(events: List<Event>)
    fun showTeams(homeTeam: List<Team>, awayTeam: List<Team>)
}
