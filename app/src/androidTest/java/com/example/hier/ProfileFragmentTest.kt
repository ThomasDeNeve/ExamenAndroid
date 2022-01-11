package com.example.hier

import android.view.KeyEvent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiSelector
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ProfileFragmentTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp() {
        login()
    }

    private fun login() {
        Thread.sleep(5_000)

        //Use UiAutomator to control the external login page
        var device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        val selector = UiSelector()
        //Select the username field by its ID
        var username = device.findObject(selector.resourceId("1-email"))
        username.click()
        username.text = "deneve.thomas@gmail.com"
        //Password has no ID. TAB to it, and get the focused object
        device.pressKeyCode(KeyEvent.KEYCODE_TAB);
        Thread.sleep(1_000)
        var password = device.findObject(selector.focused(true))
        password.text = "P@ssword001!"
        //The login button also has no ID. TAB to it and press enter
        device.pressKeyCode(KeyEvent.KEYCODE_TAB);
        device.pressKeyCode(KeyEvent.KEYCODE_TAB);
        device.pressEnter()

        Thread.sleep(5_000)
        onView(withId(R.id.profileFragment)).perform(click())
    }

    @After
    fun endTest() {
        onView(withId(R.id.btnLogout)).perform(click())
    }

    @Test
    fun testUIElements() {
        Thread.sleep(3_000)
        onView(withId(R.id.txvProfileFirstName)).check(matches(isEnabled()))
        onView(withId(R.id.txvProfileName)).check(matches(isEnabled()))
        onView(withId(R.id.txvProfileEmail)).check(matches(isEnabled()))
        onView(withId(R.id.txvProfileTel)).check(matches(isEnabled()))
        onView(withId(R.id.txvProfileBTW)).check(matches(isEnabled()))

        onView(withId(R.id.lblProfileFirstName)).check(matches(isEnabled()))
        onView(withId(R.id.lblProfileName)).check(matches(isEnabled()))
        onView(withId(R.id.lblProfileEmail)).check(matches(isEnabled()))
        onView(withId(R.id.lblProfileTel)).check(matches(isEnabled()))
        onView(withId(R.id.lblProfileBTW)).check(matches(isEnabled()))

        onView(withId(R.id.btnLogout)).check(matches(isEnabled()))

        onView(withId(R.id.lblProfileFirstName)).check(matches(withText("Thomas")))
        onView(withId(R.id.lblProfileName)).check(matches(withText("De Neve")))
        onView(withId(R.id.lblProfileEmail)).check(matches(withText("deneve.thomas@gmail.com")))
        onView(withId(R.id.lblProfileTel)).check(matches(withText("04 12 34 56 78")))
        onView(withId(R.id.lblProfileBTW)).check(matches(withText("")))
    }
/*
    @Test
    fun testLogoutLogin() {
        Thread.sleep(3_000)
        onView(withId(R.id.btnLogout)).perform(click())
        Thread.sleep(3_000)
    }*/
}
