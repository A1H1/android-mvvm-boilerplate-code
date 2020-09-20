package com.squareboat.android.di.module

import dagger.Module

@Module(includes = [ViewModelModule::class, NetworkModule::class])
class ApplicationModule