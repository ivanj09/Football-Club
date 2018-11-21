package com.ivanjt.footballclub.view

import com.ivanjt.footballclub.model.Event

interface FavouritesView {
    fun showLoading()
    fun hideLoading()
    fun showFavourites(events: List<Event>)
}