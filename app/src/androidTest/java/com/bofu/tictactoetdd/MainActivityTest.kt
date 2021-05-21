package com.bofu.tictactoetdd

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.bofu.tictactoetdd.activities.MainActivity
import com.bofu.tictactoetdd.adapters.MainAdapter
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest{

    @get: Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun recyclerview_test(){
        onView(withId(R.id.main_recycler_view)).check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.main_recycler_view)).perform(actionOnItemAtPosition<MainAdapter.MainHolder>(0,click()))
        onView(withId(R.id.main_recycler_view)).perform(actionOnItemAtPosition<MainAdapter.MainHolder>(1,click()))
        onView(withId(R.id.main_recycler_view)).perform(actionOnItemAtPosition<MainAdapter.MainHolder>(2,click()))
        onView(withId(R.id.main_recycler_view)).perform(actionOnItemAtPosition<MainAdapter.MainHolder>(3,click()))
        onView(withId(R.id.main_recycler_view)).perform(actionOnItemAtPosition<MainAdapter.MainHolder>(4,click()))
        onView(withId(R.id.main_recycler_view)).perform(actionOnItemAtPosition<MainAdapter.MainHolder>(5,click()))
        onView(withId(R.id.main_recycler_view)).perform(actionOnItemAtPosition<MainAdapter.MainHolder>(6,click()))
        onView(withId(R.id.main_recycler_view)).perform(actionOnItemAtPosition<MainAdapter.MainHolder>(7,click()))
        onView(withId(R.id.main_recycler_view)).perform(actionOnItemAtPosition<MainAdapter.MainHolder>(8,click()))
        onView(withId(R.id.main_recycler_view)).perform(actionOnItemAtPosition<MainAdapter.MainHolder>(9,click()))
        onView(withId(R.id.main_recycler_view)).perform(actionOnItemAtPosition<MainAdapter.MainHolder>(10,click()))
        onView(withId(R.id.main_recycler_view)).perform(actionOnItemAtPosition<MainAdapter.MainHolder>(11,click()))
        onView(withId(R.id.main_recycler_view)).perform(actionOnItemAtPosition<MainAdapter.MainHolder>(12,click()))

        onView(withId(R.id.btn_new_game)).perform(click())

        onView(withId(R.id.main_recycler_view)).check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.main_recycler_view)).perform(actionOnItemAtPosition<MainAdapter.MainHolder>(3,click()))
        onView(withId(R.id.main_recycler_view)).perform(actionOnItemAtPosition<MainAdapter.MainHolder>(2,click()))
        onView(withId(R.id.main_recycler_view)).perform(actionOnItemAtPosition<MainAdapter.MainHolder>(6,click()))
        onView(withId(R.id.main_recycler_view)).perform(actionOnItemAtPosition<MainAdapter.MainHolder>(5,click()))
        onView(withId(R.id.main_recycler_view)).perform(actionOnItemAtPosition<MainAdapter.MainHolder>(9,click()))
        onView(withId(R.id.main_recycler_view)).perform(actionOnItemAtPosition<MainAdapter.MainHolder>(8,click()))
        onView(withId(R.id.main_recycler_view)).perform(actionOnItemAtPosition<MainAdapter.MainHolder>(12,click()))

        onView(withId(R.id.btn_new_game)).perform(click())
    }


}