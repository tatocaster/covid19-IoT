package me.tatocaster.core.di.application

import android.app.Activity
import android.app.Service
import androidx.fragment.app.Fragment

interface CoreComponentProvider {
      val coreComponent: CoreComponent
}

val Activity.injector get() = (application as CoreComponentProvider).coreComponent
val Fragment.injector get() = (activity?.application as CoreComponentProvider).coreComponent
val Service.injector get() = (application as CoreComponentProvider).coreComponent