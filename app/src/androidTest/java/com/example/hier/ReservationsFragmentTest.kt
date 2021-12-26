package com.example.hier

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.hier.ui.reservations.ReservationsFragment
import org.hamcrest.CoreMatchers.not
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

class ReservationsFragmentTest
{
    @Before
    fun setUp() {
        launchFragmentInContainer<ReservationsFragment>(
            fragmentArgs = null,
            themeResId = R.style.Theme_MaterialComponents,
            initialState = Lifecycle.State.RESUMED,
            factory = null
        )
    }

    @Test
    fun tableIsDisplayed() {
        onView(withId(R.id.reservations_table)).check(matches(isEnabled()))
    }

    /*@Test
    fun menuNavigation()
    {
        onView(withId(R.id.ranking)).perform(click())
        onView(withId(R.id.ranking_main)).check(matches((isDisplayed())))
        android.support.test.espresso.Espresso.pressBack()

        onView(withId(R.id.info)).perform(click())
        onView(withId(R.id.info_main)).check(matches((isDisplayed())))
        android.support.test.espresso.Espresso.pressBack()

        onView(withId(R.id.nogames)).perform(click())
        onView(withId(R.id.bored_main)).check(matches((isDisplayed())))
        android.support.test.espresso.Espresso.pressBack()

        onView(withId(R.id.players_main)).check(matches((isDisplayed())))
    }*/
}