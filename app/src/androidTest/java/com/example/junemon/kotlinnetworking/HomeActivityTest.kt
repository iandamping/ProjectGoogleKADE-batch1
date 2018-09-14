package com.example.junemon.kotlinnetworking

import android.support.test.espresso.Espresso
import android.support.test.espresso.IdlingRegistry
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.example.junemon.kotlinnetworking.R.id.*
import com.example.junemon.kotlinnetworking.helper.EspressoIdlingResource
import com.example.junemon.kotlinnetworking.home.HomeActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)

class HomeActivityTest {

    @Rule
    @JvmField
    var activityRule = ActivityTestRule(HomeActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getIdlingRes())
    }

    @Test
    fun testBottomView() {
        Espresso.onView(withId(rvFootbalEventLastMatch)).check(matches(isDisplayed()))
        Espresso.onView(withId(rvFootbalEventLastMatch))
                .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(9))
        Espresso.onView(withId(rvFootbalEventLastMatch)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(9, click()))
        Espresso.onView(withId(add_to_favorite)).check(matches(isDisplayed()))
        Espresso.onView(withId(add_to_favorite)).perform(click()).perform(pressBack())


        Espresso.onView(withId(bottom_navigation)).check(matches(isDisplayed()))
        Espresso.onView(withId(favourites)).perform(click())
        Espresso.onView(withId(rvFootbalEventFav))
                .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0))
        Espresso.onView(withId(rvFootbalEventFav)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        Espresso.onView(withId(add_to_favorite)).check(matches(isDisplayed()))
        Espresso.onView(withId(add_to_favorite)).perform(click()).perform(pressBack())
        Espresso.onView(withId(swipesRefreshs)).perform(swipeDown())


    }

}