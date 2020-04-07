package me.tatocaster.covid_19geocount.common

import androidx.test.rule.ActivityTestRule
import me.tatocaster.covid_19geocount.common.activities.MainActivity
import org.junit.Before
import org.junit.Rule
import org.junit.rules.ExpectedException


open class BaseInstrumentedTest {
    @Before
    open fun before(){

    }

    @get:Rule
    var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(
        MainActivity::class.java
    )

    @get:Rule
    var exceptionRule: ExpectedException = ExpectedException.none()

}