package com.ivanjt.footballclub

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import com.ivanjt.footballclub.Adapter.LastMatchesAdapter
import com.ivanjt.footballclub.Model.Match
import com.ivanjt.footballclub.Presenter.LastMatchesPresenter
import com.ivanjt.footballclub.View.LastMatchesView

class LastMatchesFragment : Fragment(), LastMatchesView {
    private var lastMatchList: MutableList<Match> = mutableListOf()
    private lateinit var recyclerView: RecyclerView
    private lateinit var presenter: LastMatchesPresenter
    private lateinit var adapter: LastMatchesAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_last_matches, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Initialize presenter
        val gson = Gson()
        presenter = LastMatchesPresenter(this, gson)

        //Get Last Matches
        presenter.getLastMatches("4328")

        //Reference to xml view
        recyclerView = view.findViewById(R.id.rv_last_matches)
        adapter = LastMatchesAdapter(lastMatchList)
    }

    override fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showLastMatches(matches: List<Match>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
