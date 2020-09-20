package com.squareboat.android.di.module

import androidx.lifecycle.ViewModelProvider
import com.squareboat.android.di.util.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}