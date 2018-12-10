package com.ivanjt.footballclub

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Spinner
import com.google.gson.Gson
import com.ivanjt.footballclub.adapter.LastMatchesAdapter
import com.ivanjt.footballclub.model.Event
import com.ivanjt.footballclub.presenter.FavouritesPresenter
import com.ivanjt.footballclub.view.MatchFavouriteView

class MatchFavouriteFragment : Fragment(), MatchFavouriteView {
    private lateinit var recyclerView: RecyclerView
    private lateinit var presenter: FavouritesPresenter
    private lateinit var adapter: LastMatchesAdapter
    private lateinit var progressBar: ProgressBar
    private lateinit var spinner: Spinner
    private var favouriteList: MutableList<Event> = mutableListOf()

    companion object {
        fun newInstance(): MatchFavouriteFragment {
            return MatchFavouriteFragment()
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
        spinner.visibility = View.GONE

        //Give adapter to RecyclerView
        adapter = LastMatchesAdapter(view.context, favouriteList, true)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(view.context)

        //Initialize presenter
        val gson = Gson()
        presenter = FavouritesPresenter(this, view.context, gson)

        //Get favourites from db
        presenter.getFavouriteMatches()
    }

    override fun onResume() {
        super.onResume()
        presenter.getFavouriteMatches()
    }

    override fun showLoading() {
        recyclerView.visibility = View.INVISIBLE
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        recyclerView.visibility = View.VISIBLE
        progressBar.visibility = View.INVISIBLE
    }

    override fun showFavouriteMatches(events: List<Event>) {
        favouriteList.clear()
        favouriteList.addAll(events)

        adapter.notifyDataSetChanged()
    }
}
