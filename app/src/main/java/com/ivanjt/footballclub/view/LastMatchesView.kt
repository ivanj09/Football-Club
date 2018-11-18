package com.ivanjt.footballclub.view

import com.ivanjt.footballclub.model.Event

interface LastMatchesView {
    fun showLoading()
    fun hideLoading()
    fun showLastMatches(events: List<Event>)
}