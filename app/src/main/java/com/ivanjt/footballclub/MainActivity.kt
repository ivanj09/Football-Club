package com.ivanjt.footballclub

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import org.jetbrains.anko.*
import org.jetbrains.anko.design.bottomNavigationView

class MainActivity : AppCompatActivity() {
    private val TEAMS_FRAGMENT = 0
    private val MATCH_FRAGMENT = 1
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var fragment: Fragment
    private var fragmentManager: FragmentManager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Set default fragment
        setFragment(TEAMS_FRAGMENT)

        //Create view
        verticalLayout {
            lparams(width = matchParent, height = wrapContent) {
                margin = 0
                padding = 0
            }

            frameLayout {
                frameLayout {
                    id = R.id.main_content
                }
            }.lparams(width = matchParent, height = dip(0)) {
                weight = 15.0f
                leftPadding = dip(8)
                rightPadding = dip(8)
            }

            bottomNavigationView = bottomNavigationView {
                inflateMenu(R.menu.bottom_nav_view)
                fitsSystemWindows = true
                backgroundResource = android.R.color.white
                elevation = 5.0f

                setOnNavigationItemSelectedListener { item ->
                    when (item.itemId) {
                        R.id.mn_teams -> setFragment(TEAMS_FRAGMENT)
                        R.id.mn_match -> setFragment(MATCH_FRAGMENT)
                    }

                    true
                }
            }.lparams(width = matchParent, height = dip(0)) {
                weight = 1.0f
                topPadding = 0
                leftPadding = 0
                bottomPadding = 0
                rightPadding = 0
            }
        }
    }

    private fun setFragment(fragmentId: Int) {
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()

        when (fragmentId) {
            TEAMS_FRAGMENT -> {
                fragment = TeamsFragment()
                fragmentTransaction.replace(R.id.main_content, fragment).commit()
            }

            MATCH_FRAGMENT -> {
                fragment = MatchesFragment()
                fragmentTransaction.replace(R.id.main_content, fragment).commit()
            }
        }
    }
}
