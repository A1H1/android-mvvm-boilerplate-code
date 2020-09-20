package com.squareboat.android

import com.squareboat.android.di.ApplicationComponent
import com.squareboat.android.di.DaggerApplicationComponent
import dagger.android.support.DaggerApplication

class BoilerplateApplication : DaggerApplication() {
    companion object {
        lateinit var instance: BoilerplateApplication
    }

    override fun applicationInjector(): ApplicationComponent? {
        val component: ApplicationComponent? =
            DaggerApplicationComponent.builder().application(this)?.build()
        component?.inject(this)
        return component
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}