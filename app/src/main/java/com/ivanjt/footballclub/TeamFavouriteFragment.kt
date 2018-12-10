package com.ivanjt.footballclub

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.google.gson.Gson
import com.ivanjt.footballclub.adapter.TeamsAdapter
import com.ivanjt.footballclub.model.Team
import com.ivanjt.footballclub.presenter.FavouritesPresenter
import com.ivanjt.footballclub.view.TeamFavouriteView
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.UI
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class TeamFavouriteFragment : Fragment(), TeamFavouriteView {
    private lateinit var progressBar: ProgressBar
    private lateinit var recyclerView: RecyclerView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var presenter: FavouritesPresenter
    private var favouriteList: MutableList<Team> = mutableListOf()
    private lateinit var adapter: TeamsAdapter

    companion object {
        fun newInstance(): TeamFavouriteFragment {
            return TeamFavouriteFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return UI {
            verticalLayout {
                lparams(width = matchParent, height = wrapContent)

                swipeRefreshLayout = swipeRefreshLayout {
                    setColorSchemeResources(
                        R.color.colorAccent,
                        android.R.color.holo_green_light,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_blue_light
                    )

                    relativeLayout {
                        lparams(width = matchParent, height = wrapContent)

                        recyclerView = recyclerView {
                            id = R.id.rv_teams
                            layoutManager = LinearLayoutManager(context)
                        }.lparams(width = matchParent, height = wrapContent)

                        progressBar = progressBar {
                        }.lparams {
                            centerInParent()
                        }
                    }
                }.lparams(width = matchParent, height = wrapContent) {
                    padding = 0
                }
            }
        }.view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Initialize presenter
        val gson = Gson()
        presenter = FavouritesPresenter(this, view.context, gson)

        //Call getTeamFavourites() method
        presenter.getFavouriteTeams()

        //Add adapter to RecyclerView
        adapter = TeamsAdapter(view.context, favouriteList)
        recyclerView.adapter = adapter

        //Add OnRefreshListener for swipeRefreshLayout
        swipeRefreshLayout.onRefresh {
            presenter.getFavouriteTeams()
        }
    }

    override fun onResume() {
        super.onResume()

        presenter.getFavouriteTeams()
    }

    override fun showFavouriteTeams(teams: List<Team>) {
        favouriteList.clear()
        favouriteList.addAll(teams)

        adapter.notifyDataSetChanged()
    }

    override fun showLoading() {
        recyclerView.visibility = View.INVISIBLE
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        recyclerView.visibility = View.VISIBLE
        progressBar.visibility = View.INVISIBLE
    }
}
