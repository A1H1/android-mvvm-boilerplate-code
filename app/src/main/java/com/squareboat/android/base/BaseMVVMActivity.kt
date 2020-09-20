package com.squareboat.android.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.squareboat.android.di.util.ViewModelFactory
import javax.inject.Inject

abstract class BaseMVVMActivity<T : ViewModel> : BaseActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var viewModel: T

    override fun init() {
        super.init()
        viewModel = ViewModelProvider(this, viewModelFactory).get(viewModel.javaClass)
        observers()
    }

    abstract fun observers()

    abstract fun showLoading(isLoading: Boolean)
}