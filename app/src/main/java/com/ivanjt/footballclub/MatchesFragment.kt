package com.ivanjt.footballclub

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ivanjt.footballclub.adapter.MatchesPagerAdapter

class MatchesFragment : Fragment() {
    private lateinit var viewPager: ViewPager
    private lateinit var tabLayout: TabLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_matches, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Reference to xml view
        viewPager = view.findViewById(R.id.vp_matches)
        tabLayout = view.findViewById(R.id.tl_matches)

        //Set adapter for viewPager
        viewPager.adapter = fragmentManager?.let { MatchesPagerAdapter(it) }

        //Give TabLayout the ViewPager
        tabLayout.setupWithViewPager(viewPager)
    }

    fun getViewPagerAdapter(): MatchesPagerAdapter {
        return viewPager.adapter as MatchesPagerAdapter
    }
}
