package com.ivanjt.footballclub

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ivanjt.footballclub.adapter.FavouritesAdapter
import com.ivanjt.footballclub.presenter.FavouritesPresenter
import com.ivanjt.footballclub.view.FavouritesView

class FavouritesFragment : Fragment(), FavouritesView {
    private lateinit var recyclerView: RecyclerView
    private lateinit var presenter: FavouritesPresenter
    private lateinit var adapter: FavouritesAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_event_matches, container, false)
    }

    override fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showFavourites() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
