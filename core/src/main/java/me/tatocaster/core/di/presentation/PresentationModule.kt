package me.tatocaster.core.di.presentation

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import dagger.Module
import dagger.Provides
import me.tatocaster.core.di.qualifierscopes.ApplicationContext

@Module
class PresentationModule(private val activity: FragmentActivity) {

    @Provides
    fun getFragmentManager(): FragmentManager = activity.supportFragmentManager

    @Provides
    fun getLayoutInflater(): LayoutInflater = LayoutInflater.from(activity)

    @Provides
    fun getActivity(): Activity = activity

    @Provides
    fun activityContext(activity: Activity): Context = activity

    @Provides
    @ApplicationContext
    fun appContextFromActivity(activity: Activity) : Context = activity.applicationContext

}