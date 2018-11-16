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
import com.ivanjt.footballclub.Adapter.NextMatchesAdapter
import com.ivanjt.footballclub.Model.Event
import com.ivanjt.footballclub.Presenter.LastMatchesPresenter
import com.ivanjt.footballclub.Presenter.NextMatchesPresenter
import com.ivanjt.footballclub.View.NextMatchesView

class NextMatchesFragment : Fragment(), NextMatchesView {
    private var nextEventList: MutableList<Event> = mutableListOf()
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var presenter: NextMatchesPresenter
    private lateinit var adapter: NextMatchesAdapter

    companion object {
        fun newInstance(): NextMatchesFragment {
            return NextMatchesFragment()
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
        adapter = NextMatchesAdapter(view.context, nextEventList)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(view.context)

        //Initialize presenter
        val gson = Gson()
        presenter = NextMatchesPresenter(this, gson)

        //Get Next Matches
        presenter.getNextMatches("4328")
    }

    override fun showNextMatches(events: List<Event>) {
        nextEventList.clear()
        nextEventList.addAll(events)

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