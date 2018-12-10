package com.ivanjt.footballclub

import android.os.Bundle
import android.support.v4.app.Fragment
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
import com.ivanjt.footballclub.adapter.LastMatchesAdapter
import com.ivanjt.footballclub.model.Event
import com.ivanjt.footballclub.model.League
import com.ivanjt.footballclub.presenter.LastMatchesPresenter
import com.ivanjt.footballclub.view.LastMatchesView

class LastMatchesFragment : Fragment(), LastMatchesView {
    private var lastEventList: MutableList<Event> = mutableListOf()
    private lateinit var recyclerView: RecyclerView
    private lateinit var presenter: LastMatchesPresenter
    private lateinit var adapter: LastMatchesAdapter
    private lateinit var progressBar: ProgressBar
    private lateinit var spinner: Spinner
    private var leagueIdList: MutableList<String> = mutableListOf()
    private lateinit var spinnerItems: MutableList<String>

    fun getAdapter(): LastMatchesAdapter {
        return adapter
    }

    companion object {
        fun newInstance(): LastMatchesFragment {
            return LastMatchesFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_event_matches, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Reference to xml view
        recyclerView = view.findViewById(R.id.rv_matches)
        progressBar = view.findViewById(R.id.pb_loading)
        spinner = view.findViewById(R.id.sp_league)

        //Initialize presenter
        val gson = Gson()
        presenter = LastMatchesPresenter(this, gson)

        //Add leagues to spinner
        presenter.getLeagueList()

        //Add OnItemSelectedListener for spinner
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                presenter.getLastMatches(leagueIdList[p2])
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }

        //Give adapter to RecyclerView
        adapter = LastMatchesAdapter(view.context, lastEventList, false)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(view.context)
    }

    override fun showLoading() {
        recyclerView.visibility = View.INVISIBLE
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        recyclerView.visibility = View.VISIBLE
        progressBar.visibility = View.INVISIBLE
    }

    override fun showLastMatches(events: List<Event>) {
        lastEventList.clear()
        lastEventList.addAll(events)

        adapter.notifyDataSetChanged()

        val filter = adapter.filter as LastMatchesAdapter.EventFilter
        filter.notifyDataSetChanged()
    }

    override fun showLeagueList(leagues: List<League>) {
        spinnerItems = mutableListOf()

        for (league in leagues) {
            leagueIdList.add(league.id.toString())
            spinnerItems.add(league.name.toString())
        }

        if (spinnerItems.size > 0 && context != null) {
            presenter.getLastMatches(leagueIdList[0])
            spinner.adapter = ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        }
    }
}
