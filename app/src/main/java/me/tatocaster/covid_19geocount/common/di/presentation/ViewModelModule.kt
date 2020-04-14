package me.tatocaster.covid_19geocount.common.di.presentation

import androidx.lifecycle.ViewModel
import dagger.MapKey
import dagger.Module
import dagger.Provides
import me.tatocaster.covid_19geocount.common.CovidStatsViewModelFactory
import javax.inject.Provider
import kotlin.reflect.KClass


@Module
object ViewModelModule {
    @MustBeDocumented
    @Target(
        AnnotationTarget.FUNCTION,
        AnnotationTarget.PROPERTY_GETTER,
        AnnotationTarget.PROPERTY_SETTER
    )
    @Retention(AnnotationRetention.RUNTIME)
    @MapKey
    annotation class ViewModelKey(val value: KClass<out ViewModel>)

    @Provides
    fun viewModelFactory(
        providerMap: Map<Class<out ViewModel>,
                @JvmSuppressWildcards Provider<ViewModel>>
    ): CovidStatsViewModelFactory {
        return CovidStatsViewModelFactory(providerMap)
    }

}
