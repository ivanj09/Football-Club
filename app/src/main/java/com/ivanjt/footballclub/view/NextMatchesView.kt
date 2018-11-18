package com.ivanjt.footballclub.view

import com.ivanjt.footballclub.model.Event

interface NextMatchesView {
    fun showLoading()
    fun hideLoading()
    fun showNextMatches(events: List<Event>)
}
