package com.ivanjt.footballclub

import android.os.Bundle
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView
import com.google.gson.Gson
import com.ivanjt.footballclub.adapter.LastMatchesAdapter
import com.ivanjt.footballclub.model.Event
import com.ivanjt.footballclub.model.Team
import com.ivanjt.footballclub.presenter.DetailPresenter
import com.ivanjt.footballclub.view.DetailView
import com.squareup.picasso.Picasso

class DetailActivity : AppCompatActivity(), DetailView {
    private lateinit var homeImage: ImageView
    private lateinit var awayImage: ImageView
    private lateinit var homeTeam: TextView
    private lateinit var awayTeam: TextView
    private lateinit var homeGoal: TextView
    private lateinit var awayGoal: TextView
    private lateinit var homeGoalDetails: TextView
    private lateinit var awayGoalDetails: TextView
    private lateinit var homeShots: TextView
    private lateinit var awayShots: TextView
    private lateinit var homeGoalKeeper: TextView
    private lateinit var awayGoalKeeper: TextView
    private lateinit var homeDefenses: TextView
    private lateinit var awayDefenses: TextView
    private lateinit var homeMidField: TextView
    private lateinit var awayMidField: TextView
    private lateinit var homeForward: TextView
    private lateinit var awayForward: TextView
    private lateinit var homeSubstitutes: TextView
    private lateinit var awaySubstitutes: TextView
    private lateinit var presenter: DetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        //Set back button in actionBar
        val actionBar: ActionBar? = supportActionBar
        if (actionBar != null) {
            actionBar.elevation = 0F
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.title = "Detail"
        }

        //Set reference to xml view
        homeTeam = findViewById(R.id.tv_home)
        homeGoal = findViewById(R.id.tv_home_score)
        homeGoalDetails = findViewById(R.id.tv_home_goal_details)
        homeShots = findViewById(R.id.tv_home_shots)
        homeGoalKeeper = findViewById(R.id.tv_home_goal_keeper)
        homeDefenses = findViewById(R.id.tv_home_defenses)
        homeMidField = findViewById(R.id.tv_home_mid_field)
        homeForward = findViewById(R.id.tv_home_forward)
        homeSubstitutes = findViewById(R.id.tv_home_substitutes)
        homeImage = findViewById(R.id.iv_home_team_badge)

        awayImage = findViewById(R.id.iv_away_team_badge)
        awayTeam = findViewById(R.id.tv_away)
        awayGoal = findViewById(R.id.tv_away_score)
        awayGoalDetails = findViewById(R.id.tv_away_goal_details)
        awayShots = findViewById(R.id.tv_away_shots)
        awayGoalKeeper = findViewById(R.id.tv_away_goal_keeper)
        awayDefenses = findViewById(R.id.tv_away_defenses)
        awayMidField = findViewById(R.id.tv_away_mid_field)
        awayForward = findViewById(R.id.tv_away_forward)
        awaySubstitutes = findViewById(R.id.tv_away_substitutes)

        val idEvent = intent.extras.getString(LastMatchesAdapter.EXTRA_EVENT)

        //Initialize presenter
        val gson = Gson()
        presenter = DetailPresenter(this, gson)

        presenter.getMatchDetail(idEvent)
    }

    override fun showMatchDetail(events: List<Event>) {
        val event = events.get(0)

        homeTeam.text = event.homeTeam
        homeGoal.text = event.homeScore
        homeGoalDetails.text = event.homeGoalDetails?.replace(";", "\n")?.replace(":", " - ")
        homeShots.text = event.homeShots
        homeGoalKeeper.text = event.homeLineupGoalkeeper?.replace(";", "\n")
        homeDefenses.text = event.homeLineupDefense?.replace("; ", "\n")
        homeMidField.text = event.homeLineupMidfield?.replace("; ", "\n")
        homeForward.text = event.homeLineupForward?.replace("; ", "\n")
        homeSubstitutes.text = event.homeLineupSubstitutes?.replace("; ", "\n")


        awayTeam.text = event.awayTeam
        awayGoal.text = event.awayScore
        awayGoalDetails.text = event.awayGoalDetails?.replace(";", "\n")?.replace(":", " - ")
        awayShots.text = event.awayShots
        awayGoalKeeper.text = event.awayLineupGoalkeeper?.replace(";", "\n")
        awayDefenses.text = event.awayLineupDefense?.replace("; ", "\n")
        awayMidField.text = event.awayLineupMidfield?.replace("; ", "\n")
        awayForward.text = event.awayLineupForward?.replace("; ", "\n")
        awaySubstitutes.text = event.awayLineupSubstitutes?.replace("; ", "\n")

        presenter.getTeamDetail(event.homeTeamId, event.awayTeamId)
    }

    override fun showTeams(homeTeam: List<Team>, awayTeam: List<Team>) {
        Picasso.get().load(homeTeam[0].badge).into(homeImage)
        Picasso.get().load(awayTeam[0].badge).into(awayImage)
    }
}
