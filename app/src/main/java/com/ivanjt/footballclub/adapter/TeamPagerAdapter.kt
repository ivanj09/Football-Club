package com.ivanjt.footballclub.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.ivanjt.footballclub.PlayersFragment
import com.ivanjt.footballclub.TeamDetailFragment

class TeamPagerAdapter(
    fragmentManager: FragmentManager,
    desc: String?,
    teamId: String?
) : FragmentPagerAdapter(fragmentManager) {

    private var teamDetailFragment: TeamDetailFragment = TeamDetailFragment.newInstance(desc.toString())
    private var playersFragment: PlayersFragment = PlayersFragment.newInstance(teamId.toString())

    override fun getItem(p0: Int): Fragment {
        when (p0) {
            0 -> return teamDetailFragment
            1 -> return playersFragment
        }
        return Fragment()
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        var title: String? = null
        when (position) {
            0 -> title = "Overview"
            1 -> title = "Players"
        }

        return title
    }
}