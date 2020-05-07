package me.tatocaster.core_android_testing.base

import android.app.Activity
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.MediumTest
import androidx.test.rule.ActivityTestRule
import me.tatocaster.core.base.BaseActivity
import me.tatocaster.core_android_testing.view.SingleFragmentActivity
import org.junit.Before
import org.junit.Rule
import org.junit.rules.ExpectedException
import kotlin.reflect.KClass

@MediumTest
abstract class BaseInstrumentedTest {
    @Before
    fun before() {
        onPrepareInjection()
        setContentFragment()
    }

    /*@Rule
    @JvmField
    var activityRule: ActivityTestRule<SingleFragmentActivity> = ActivityTestRule(SingleFragmentActivity::class.java)*/

    @Rule
    @JvmField
    var activityScenarioRule: ActivityScenarioRule<SingleFragmentActivity> =
        ActivityScenarioRule(SingleFragmentActivity::class.java)

    @Rule
    @JvmField
    var exceptionRule: ExpectedException = ExpectedException.none()

    abstract fun onRequestFragment(): Fragment

    open fun onPrepareInjection() {}

    private fun setContentFragment() {
        activityScenarioRule.scenario.onActivity { activity ->
            activity.supportFragmentManager.attach(android.R.id.content, onRequestFragment())
        }
    }

    private fun FragmentManager.attach(@IdRes placeHolder: Int, fragment: Fragment) {
        val tag = getTag(fragment::class)
        beginTransaction()
            .replace(placeHolder, fragment, tag)
            .commitAllowingStateLoss()
    }

    private fun getTag(type: KClass<*>): String = type.java.name

}