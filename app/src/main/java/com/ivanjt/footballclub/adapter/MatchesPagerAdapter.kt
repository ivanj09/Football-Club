package com.ivanjt.footballclub.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.ivanjt.footballclub.LastMatchesFragment
import com.ivanjt.footballclub.NextMatchesFragment

class MatchesPagerAdapter(fragmentManager: FragmentManager) :
    FragmentStatePagerAdapter(fragmentManager) {

    private var lastMatchesFragment: LastMatchesFragment = LastMatchesFragment.newInstance()
    private var nextMatchesFragment: NextMatchesFragment = NextMatchesFragment.newInstance()

    override fun getItem(p0: Int): Fragment {
        when (p0) {
            0 -> return lastMatchesFragment
            1 -> return nextMatchesFragment
        }
        return Fragment()
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        var title: String? = null
        when (position) {
            0 -> title = "Last Matches"
            1 -> title = "Next Matches"
        }

        return title
    }

}
