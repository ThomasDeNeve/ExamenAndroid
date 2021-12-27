package com.example.hier

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.hamcrest.CoreMatchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun isActivityInView() {
        onView(withId(R.id.root_constraintlayout)).check(matches(isDisplayed()))
    }

    @Test
    fun isToolbarAutomaticallyHidden() {
        onView(withId(R.id.my_toolbar)).check(matches(not(isDisplayed())))
    }

    @Test
    fun isNavHostFragmentDisplayed() {
        onView(withId(R.id.nav_host_fragment)).check(matches(isDisplayed()))
    }
}
