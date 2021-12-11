package com.rakamin.alodokter.ui.register

import android.view.View
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.rakamin.alodokter.R
import org.hamcrest.Matcher
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RegisterFragmentTest {

    private lateinit var scenario: FragmentScenario<RegisterFragment>

    @Before
    fun setup() {
        scenario = launchFragmentInContainer(themeResId = R.style.Theme_AppCompat_Light_NoActionBar)
        scenario.moveToState(Lifecycle.State.STARTED)
    }

    @Test
    fun fieldInputValidation() {
        val name = ""
        val email = "emailsalah"
        val password = "1122334"
        val confirmPassword = "1111111"

        onView(withId(R.id.edt_full_name)).perform(typeText(name))
        onView(withId(R.id.edt_email)).perform(typeText(email))
        onView(withId(R.id.edt_password)).perform(typeText(password))
        onView(withId(R.id.edt_confirm_password)).perform(typeText(confirmPassword))
        onView(isRoot()).perform(waitFor(5000L))
    }

    private fun waitFor(delay: Long): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> = isRoot()
            override fun getDescription(): String = "wait please.."
            override fun perform(uiController: UiController?, view: View?) {
                uiController?.loopMainThreadForAtLeast(delay)
            }
        }
    }

}