package com.squareboat.android.utils

import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.os.Build
import android.util.TypedValue
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.inputmethod.InputMethodManager
import com.squareboat.android.BoilerplateApplication

object AppUtils {
    private var screenWidth = 0
    private var screenHeight = 0
    private val fadeIn = AlphaAnimation(0.0f, 1.0f)
    private val fadeOut = AlphaAnimation(1.0f, 0.0f)

    fun addCleanIntent() = Intent.FLAG_ACTIVITY_NEW_TASK or
            Intent.FLAG_ACTIVITY_CLEAR_TOP or
            Intent.FLAG_ACTIVITY_SINGLE_TOP or
            Intent.FLAG_ACTIVITY_CLEAR_TASK

    fun isNetworkAvailable(): Boolean {
        val connectivityManager =
            BoilerplateApplication.instance.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val nw = connectivityManager.activeNetwork ?: return false
            val actNw = connectivityManager.getNetworkCapabilities(nw) ?: return false
            return when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
                else -> false
            }
        } else {
            val nwInfo = connectivityManager.activeNetworkInfo ?: return false
            return nwInfo.isConnected
        }
    }

    fun showFadeInEffect(view: View) {
        view.startAnimation(fadeIn)
        fadeIn.duration = 500
        fadeIn.fillAfter = true
    }

    fun showFadeOutEffect(view: View) {
        view.startAnimation(fadeOut)
        fadeOut.duration = 500
        fadeOut.fillAfter = true
    }

    fun dpToPx(dp: Float, context: Context): Int {
        return dpToPx(dp, context.resources)
    }

    fun dpToPx(dp: Float, resources: Resources): Int {
        val px =
            TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.displayMetrics)
        return px.toInt()
    }

    fun hideKeyboardFromView(context: Context, view: View) {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun showKeyboard(context: Context, view: View?) {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(view, InputMethodManager.SHOW_FORCED)
    }

    fun requestFocus(view: View) {
        if (view.requestFocus()) {
            hideKeyboardFromView(view.context, view)
        }
    }

    fun openDialer(context: Context, phoneNumber: String) {
        val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
        if(intent.resolveActivity(context.packageManager) != null)
            context.startActivity(intent)
    }

    fun openEmail(context: Context, email: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("mailto:$email"))
        if(intent.resolveActivity(context.packageManager) != null)
            context.startActivity(intent)
    }

    fun openLink(context: Context, url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        if(intent.resolveActivity(context.packageManager) != null)
            context.startActivity(intent)
    }
}
