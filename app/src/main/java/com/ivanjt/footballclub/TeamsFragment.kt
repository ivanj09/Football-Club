package com.ivanjt.footballclub

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import android.widget.Spinner
import com.google.gson.Gson
import com.ivanjt.footballclub.Adapter.TeamsAdapter
import com.ivanjt.footballclub.Model.League
import com.ivanjt.footballclub.Model.Team
import com.ivanjt.footballclub.Presenter.TeamsPresenter
import com.ivanjt.footballclub.View.TeamsView
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.UI
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class TeamsFragment : Fragment(), TeamsView {
    private var footBallClubList: MutableList<Team> = mutableListOf()
    private lateinit var listTeam: RecyclerView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var progressBar: ProgressBar
    private lateinit var presenter: TeamsPresenter
    private lateinit var adapter: TeamsAdapter
    private lateinit var spinner: Spinner
    private lateinit var leagueName: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Initialize presenter
        val gson = Gson()
        presenter = TeamsPresenter(this, gson)

        //Add leagues to spinner
        presenter.getLeagueList()

        //Add adapter to RecyclerView
        adapter = TeamsAdapter(footBallClubList)
        listTeam.adapter = adapter

        //Add OnItemSelectedListener for spinner
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                leagueName = spinner.selectedItem.toString()
                presenter.getTeamList(leagueName)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }

        //Add OnRefreshListener for swipeRefreshLayout
        swipeRefreshLayout.onRefresh {
            presenter.getTeamList(leagueName)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return UI {
            verticalLayout {
                spinner = spinner().lparams(width = matchParent, height = wrapContent) {
                    margin = dip(16)
                    leftPadding = dip(8)
                    rightPadding = dip(8)
                    bottomPadding = dip(8)
                }

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

                        listTeam = recyclerView {
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

    override fun showLoading() {
        listTeam.visibility = RecyclerView.INVISIBLE
        progressBar.visibility = ProgressBar.VISIBLE
    }

    override fun hideLoading() {
        listTeam.visibility = RecyclerView.VISIBLE
        progressBar.visibility = ProgressBar.INVISIBLE
    }

    override fun showTeamList(teams: List<Team>) {
        swipeRefreshLayout.isRefreshing = false

        footBallClubList.clear()
        footBallClubList.addAll(teams)

        adapter.notifyDataSetChanged()
    }

    override fun showLeagueList(leagues: List<League>) {
        swipeRefreshLayout.isRefreshing = false

        val spinnerItems: MutableList<String> = mutableListOf()
        for (league in leagues) {
            spinnerItems.add(league.name.toString())
        }

        val spinnerAdapter = ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        spinner.adapter = spinnerAdapter
    }
}
