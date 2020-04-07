package me.tatocaster.covid_19geocount.base

import androidx.annotation.UiThread
import androidx.fragment.app.Fragment
import me.tatocaster.covid_19geocount.CovidStatsApplication
import me.tatocaster.covid_19geocount.common.di.presentation.PresentationComponent
import me.tatocaster.covid_19geocount.common.di.presentation.PresentationModule

abstract class BaseFragment : Fragment() {
    private var isInjectorUsed: Boolean = false

    @UiThread
    protected fun getPresentationComponent(): PresentationComponent {
        if (isInjectorUsed) {
            throw RuntimeException("there is no need to use injector more than once")
        }
        isInjectorUsed = true
        return getApplicationComponent()
            .newPresentationComponent(PresentationModule(activity!!))

    }

    private fun getApplicationComponent() = CovidStatsApplication.applicationComponent

}