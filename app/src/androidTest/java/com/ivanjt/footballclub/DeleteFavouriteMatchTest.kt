package com.ivanjt.footballclub

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DeleteFavouriteMatchTest {
    @Rule
    @JvmField
    var activity = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setUp() {
        onView(withId(R.id.mn_favourites)).perform(click())
    }

    @Test
    fun deleteFavouriteTest() {
        onView(withText("Arsenal")).check(matches(isDisplayed()))
        onView(withText("Arsenal")).perform(click())

        onView(withId(R.id.mn_favourite))
            .check(matches(isDisplayed()))
        onView(withId(R.id.mn_favourite)).perform(click())

        onView(withText("Successfully deleted from your favourites!:)"))
            .check(matches(isDisplayed()))

        pressBack()
    }
}