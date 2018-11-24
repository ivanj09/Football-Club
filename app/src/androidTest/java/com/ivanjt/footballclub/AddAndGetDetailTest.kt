package com.ivanjt.footballclub

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.ivanjt.footballclub.R.id.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AddAndGetDetailTest {
    @Rule
    @JvmField
    var activity = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setUp() {
        onView(withId(mn_matches)).perform(click())
    }

    @Test
    fun getMatchDetailTest() {
        onView(withId(vp_matches)).check(matches(isDisplayed()))
        onView(withId(vp_matches)).perform(click())

        Thread.sleep(3500)

        onView(withText("Arsenal")).check(matches(isDisplayed()))
        onView(withText("Arsenal")).perform(click())

        Thread.sleep(3500)
        onView(withText("Arsenal")).check(matches(isDisplayed()))
        onView(withText("Wolves")).check(matches(isDisplayed()))

    }

    @Test
    fun addFavouriteTest() {
        onView(withId(vp_matches)).check(matches(isDisplayed()))
        onView(withId(vp_matches)).perform(click())

        Thread.sleep(3500)

        onView(withText("Arsenal")).check(matches(isDisplayed()))
        onView(withText("Arsenal")).perform(click())

        onView(withId(mn_favourite)).check(matches(isDisplayed()))
        onView(withId(mn_favourite)).perform(click())

        onView(withText("Successfully added to your favourites!:)")).check(matches(isDisplayed()))
        pressBack()
    }
}