package com.ivanjt.footballclub.Adapter

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.ivanjt.footballclub.LastMatchesFragment
import com.ivanjt.footballclub.NextMatchesFragment

class MatchesPagerAdapter(private val context: Context, private val fragmentManager: FragmentManager) :
    FragmentPagerAdapter(fragmentManager) {

    override fun getItem(p0: Int): Fragment {
        when (p0) {
            1 -> {
                return NextMatchesFragment()
            }

            2 -> {
                return LastMatchesFragment()
            }
        }
        return Fragment()
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        var title: String? = null
        when (position) {
            1 -> title = "Last Matches"
            2 -> title = "Next Matches"
        }

        return title
    }

}
