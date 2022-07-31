package com.ariefzuhri.discovergoogle.common.util

import android.content.Context
import android.graphics.Bitmap
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import coil.load
import coil.transform.CircleCropTransformation
import com.ariefzuhri.discovergoogle.R

fun Context?.getBitmapFromDrawable(drawableId: Int): Bitmap? {
    return this?.let { context ->
        ContextCompat.getDrawable(context, drawableId)
    }?.toBitmap()
}

fun ImageView?.load(source: Any?) {
    this?.load(source) {
        placeholder(R.drawable.ic_placeholder_24)
        error(R.drawable.ic_error_24)
        crossfade(true)
        transformations(CircleCropTransformation())
    }
}