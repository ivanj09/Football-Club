package com.ivanjt.footballclub.view

import com.ivanjt.footballclub.model.Team

interface TeamFavouriteView : FavouriteView {
    fun showFavouriteTeams(teams: List<Team>)
}
