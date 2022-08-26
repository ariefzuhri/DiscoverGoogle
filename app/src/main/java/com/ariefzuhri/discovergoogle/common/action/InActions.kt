package com.ariefzuhri.discovergoogle.common.action

import android.content.Context
import android.net.Uri
import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import com.ariefzuhri.discovergoogle.R
import com.ariefzuhri.discovergoogle.common.util.getBitmapFromDrawable

fun Context?.openCustomTabs(url: String?) {
    this?.let { context ->
        val colorScheme = CustomTabColorSchemeParams.Builder()
            .setToolbarColor(ContextCompat.getColor(context, R.color.blue))
            .build()

        CustomTabsIntent.Builder().apply {
            setShowTitle(true)
            setDefaultColorSchemeParams(colorScheme)
            getBitmapFromDrawable(R.drawable.ic_back_24)?.let { icon -> setCloseButtonIcon(icon) }
            build().launchUrl(context, Uri.parse(url))
        }
    }
}