package com.squareboat.android.base

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import butterknife.ButterKnife
import com.squareboat.android.R
import com.squareboat.android.data.model.ErrorResponse
import dagger.android.support.DaggerAppCompatActivity

abstract class BaseActivity : DaggerAppCompatActivity(), BaseErrorInterface {
    @LayoutRes
    protected abstract fun layoutRes(): Int
    protected open fun init() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutRes())
        ButterKnife.bind(this)
        init()
    }

    override fun showInternetError() {
        Toast.makeText(this, R.string.no_internet, Toast.LENGTH_LONG).show()
    }

    override fun showSocketTimeoutError() {
        Toast.makeText(this, R.string.no_internet, Toast.LENGTH_LONG).show()
    }

    override fun showOtherError(errorResponse: ErrorResponse) {
        Toast.makeText(
            this,
            errorResponse.message ?: getString(R.string.something_went_wrong),
            Toast.LENGTH_LONG
        ).show()
    }
}