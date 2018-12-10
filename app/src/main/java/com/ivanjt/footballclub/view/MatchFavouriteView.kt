package com.ivanjt.footballclub.view

import com.ivanjt.footballclub.model.Event

interface MatchFavouriteView : FavouriteView {
    fun showFavouriteMatches(events: List<Event>)
}