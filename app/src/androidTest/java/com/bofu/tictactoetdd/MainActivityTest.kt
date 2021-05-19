package com.bofu.tictactoetdd

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
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
    fun btnOneTest(){
        onView(withId(R.id.btn_one)).check(matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun btnTwoTest(){
        onView(withId(R.id.btn_two)).check(matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun btnThreeTest(){
        onView(withId(R.id.btn_three)).check(matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun btnFourTest(){
        onView(withId(R.id.btn_four)).check(matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun btnFiveTest(){
        onView(withId(R.id.btn_five)).check(matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun btnSixTest(){
        onView(withId(R.id.btn_six)).check(matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun btnSevenTest(){
        onView(withId(R.id.btn_seven)).check(matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun btnEightTest(){
        onView(withId(R.id.btn_eight)).check(matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun btnNineTest(){
        onView(withId(R.id.btn_nine)).check(matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun btnPlayTest(){
        onView(withId(R.id.btn_one)).perform(click())
        onView(withId(R.id.btn_one)).check(matches(withText("X")))
        onView(withId(R.id.btn_two)).perform(click())
        onView(withId(R.id.btn_two)).check(matches(withText("O")))
        onView(withId(R.id.btn_three)).perform(click())
        onView(withId(R.id.btn_three)).check(matches(withText("X")))
        onView(withId(R.id.btn_four)).perform(click())
        onView(withId(R.id.btn_four)).check(matches(withText("O")))
        onView(withId(R.id.btn_five)).perform(click())
        onView(withId(R.id.btn_five)).check(matches(withText("X")))
        onView(withId(R.id.btn_six)).perform(click())
        onView(withId(R.id.btn_six)).check(matches(withText("O")))
        onView(withId(R.id.btn_seven)).perform(click())
        onView(withId(R.id.btn_seven)).check(matches(withText("X")))
        onView(withId(R.id.btn_eight)).perform(click())
        onView(withId(R.id.btn_eight)).check(matches(withText("O")))
        onView(withId(R.id.btn_nine)).perform(click())
        onView(withId(R.id.btn_nine)).check(matches(withText("X")))
    }
}