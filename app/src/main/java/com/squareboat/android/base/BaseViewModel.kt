package com.squareboat.android.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.squareboat.android.data.model.DataWrapper
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel<T> : ViewModel() {
    protected val disposable = CompositeDisposable()
    protected val data = MutableLiveData<DataWrapper<T>>()
    val response: LiveData<DataWrapper<T>> = data

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}