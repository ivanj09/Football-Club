package com.ivanjt.footballclub

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.google.gson.Gson
import com.ivanjt.footballclub.adapter.LastMatchesAdapter
import com.ivanjt.footballclub.model.Event
import com.ivanjt.footballclub.presenter.LastMatchesPresenter
import com.ivanjt.footballclub.view.LastMatchesView

class LastMatchesFragment : Fragment(), LastMatchesView {
    private var lastEventList: MutableList<Event> = mutableListOf()
    private lateinit var recyclerView: RecyclerView
    private lateinit var presenter: LastMatchesPresenter
    private lateinit var adapter: LastMatchesAdapter
    private lateinit var progressBar: ProgressBar

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

        //Give adapter to RecyclerView
        adapter = LastMatchesAdapter(view.context, lastEventList)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(view.context)

        //Initialize presenter
        val gson = Gson()
        presenter = LastMatchesPresenter(this, gson)

        //Get Last Matches from English Premier League
        presenter.getLastMatches("4328")
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
    }
}
