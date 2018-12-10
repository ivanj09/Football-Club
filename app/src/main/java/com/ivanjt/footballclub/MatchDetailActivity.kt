package com.ivanjt.footballclub

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.google.gson.Gson
import com.ivanjt.footballclub.adapter.LastMatchesAdapter
import com.ivanjt.footballclub.database.contract.FavouriteMatch
import com.ivanjt.footballclub.database.helper.FootballClubDbHelper
import com.ivanjt.footballclub.model.Event
import com.ivanjt.footballclub.model.Team
import com.ivanjt.footballclub.presenter.DetailPresenter
import com.ivanjt.footballclub.view.DetailView
import com.squareup.picasso.Picasso
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import java.sql.SQLException
import java.text.SimpleDateFormat
import java.util.*

class MatchDetailActivity : AppCompatActivity(), DetailView {
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
    private lateinit var date: TextView
    private lateinit var time: TextView
    private lateinit var awaySubstitutes: TextView
    private lateinit var presenter: DetailPresenter
    private lateinit var event: Event
    private lateinit var root: View
    private val database: FootballClubDbHelper
        get() = FootballClubDbHelper.getInstance(this)
    private var homeTeamBadge: String? = null
    private var awayTeamBadge: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_details)

        //Set back button in actionBar
        val actionBar: ActionBar? = supportActionBar
        if (actionBar != null) {
            actionBar.elevation = 0F
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.title = "Detail"
        }

        //Set reference to xml view
        root = findViewById(R.id.layout)
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
        time = findViewById(R.id.tv_time_event)
        date = findViewById(R.id.tv_date_event)

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
        event = events[0]

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

        var df1 = SimpleDateFormat("yyyy-MM-dd hh:mm:ssZZZZZ")
        var df2 = SimpleDateFormat("hh:mm:ssZZZZZ")

        df1.timeZone = TimeZone.getTimeZone("UTC")
        df2.timeZone = TimeZone.getTimeZone("UTC")
        val d = df1.parse(event.date + " " + event.time)
        val t = df2.parse(event.time)

        df1 = SimpleDateFormat("EEE, dd MMM yyyy")
        df2 = SimpleDateFormat("HH:mm")
        df1.timeZone = TimeZone.getDefault()
        df2.timeZone = TimeZone.getDefault()

        event.date = df1.format(d)
        event.time = df2.format(t)

        date.text = event.date.toString()
        time.text = event.time.toString()

        presenter.getTeamDetail(event.homeTeamId, event.awayTeamId)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_favourites, menu)

        val menuItem: MenuItem? = menu?.findItem(R.id.mn_favourite)
        menuItem?.isChecked = getMatchFavouriteState()

        if (menuItem?.isChecked!!) {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_star_black_24dp)
        }

        return super.onCreateOptionsMenu(menu)

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val isChecked = item?.isChecked

        when (item?.itemId) {
            R.id.mn_favourite -> {
                if (!isChecked!!) {
                    if (addToFavourites()) {
                        item.icon = ContextCompat.getDrawable(this, R.drawable.ic_star_black_24dp)
                        item.isChecked = true
                    }
                } else {
                    if (deleteFromFavourites()) {
                        item.icon = ContextCompat.getDrawable(this, R.drawable.ic_star_border_black_24dp)
                        item.isChecked = false
                    }
                }

                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun getMatchFavouriteState(): Boolean {
        return try {
            var isEmpty = false
            database.use {
                val result = select(FavouriteMatch.TABLE_NAME)
                    .whereArgs(
                        "(event_id = {id})",
                        "id" to intent.extras.getString(LastMatchesAdapter.EXTRA_EVENT)
                    )
                val favorite = result.parseList(classParser<FavouriteMatch>())
                isEmpty = favorite.isEmpty()
            }
            !isEmpty
        } catch (e: SQLException) {
            e.printStackTrace()
            false
        }
    }

    private fun deleteFromFavourites(): Boolean {
        return try {
            database.use {
                delete(
                    FavouriteMatch.TABLE_NAME,
                    "(event_id = {id})",
                    "id" to intent.extras.getString(LastMatchesAdapter.EXTRA_EVENT)
                )
            }
            Snackbar.make(root, "Successfully deleted from your favourites!:)", Snackbar.LENGTH_SHORT).show()
            true
        } catch (e: SQLException) {
            e.printStackTrace()

            //Add snackbar
            Snackbar.make(root, "Cannot deleted from your favourites!:(", Snackbar.LENGTH_SHORT).show()
            false
        }
    }

    private fun addToFavourites(): Boolean {
        return try {
            database.use {
                insert(
                    FavouriteMatch.TABLE_NAME,
                    FavouriteMatch.EVENT_ID to event.id,
                    FavouriteMatch.HOME_TEAM_NAME to event.homeTeam,
                    FavouriteMatch.HOME_TEAM_BADGE to homeTeamBadge,
                    FavouriteMatch.HOME_SCORE to event.homeScore,
                    FavouriteMatch.AWAY_TEAM_NAME to event.awayTeam,
                    FavouriteMatch.AWAY_TEAM_BADGE to awayTeamBadge,
                    FavouriteMatch.AWAY_SCORE to event.awayScore,
                    FavouriteMatch.DATE to event.date,
                    FavouriteMatch.TIME to event.time
                )
            }

            //Add snackbar
            Snackbar.make(root, "Successfully added to your favourites!:)", Snackbar.LENGTH_SHORT).show()
            true
        } catch (e: SQLiteConstraintException) {
            e.printStackTrace()

            //Add snackbar
            Snackbar.make(root, "Cannot added to your favourites!:(", Snackbar.LENGTH_SHORT).show()
            false
        }
    }

    override fun showTeams(homeTeam: List<Team>, awayTeam: List<Team>) {
        homeTeamBadge = homeTeam[0].badge
        awayTeamBadge = awayTeam[0].badge

        Picasso.get().load(homeTeam[0].badge).into(homeImage)
        Picasso.get().load(awayTeam[0].badge).into(awayImage)
    }
}
