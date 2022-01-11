package com.example.hier

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.properties.Delegates
import androidx.recyclerview.widget.RecyclerView

import androidx.test.espresso.NoMatchingViewException

import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.CoreMatchers.`is`


class ReservationsFragmentTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    var resCount: Int = 0

    @Before
    fun setUp() {
        onView(withId(R.id.reservationsFragment)).perform(click())
    }

    @Test
    fun tableIsDisplayed() {
        onView(withId(R.id.reservations_table)).check(matches(isEnabled()))
    }

    /*
    @Test
    fun reservationsListItemCount() {
        onView(withId(R.id.reservations_list)).check(RecyclerViewItemCountAssertion(30));
    }

    class RecyclerViewItemCountAssertion(private val expectedCount: Int) :
        ViewAssertion {
        override fun check(view: View, noViewFoundException: NoMatchingViewException?) {
            if (noViewFoundException != null) {
                throw noViewFoundException
            }
            val recyclerView = view as RecyclerView
            val adapter = recyclerView.adapter
            assertThat(adapter!!.itemCount, `is`(expectedCount))
        }
    }
    */
}
