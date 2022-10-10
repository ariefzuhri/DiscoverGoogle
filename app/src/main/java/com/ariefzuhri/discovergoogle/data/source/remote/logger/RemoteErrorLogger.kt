package com.ariefzuhri.discovergoogle.data.source.remote.logger

import android.util.Log
import com.ariefzuhri.discovergoogle.data.source.remote.response.ErrorResponse

class RemoteErrorLogger(
    private val exceptionExtractor: IExceptionExtractor<ErrorResponse>,
) : ErrorLogger {

    override fun e(tag: String, message: String?): Exception {
        Log.e(tag, message.orEmpty())
        return Exception(message)
    }

    override fun e(tag: String, t: Throwable?): Exception {
        Log.e(tag, t?.stackTraceToString().orEmpty())
        val message = exceptionExtractor.getMessage(t)
        return Exception(message)
    }
}