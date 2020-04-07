package me.tatocaster.covid_19geocount.common.di.presentation

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class PresentationModule(private val activity: FragmentActivity) {

    @Provides
    fun getFragmentManager(): FragmentManager = activity.supportFragmentManager

    @Provides
    fun getLayoutInflater(): LayoutInflater = LayoutInflater.from(activity)

    @Provides
    fun getActivity(): Activity = activity

    @Provides
    fun context(activity: Activity): Context = activity

}