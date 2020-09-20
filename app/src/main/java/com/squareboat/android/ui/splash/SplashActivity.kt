package com.squareboat.android.ui.splash

import com.squareboat.android.R
import com.squareboat.android.base.BaseActivity
import com.squareboat.android.ui.dashboard.DashboardActivity
import kotlinx.android.synthetic.main.activity_splash.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : BaseActivity() {
    override fun layoutRes() = R.layout.activity_splash

    override fun init() {
        CoroutineScope(Dispatchers.Main).launch {
            for (i in 1..4) {
                delay(1000)
                splashCounterTextView.text = i.toString()
                if (i == 3) {
                    DashboardActivity.launch(this@SplashActivity, isCleanRequired = true)
                        .apply { finish() }
                }
            }
        }
    }
}