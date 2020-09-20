package com.squareboat.android.ui.dashboard

import android.content.Context
import android.content.Intent
import com.squareboat.android.R
import com.squareboat.android.base.BaseActivity
import com.squareboat.android.utils.AppUtils.addCleanIntent

class DashboardActivity : BaseActivity() {
    companion object {
        fun launch(context: Context, isCleanRequired: Boolean = false) {
            val intent = Intent(context, DashboardActivity::class.java)
            if (isCleanRequired)
                intent.flags = addCleanIntent()

            context.startActivity(intent)
        }
    }

    override fun layoutRes() = R.layout.activity_dashboard
}