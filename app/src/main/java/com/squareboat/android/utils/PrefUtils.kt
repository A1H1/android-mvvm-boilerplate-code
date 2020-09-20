package com.squareboat.android.utils

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.squareboat.android.BoilerplateApplication
import com.squareboat.android.utils.AppConst.PREF_FILE
import com.squareboat.android.utils.AppConst.PREF_SAMPLE

object PrefUtils {
    private lateinit var context: Context

    private fun initialize(): SharedPreferences {
        if (!PrefUtils::context.isInitialized) {
            context = BoilerplateApplication.instance
        }

        return context.getSharedPreferences(PREF_FILE, MODE_PRIVATE)
    }

    fun setSampleString(data: String) {
        val pref = initialize().edit()
        pref.putString(PREF_SAMPLE, data)
        pref.apply()
    }

    fun getSampleString(): String? {
        val pref = initialize()
        if (!pref.contains(PREF_SAMPLE)) {
            return null
        }

        return pref.getString(PREF_SAMPLE, "")
    }
}