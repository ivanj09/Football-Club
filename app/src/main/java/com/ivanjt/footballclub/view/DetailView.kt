package com.ivanjt.footballclub.view

import com.ivanjt.footballclub.model.Event
import com.ivanjt.footballclub.model.Team

interface DetailView {
    fun showMatchDetail(events: List<Event>)
    fun showTeams(homeTeam: List<Team>, awayTeam: List<Team>)
}
