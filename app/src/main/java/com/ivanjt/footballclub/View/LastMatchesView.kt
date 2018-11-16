package com.ivanjt.footballclub.View

import com.ivanjt.footballclub.Model.Event

interface LastMatchesView {
    fun showLoading()
    fun hideLoading()
    fun showLastMatches(events: List<Event>)
}