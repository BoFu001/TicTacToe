package com.bofu.tictactoetdd

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith



@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {

    @get: Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)


    @Test
    fun AreBtnsInView(){
        onView(withId(R.id.btn_one)).check(matches(ViewMatchers.isDisplayed()))
    }
}