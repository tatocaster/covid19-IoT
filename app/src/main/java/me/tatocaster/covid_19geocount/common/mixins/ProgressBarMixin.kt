package me.tatocaster.covid_19geocount.common.mixins

import me.tatocaster.covid_19geocount.base.JustLoading
import me.tatocaster.covid_19geocount.base.Payload
import me.tatocaster.covid_19geocount.base.State
import me.tatocaster.covid_19geocount.common.views.CustomProgressBar


interface ProgressBarMixin {

    fun applyProgressbarMixin(state: State<Payload>, progressbarView: CustomProgressBar) {
        if (state is JustLoading) {
            progressbarView.show()
        } else {
            progressbarView.hide()
        }
    }

}