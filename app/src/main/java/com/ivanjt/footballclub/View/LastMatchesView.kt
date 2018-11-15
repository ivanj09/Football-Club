package com.ivanjt.footballclub.View

import com.ivanjt.footballclub.Model.Match

interface LastMatchesView {
    fun showLoading()
    fun hideLoading()
    fun showLastMatches(matches: List<Match>)
}