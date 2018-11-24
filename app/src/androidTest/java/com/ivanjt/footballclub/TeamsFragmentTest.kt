package com.ivanjt.footballclub

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.ivanjt.footballclub.R.id.rv_teams
import com.ivanjt.footballclub.R.id.sp_league
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TeamsFragmentTest {
    @Rule
    @JvmField
    var activity = ActivityTestRule(MainActivity::class.java)

    @Test
    fun recyclerViewTest() {
        Thread.sleep(3500)

        onView(withId(sp_league)).check(matches(isDisplayed()))
        onView(withId(rv_teams)).check(matches(isDisplayed()))

        onView(withId(rv_teams)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))
        onView(withId(rv_teams)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(1))

        onView(withId(sp_league)).perform(click())
        onView(withText("Spanish La Liga")).perform(click())

        onView(withId(rv_teams)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))
        onView(withId(rv_teams)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(1))
    }
}