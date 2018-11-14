package com.ivanjt.footballclub

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ProgressBar
import android.widget.Spinner
import com.ivanjt.footballclub.Model.Team
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class MainActivity : AppCompatActivity() {
    private var footBallClubList: List<Team>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        MainActivityUI().setContentView(this)
    }

    class MainActivityUI : AnkoComponent<MainActivity> {
        private lateinit var listTeam: RecyclerView
        private lateinit var spinner: Spinner
        private lateinit var swipeRefreshLayout: SwipeRefreshLayout
        private lateinit var progressBar: ProgressBar

        override fun createView(ui: AnkoContext<MainActivity>): View = with(ui) {
            verticalLayout {
                lparams(width = matchParent, height = wrapContent)
                leftPadding = dip(8)
                rightPadding = dip(8)
                bottomPadding = dip(8)

                swipeRefreshLayout = swipeRefreshLayout {
                    setColorSchemeResources(
                        R.color.colorAccent,
                        android.R.color.holo_green_light,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_blue_light
                    )

                    relativeLayout {
                        lparams(width = matchParent, height = wrapContent)

                        spinner = spinner().lparams(width = matchParent, height = wrapContent) {
                            margin = dip(16)
                        }

                        listTeam = recyclerView {
                            lparams(width = matchParent, height = wrapContent)
                            layoutManager = LinearLayoutManager(context)
                        }

                        progressBar = progressBar {
                        }.lparams {
                            centerInParent()
                        }
                    }
                }
            }
        }
    }
}
