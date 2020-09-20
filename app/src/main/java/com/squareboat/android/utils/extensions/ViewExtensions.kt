package com.squareboat.android.utils.extensions

import android.graphics.Color
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.google.android.material.textfield.TextInputLayout

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.show(enable: Boolean) {
    visibility = if (enable) View.VISIBLE else View.GONE
}

fun View.invisible(enable: Boolean) {
    visibility = if (enable) View.INVISIBLE else View.VISIBLE
}

fun View.isVisible() = visibility == View.VISIBLE

fun TextView.setColor(color: Int) {
    setTextColor(color)
}

fun ImageView.setColor(color: Int) {
    setColorFilter(color, android.graphics.PorterDuff.Mode.SRC_IN)
}

fun View.setBackgroundTint(color: Int) {
    backgroundTintList = ContextCompat.getColorStateList(context, color)
}

fun AlertDialog.show(enable: Boolean) {
    if (enable) show() else dismiss()
}

fun ImageView.parseColor(color: String?, fallback: Int) {
    try {
        setColor(Color.parseColor(color))
    } catch (e: Exception) {
        setImageDrawable(ContextCompat.getDrawable(context, fallback))
    }
}

fun TextInputLayout.clearError() {
    error = null
    isErrorEnabled = false
}

fun EditText.setNullableText(text: String?) {
    text?.let { setText(it) }
}

fun EditText.setNullableText(text: Int?) {
    text?.let { setText(it.toString()) }
}

fun View.changeColor(color: Int) {
    this.background.setTintList(ContextCompat.getColorStateList(context, color))
}