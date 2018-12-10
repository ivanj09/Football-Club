package com.ivanjt.footballclub

import android.database.SQLException
import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.TabLayout
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.google.gson.Gson
import com.ivanjt.footballclub.adapter.TeamPagerAdapter
import com.ivanjt.footballclub.adapter.TeamsAdapter
import com.ivanjt.footballclub.database.contract.FavouriteTeam
import com.ivanjt.footballclub.database.helper.FootballClubDbHelper
import com.ivanjt.footballclub.model.Team
import com.ivanjt.footballclub.presenter.TeamDetailPresenter
import com.ivanjt.footballclub.view.TeamDetailView
import com.squareup.picasso.Picasso
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class TeamDetailActivity : AppCompatActivity(), TeamDetailView {
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager
    private lateinit var root: LinearLayout
    private lateinit var id: String
    private lateinit var team: Team
    private lateinit var teamName: TextView
    private lateinit var teamBadge: ImageView
    private lateinit var year: TextView
    private lateinit var stadium: TextView
    private lateinit var presenter: TeamDetailPresenter
    private val database: FootballClubDbHelper
        get() = FootballClubDbHelper.getInstance(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_details)

        //Set elevation to zero
        val actionBar: ActionBar? = supportActionBar
        if (actionBar != null) actionBar.elevation = 0f

        actionBar?.title = null

        //Get extra from intent
        id = intent.getStringExtra(TeamsAdapter.EXTRA_TEAM_ID)

        //Reference to xml view
        teamName = findViewById(R.id.tv_team_name)
        teamBadge = findViewById(R.id.iv_badge)
        year = findViewById(R.id.tv_year)
        stadium = findViewById(R.id.tv_stadium)
        viewPager = findViewById(R.id.vp_team)
        tabLayout = findViewById(R.id.tl_team)
        root = findViewById(R.id.layout)

        //Initialize presenter
        val gson = Gson()
        presenter = TeamDetailPresenter(this, gson)

        presenter.getTeamDetail(id)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_favourites, menu)

        val menuItem: MenuItem? = menu?.findItem(R.id.mn_favourite)
        menuItem?.isChecked = getTeamFavouriteState()

        if (menuItem?.isChecked!!) {
            menuItem.icon = ContextCompat.getDrawable(this, R.drawable.ic_star_black_24dp)
        }

        return super.onCreateOptionsMenu(menu)
    }

    private fun getTeamFavouriteState(): Boolean {
        return try {
            var isEmpty = false
            database.use {
                val result = select(FavouriteTeam.TABLE_NAME)
                    .whereArgs(
                        "(team_id = {id})",
                        "id" to id
                    )
                val favorite = result.parseList(classParser<FavouriteTeam>())
                isEmpty = favorite.isEmpty()
            }
            !isEmpty
        } catch (e: SQLException) {
            e.printStackTrace()
            false
        }
    }

    private fun addToFavourites(): Boolean {
        return try {
            database.use {
                insert(
                    FavouriteTeam.TABLE_NAME,
                    FavouriteTeam.TEAM_ID to team.id,
                    FavouriteTeam.NAME to team.name,
                    FavouriteTeam.BADGE to team.badge,
                    FavouriteTeam.YEAR to team.year,
                    FavouriteTeam.STADIUM to team.stadium,
                    FavouriteTeam.DESCRIPTION to team.desc
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

    private fun deleteFromFavourites(): Boolean {
        return try {
            database.use {
                delete(
                    FavouriteTeam.TABLE_NAME,
                    "(team_id = {id})",
                    "id" to id
                )
            }
            Snackbar.make(root, "Successfully deleted from your favourites!:)", Snackbar.LENGTH_SHORT).show()
            true
        } catch (e: java.sql.SQLException) {
            e.printStackTrace()

            //Add snackbar
            Snackbar.make(root, "Cannot deleted from your favourites!:(", Snackbar.LENGTH_SHORT).show()
            false
        }
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

    override fun showDetail(team: Team) {
        teamName.text = team.name
        year.text = team.year
        stadium.text = team.stadium
        Picasso.get().load(team.badge).into(teamBadge)

        this.team = team

        //Set adapter to viewPager
        viewPager.adapter = TeamPagerAdapter(supportFragmentManager, team.desc, team.id)

        //Give TabLayout the ViewPager
        tabLayout.setupWithViewPager(viewPager)
    }
}