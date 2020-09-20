package com.squareboat.android.utils.extensions

import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import java.io.File

fun ImageView.loadUrl(url: String, isRounded: Boolean = false, placeholder: Int? = null) {
    Glide.with(this).load(url).apply {
        if (isRounded) circleCrop()
        placeholder?.let { placeholder(placeholder) }
    }.into(this)
}

fun ImageView.loadUri(uri: Uri, isRounded: Boolean = false) {
    Glide.with(this).load(uri).apply {
        if (isRounded) circleCrop()
    }.into(this)
}

fun ImageView.loadFile(file: File, isRounded: Boolean = false, placeholder: Int? = null) {
    Glide.with(this).load(file).apply {
        if (isRounded) circleCrop()
        placeholder?.let { placeholder(placeholder) }
    }.into(this)
}