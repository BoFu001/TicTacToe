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
    fun AreBtnOneInView(){
        onView(withId(R.id.btn_one)).check(matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun AreBtnTwoInView(){
        onView(withId(R.id.btn_two)).check(matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun AreBtnThreeInView(){
        onView(withId(R.id.btn_three)).check(matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun AreBtnFourInView(){
        onView(withId(R.id.btn_four)).check(matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun AreBtnFiveInView(){
        onView(withId(R.id.btn_five)).check(matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun AreBtnSixInView(){
        onView(withId(R.id.btn_six)).check(matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun AreBtnSevenInView(){
        onView(withId(R.id.btn_seven)).check(matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun AreBtnEightInView(){
        onView(withId(R.id.btn_eight)).check(matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun AreBtnNineInView(){
        onView(withId(R.id.btn_nine)).check(matches(ViewMatchers.isDisplayed()))
    }
}