package com.ivanjt.footballclub.view

import com.ivanjt.footballclub.model.Event
import com.ivanjt.footballclub.model.League

interface LastMatchesView {
    fun showLoading()
    fun hideLoading()
    fun showLastMatches(events: List<Event>)
    fun showLeagueList(leagues: List<League>)
}