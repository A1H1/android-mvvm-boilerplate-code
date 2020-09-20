package com.squareboat.android.di.module

import com.squareboat.android.di.util.ActivityScoped
import com.squareboat.android.ui.dashboard.DashboardActivity
import com.squareboat.android.ui.splash.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector
    abstract fun bindSplashActivity(): SplashActivity

    @ActivityScoped
    @ContributesAndroidInjector
    abstract fun bindDashboardActivity(): DashboardActivity
}