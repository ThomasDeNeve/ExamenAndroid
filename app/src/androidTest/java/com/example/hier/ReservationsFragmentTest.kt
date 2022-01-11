package com.example.hier

import android.view.KeyEvent
import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import androidx.recyclerview.widget.RecyclerView

import androidx.test.espresso.NoMatchingViewException

import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiSelector
import org.hamcrest.CoreMatchers.`is`
import org.junit.After


class ReservationsFragmentTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp() {
        login()
        onView(withId(R.id.reservationsFragment)).perform(click())
    }

    private fun login() {
        Thread.sleep(5_000)

        //Use UiAutomator to control the external login page
        val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        val selector = UiSelector()
        //Select the username field by its ID
        val username = device.findObject(selector.resourceId("1-email"))
        username.click()
        username.text = "deneve.thomas@gmail.com"
        //Password has no ID. TAB to it, and get the focused object
        device.pressKeyCode(KeyEvent.KEYCODE_TAB)
        Thread.sleep(1_000)
        val password = device.findObject(selector.focused(true))
        password.text = "P@ssword001!"
        //The login button also has no ID. TAB to it and press enter
        device.pressKeyCode(KeyEvent.KEYCODE_TAB)
        device.pressKeyCode(KeyEvent.KEYCODE_TAB)
        device.pressEnter()

        Thread.sleep(5_000)
    }

    @After
    fun endTest() {
        onView(withId(R.id.profileFragment)).perform(click())
        Thread.sleep(1_000)
        onView(withId(R.id.btnLogout)).perform(click())
    }

    @Test
    fun tableIsDisplayed() {
        onView(withId(R.id.reservations_table)).check(matches(isEnabled()))
    }

    @Test
    fun reservationsListItemCount() {
        onView(withId(R.id.reservations_list)).check(RecyclerViewItemCountAssertion(30))
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
}
