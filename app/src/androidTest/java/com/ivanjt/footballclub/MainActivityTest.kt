package com.ivanjt.footballclub

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.ivanjt.footballclub.R.id.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @Rule
    @JvmField
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun mainActivityBehaviour() {
        onView(withId(main_content)).check(matches(isDisplayed()))
        onView(withId(btm_nav_view)).check(matches(isDisplayed()))

        onView(withId(mn_matches)).perform(click())
        onView(withId(main_content)).check(matches(isDisplayed()))

        onView(withId(mn_favourites)).perform(click())
        onView(withId(mn_favourites)).check(matches(isDisplayed()))
    }
}