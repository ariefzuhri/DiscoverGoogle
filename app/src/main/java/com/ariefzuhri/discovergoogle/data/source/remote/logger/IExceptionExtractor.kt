package com.ariefzuhri.discovergoogle.data.source.remote.logger

import retrofit2.HttpException

interface IExceptionExtractor<ErrorResponse> {

    fun getMessage(t: Throwable?): String

    fun deserializeErrorBody(e: HttpException?): ErrorResponse?
}