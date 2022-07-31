package com.ariefzuhri.discovergoogle.common.util

import android.content.Context
import com.ariefzuhri.discovergoogle.common.helper.FuzzyDateTimeFormatter
import java.time.Instant
import java.util.*

fun String?.toFuzzyTime(context: Context?): String {
    val instant = Instant.parse(this)
    val date = Date.from(instant)
    return FuzzyDateTimeFormatter.getTimeAgo(context, date)
}