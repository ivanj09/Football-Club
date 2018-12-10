package com.ivanjt.footballclub.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.ivanjt.footballclub.MatchFavouriteFragment
import com.ivanjt.footballclub.TeamFavouriteFragment

class FavouritePagerAdapter(fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager) {
    private val matchFavouritesFragment: MatchFavouriteFragment = MatchFavouriteFragment.newInstance()
    private val teamFavouriteFragment: TeamFavouriteFragment = TeamFavouriteFragment.newInstance()

    override fun getItem(p0: Int): Fragment {
        when (p0) {
            0 -> return matchFavouritesFragment
            1 -> return teamFavouriteFragment
        }

        return Fragment()
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Match Favourites"
            1 -> "Team Favourites"
            else -> null
        }
    }

    override fun getCount(): Int {
        return 2
    }

}