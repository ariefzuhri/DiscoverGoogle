package com.ariefzuhri.discovergoogle.domain.model

import android.content.Context
import com.ariefzuhri.discovergoogle.common.util.toFuzzyTime

data class Article(
    val title: String,
    val author: String,
    val url: String,
    val imageUrl: String,
    val publishedTime: String,
) {

    fun getFuzzyPublishedTime(context: Context): String {
        return publishedTime.toFuzzyTime(context)
    }
}