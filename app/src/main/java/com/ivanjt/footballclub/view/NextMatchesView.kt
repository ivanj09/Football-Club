package com.ivanjt.footballclub.view

import com.ivanjt.footballclub.model.Event
import com.ivanjt.footballclub.model.League

interface NextMatchesView {
    fun showLoading()
    fun hideLoading()
    fun showNextMatches(events: List<Event>)
    fun showLeagueList(leagues: List<League>)
}
