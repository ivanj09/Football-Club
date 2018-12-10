package com.ivanjt.footballclub.presenter

import android.content.Context
import com.google.gson.Gson
import com.ivanjt.footballclub.database.contract.FavouriteMatch
import com.ivanjt.footballclub.database.contract.FavouriteTeam
import com.ivanjt.footballclub.database.helper.FootballClubDbHelper
import com.ivanjt.footballclub.model.Event
import com.ivanjt.footballclub.model.Team
import com.ivanjt.footballclub.view.FavouriteView
import com.ivanjt.footballclub.view.MatchFavouriteView
import com.ivanjt.footballclub.view.TeamFavouriteView
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class FavouritesPresenter(private val view: FavouriteView, private val context: Context, private val gson: Gson) {
    private lateinit var favouriteMatchList: List<FavouriteMatch>
    private lateinit var favouriteTeamList: List<FavouriteTeam>

    private val database: FootballClubDbHelper
        get() = FootballClubDbHelper.getInstance(context)

    fun getFavouriteMatches() {
        view.showLoading()

        doAsync {
            database.use {
                val result = select(FavouriteMatch.TABLE_NAME)
                favouriteMatchList = result.parseList(classParser())
            }

            val list: ArrayList<Event> = arrayListOf()
            for (favourite in favouriteMatchList) {
                list.add(
                    Event(
                        favourite.eventId, null, null, null,
                        favourite.homeTeamName, favourite.awayTeamName, favourite.homeScore, favourite.awayScore,
                        null, null, null, null,
                        null, null, null,
                        null, null, null,
                        null, null, null,
                        null, favourite.date, null, null, favourite.time
                    )
                )
            }

            uiThread {
                view.hideLoading()
                (view as MatchFavouriteView).showFavouriteMatches(list)
            }
        }
    }

    fun getFavouriteTeams() {
        view.showLoading()

        doAsync {
            database.use {
                val result = select(FavouriteTeam.TABLE_NAME)
                favouriteTeamList = result.parseList(classParser())
            }

            val list: ArrayList<Team> = arrayListOf()
            for (favourite in favouriteTeamList) {
                list.add(
                    Team(
                        favourite.teamId,
                        favourite.name,
                        favourite.badge,
                        favourite.year,
                        favourite.stadium,
                        favourite.description
                    )
                )
            }

            uiThread {
                view.hideLoading()
                (view as TeamFavouriteView).showFavouriteTeams(list)
            }
        }
    }
}