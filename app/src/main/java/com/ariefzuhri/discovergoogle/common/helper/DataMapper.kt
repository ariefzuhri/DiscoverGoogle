package com.ariefzuhri.discovergoogle.common.helper

import com.ariefzuhri.discovergoogle.data.source.remote.response.NewsResponse
import com.ariefzuhri.discovergoogle.domain.model.Article

fun NewsResponse.ArticlesItem?.toDomain(): Article {
    return Article(
        title = this?.title.orEmpty(),
        author = if (this?.author == "Unknown") "" else this?.author.orEmpty(),
        url = this?.url.orEmpty(),
        imageUrl = this?.urlToImage.orEmpty(),
        publishedTime = this?.publishedAt.orEmpty(),
    )
}