package com.example.junemon.kotlinnetworking

import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.IdlingRegistry
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.example.junemon.kotlinnetworking.R.id.*
import com.example.junemon.kotlinnetworking.feature.mainscreen.MainScreen
import com.example.junemon.kotlinnetworking.helper.EspressoIdlingResource
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class HomeActivityTest {

    @Rule
    @JvmField
    var activityRule = ActivityTestRule(MainScreen::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getIdlingRes())
    }

    @Test
    fun testBottomView() {
        Espresso.onView(withId(R.id.swipeMainScreen)).perform(ViewActions.swipeDown())
        Espresso.onView(withId(R.id.rvLeague)).check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.rvLeague))
                .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0))
        Espresso.onView(withId(R.id.rvLeague)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        Espresso.onView(allOf(withId(android.R.id.button1), withText("OK"), childAtPosition(
                childAtPosition(withClassName(`is`<String>("android.widget.ScrollView")), 0), 0)))

        val appCompatButton = Espresso.onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(`is`("android.widget.ScrollView")),
                                        0),
                                3)))
        appCompatButton.perform(scrollTo(), click())
        Espresso.onView(withId(R.id.rvFootbalEventLastMatch)).check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.rvFootbalEventLastMatch))
                .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(9))
        Espresso.onView(withId(R.id.rvFootbalEventLastMatch)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(9, click()))
        Espresso.onView(withId(R.id.add_to_favorite)).check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.add_to_favorite)).perform(click()).perform(ViewActions.pressBack())

        Espresso.onView(withId(bottom_navigation)).check(matches(isDisplayed()))
        Espresso.onView(withId(listTeams)).perform(click())
        Espresso.onView(withId(rvAllTeam))
                .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0))
        Espresso.onView(withId(rvAllTeam)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        Espresso.onView(withId(add_to_favorite)).perform(click()).perform(pressBack())

        Espresso.onView(withId(bottom_navigation)).check(matches(isDisplayed()))
        Espresso.onView(withId(favourites)).perform(click())
        Espresso.onView(withId(rvFootbalEventFav))
                .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0))
        Espresso.onView(withId(rvFootbalEventFav)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        Espresso.onView(withId(add_to_favorite)).check(matches(isDisplayed()))
        Espresso.onView(withId(add_to_favorite)).perform(click()).perform(pressBack())
        Espresso.onView(withId(swipesRefreshs)).perform(swipeDown())
        Espresso.onView(withId(tabFavPage)).perform(swipeRight())

        val tabView = onView(
                allOf(childAtPosition(
                        childAtPosition(
                                withId(R.id.tabFavPage),
                                0),
                        1),
                        isDisplayed()))
        tabView.perform(click())

        Espresso.onView(withId(rvFavAllTeam))
                .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0))
        Espresso.onView(withId(rvFavAllTeam)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        Espresso.onView(withId(add_to_favorite)).check(matches(isDisplayed()))
        Espresso.onView(withId(add_to_favorite)).perform(click()).perform(pressBack())
        Espresso.onView(withId(swipeRefFavTeam)).perform(swipeDown())
        Espresso.onView(withId(bottom_navigation)).check(matches(isDisplayed()))
        pressBack()

        Espresso.onView(withId(bottom_navigation)).check(matches(isDisplayed()))
        Espresso.onView(withId(listTeams)).perform(click())
        Espresso.onView(withId(action_search)).perform(click())
        Espresso.onView(withId(etSearch)).check(matches(isDisplayed())).perform(typeText("arsenal"))
        pressBack()

    }

}

private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int): Matcher<View> {

    return object : TypeSafeMatcher<View>() {
        override fun describeTo(description: Description) {
            description.appendText("Child at position $position in parent ")
            parentMatcher.describeTo(description)
        }

        public override fun matchesSafely(view: View): Boolean {
            val parent = view.parent
            return (parent is ViewGroup && parentMatcher.matches(parent)
                    && view == parent.getChildAt(position))
        }
    }
}
