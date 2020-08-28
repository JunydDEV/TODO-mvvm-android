package com.reminder

import android.view.View
import android.widget.CheckBox
import android.widget.DatePicker
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.PickerActions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.reminder.ui.MainActivity
import org.hamcrest.Matchers
import org.hamcrest.Matchers.any
import org.hamcrest.Matchers.equalTo
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.regex.Matcher


@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testRecyclerView(){
        onView(withId(R.id.recyclerViewReminders)).check(matches(isDisplayed()))
    }

    @Test
    fun createNewTask(){
        onView(withId(R.id.textViewCreateTask)).perform(click())
        onView(withId(R.id.editTextNotesTitle)).perform(replaceText("Test Note"))
        onView(withId(R.id.editTextNotes)).perform(replaceText("Test Note Description"))
        onView(withId(R.id.textViewChooseDate)).perform(click())

        onView(withClassName(equalTo(DatePicker::class.java.name)))
            .perform(PickerActions.setDate(2020, 10, 20))

        onView(withText("OK")).perform(click())
        onView(withId(R.id.buttonSaveNote)).perform(click())

    }

    @Test
    fun createNewTaskFromFab(){
        onView(withId(R.id.fab)).perform(click())
        onView(withId(R.id.editTextNotesTitle)).perform(replaceText("Test Note"))
        onView(withId(R.id.editTextNotes)).perform(replaceText("Test Note Description"))
        onView(withId(R.id.textViewChooseDate)).perform(click())

        onView(withClassName(equalTo(DatePicker::class.java.name)))
            .perform(PickerActions.setDate(2020, 10, 20))

        onView(withText("OK")).perform(click())
        onView(withId(R.id.buttonSaveNote)).perform(click())
    }

    @Test
    fun testDeletePopup(){
        onView(withId(R.id.recyclerViewReminders)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, longClick()))
        onView(withId(R.id.layoutDeleteItem)).check(matches(isDisplayed()))
        onView(withId(R.id.imageViewDelete)).perform(longClick())
    }

    @Test
    fun testTaskCompletion(){
        onView(withId(R.id.recyclerViewReminders)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,MyViewAction.clickChildViewWithId(R.id.checkboxCompleteTask)))
    }

    object MyViewAction {
        fun clickChildViewWithId(id: Int): ViewAction {
            return object : ViewAction {

                override fun getDescription(): String {
                    return "Click on a child view with specified id."
                }

                override fun getConstraints(): org.hamcrest.Matcher<View> {
                    return any(View::class.java)
                }

                override fun perform(
                    uiController: UiController,
                    view: View
                ) {
                    val v = view.findViewById<CheckBox>(id)
                    v.isChecked = !v.isChecked
                }
            }
        }
    }

}