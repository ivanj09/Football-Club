package com.ivanjt.footballclub.View

import com.ivanjt.footballclub.Model.Event

interface NextMatchesView {
    fun showLoading()
    fun hideLoading()
    fun showNextMatches(events: List<Event>)
}
