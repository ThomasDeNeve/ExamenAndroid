package com.example.hier

import android.provider.ContactsContract
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.hier.ui.profile.ProfileFragment
import com.example.hier.ui.reservations.ReservationsFragment
import org.hamcrest.CoreMatchers.not
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

class ProfileFragmentTest {
    @Before
    fun setUp() {
        launchFragmentInContainer<ProfileFragment>(
            fragmentArgs = null,
            themeResId = R.style.Theme_MaterialComponents,
            initialState = Lifecycle.State.RESUMED,
            factory = null
        )
    }

    @Test
    fun testUIelements()
    {
        onView(withId(R.id.btnLogout)).check(matches(isEnabled()))
    }
}