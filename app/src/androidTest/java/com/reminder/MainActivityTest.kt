package com.reminder

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.reminder.ui.MainActivity
import org.hamcrest.Matchers.*

import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testRecyclerView(){
        onView(withId(R.id.recyclerViewReminders)).check(matches(isDisplayed()))
        onView(withId(R.id.recyclerViewReminders)).check{ view, _ ->  assertThat((view as RecyclerView).adapter?.itemCount,
           equalTo(4)
        )}
    }


}