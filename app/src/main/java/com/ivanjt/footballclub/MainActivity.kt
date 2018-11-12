package com.ivanjt.footballclub

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.ivanjt.footballclub.FootballClubAdapter.FootballAdapter
import com.ivanjt.footballclub.Model.FootballClub
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var footBallClubList: ArrayList<FootballClub> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initClubs()

        rv_football_clubs.layoutManager = LinearLayoutManager(this)
        rv_football_clubs.adapter = FootballAdapter(this, footBallClubList)
    }

    private fun initClubs() {
        val clubsName = resources.getStringArray(R.array.football_club_name)
        val clubsImage = resources.obtainTypedArray(R.array.football_club_image)
        val clubsDesc = resources.getStringArray(R.array.football_club_desc)

        footBallClubList.clear()

        for (i in clubsName.indices) {
            footBallClubList.add(FootballClub(clubsName[i], clubsDesc[i], clubsImage.getResourceId(i, 0)))
        }

        clubsImage.recycle()
    }
}
