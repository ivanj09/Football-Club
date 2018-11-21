package com.ivanjt.footballclub.presenter

import android.content.Context
import com.google.gson.Gson
import com.ivanjt.footballclub.database.contract.Favourite
import com.ivanjt.footballclub.database.helper.FootballClubDbHelper
import com.ivanjt.footballclub.model.Event
import com.ivanjt.footballclub.view.FavouritesView
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class FavouritesPresenter(private val view: FavouritesView, private val context: Context, private val gson: Gson) {
    private lateinit var favouriteList: List<Favourite>
    private val database: FootballClubDbHelper
        get() = FootballClubDbHelper.getInstance(context)

    fun getFavourites() {
        view.showLoading()

        doAsync {
            database.use {
                val result = select(Favourite.TABLE_NAME)
                favouriteList = result.parseList(classParser<Favourite>())
            }

            val list: ArrayList<Event> = arrayListOf()
            for (favourite in favouriteList) {
                list.add(
                    Event(
                        favourite.eventId, null, null, null,
                        favourite.homeTeamName, favourite.awayTeamName, favourite.homeScore, favourite.awayScore,
                        null, null, null, null,
                        null, null, null,
                        null, null, null,
                        null, null, null,
                        null, favourite.date, null, null
                    )
                )
            }

            uiThread {
                view.hideLoading()
                view.showFavourites(list)
            }
        }
    }
}