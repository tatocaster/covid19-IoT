package me.tatocaster.core.mixins

import me.tatocaster.core.base.JustLoading
import me.tatocaster.core.base.Payload
import me.tatocaster.core.base.State
import me.tatocaster.core.views.CustomProgressBar


interface ProgressBarMixin {

    fun applyProgressbarMixin(state: State<Payload>, progressbarView: CustomProgressBar) {
        if (state is JustLoading) {
            progressbarView.show()
        } else {
            progressbarView.hide()
        }
    }

}