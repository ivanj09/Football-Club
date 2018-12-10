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
import com.ivanjt.footballclub.adapter.PlayersAdapter
import com.ivanjt.footballclub.model.Player
import com.ivanjt.footballclub.presenter.PlayerPresenter
import com.ivanjt.footballclub.view.PlayerView

class PlayersFragment : Fragment(), PlayerView {
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var adapter: PlayersAdapter
    private lateinit var presenter: PlayerPresenter
    private var playerList: MutableList<Player> = mutableListOf()

    companion object {
        private val TEAM_ID = "com.ivanjt.footballclub.TEAM_ID"

        fun newInstance(teamId: String?): PlayersFragment {
            val fragment = PlayersFragment()
            val args = Bundle()
            args.putString(TEAM_ID, teamId)

            fragment.arguments = args

            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //get argument
        val args = arguments
        val teamId = args?.getString(TEAM_ID)

        //Reference to xml view
        recyclerView = view.findViewById(R.id.rv_players)
        progressBar = view.findViewById(R.id.pb_loading)

        //Instantiate presenter and gson
        val gson = Gson()
        presenter = PlayerPresenter(this, gson)

        //Give adapter to recyclerView
        adapter = PlayersAdapter(view.context, playerList)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(view.context)

        //Call getPlayers() method
        presenter.getPlayers(teamId.toString())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_players, container, false)
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
        recyclerView.visibility = View.INVISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.INVISIBLE
        recyclerView.visibility = View.VISIBLE
    }

    override fun showPlayers(players: List<Player>) {
        playerList.clear()
        playerList.addAll(players)

        adapter.notifyDataSetChanged()
    }
}
