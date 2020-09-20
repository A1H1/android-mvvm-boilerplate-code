package com.squareboat.android.di

import android.app.Application
import com.squareboat.android.BoilerplateApplication
import com.squareboat.android.di.module.ActivityBindingModule
import com.squareboat.android.di.module.ApplicationModule
import com.squareboat.android.di.module.ContextModule
import com.squareboat.android.di.module.DialogBindingModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication
import javax.inject.Singleton

@Singleton
@Component(modules = [ContextModule::class, ApplicationModule::class, AndroidSupportInjectionModule::class, ActivityBindingModule::class, DialogBindingModule::class])
interface ApplicationComponent : AndroidInjector<DaggerApplication> {
    fun inject(application: BoilerplateApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application?): Builder?

        fun build(): ApplicationComponent?
    }
}