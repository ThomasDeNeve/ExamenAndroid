package com.example.hier

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.hier.ui.reservations.ReservationsFragment
import org.junit.Before
import org.junit.Test

class ReservationsFragmentTest {
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
}