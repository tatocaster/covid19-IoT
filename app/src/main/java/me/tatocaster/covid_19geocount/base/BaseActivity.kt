package me.tatocaster.covid_19geocount.base

import androidx.annotation.UiThread
import androidx.appcompat.app.AppCompatActivity
import me.tatocaster.covid_19geocount.CovidStatsApplication
import me.tatocaster.covid_19geocount.common.di.presentation.PresentationComponent
import me.tatocaster.covid_19geocount.common.di.presentation.PresentationModule

abstract class BaseActivity : AppCompatActivity() {
    private var isInjectorUsed = false

    @UiThread
    protected fun getPresentationComponent(): PresentationComponent {
        if (isInjectorUsed) {
            throw IllegalStateException("there is no need to use injector more than once")
        }
        isInjectorUsed = true
        return getApplicationComponent()
            .newPresentationComponent(PresentationModule(this))
    }

    private fun getApplicationComponent() = CovidStatsApplication.applicationComponent
}