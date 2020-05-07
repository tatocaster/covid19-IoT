package me.tatocaster.core_android_testing.utils

import io.reactivex.schedulers.Schedulers
import me.tatocaster.core.source.BaseSchedulerProvider

class TrampolineSchedulerProvider : BaseSchedulerProvider {
    override fun computation() = Schedulers.trampoline()
    override fun ui() = Schedulers.trampoline()
    override fun io() = Schedulers.trampoline()
}