package com.example.hier

import android.view.KeyEvent
import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onData

import androidx.test.espresso.NoMatchingViewException

import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiSelector
import org.hamcrest.CoreMatchers.*
import org.junit.After

//A lot of "sleeps" are added to make it easier to follow along visually
//This should not be done in production
class MeetingRoomFragmentTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp() {
        login()
        onView(withId(R.id.choiceMeetingRoomFragment)).perform(click())
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
    fun filterNumberOfMeetingRooms() {
        onView(withId(R.id.btnLocOne)).perform(click())
        //Items in RecyclerView when first opening
        onView(withId(R.id.room_list)).check(RecyclerViewItemCountAssertion(3))
        Thread.sleep(1_000)
        //Items in RecyclerView after filtering on capacity
        onView(withId(R.id.seatSpinner)).perform(click())
        onData(allOf(`is`(instanceOf(Int::class.java)), `is`(14))).perform(click())
        onView(withId(R.id.room_list)).check(RecyclerViewItemCountAssertion(2))
        Thread.sleep(1_000)
        onView(withId(R.id.room_list)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()));
        Thread.sleep(1_000)
        onView(withId(R.id.btn_reserve)).perform(scrollTo(), click())
        Thread.sleep(5_000)
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
