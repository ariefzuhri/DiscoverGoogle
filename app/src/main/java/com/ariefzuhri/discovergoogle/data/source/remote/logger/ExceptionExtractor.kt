package com.ariefzuhri.discovergoogle.data.source.remote.logger

import android.content.Context
import com.ariefzuhri.discovergoogle.R
import com.ariefzuhri.discovergoogle.data.source.remote.response.ErrorResponse
import com.squareup.moshi.Moshi
import retrofit2.HttpException
import java.io.IOException

class ExceptionExtractor(
    private val context: Context,
) : IExceptionExtractor<ErrorResponse> {

    private val moshiAdapter by lazy {
        Moshi.Builder().build().adapter(ErrorResponse::class.java)
    }

    override fun getMessage(t: Throwable?): String {
        return when (t) {
            is IOException -> context.getString(R.string.toast_failed_io_remote)
            is HttpException -> context.getString(R.string.toast_failed_http_remote,
                deserializeErrorBody(t)?.message,
                t.code())
            else -> t?.message.orEmpty()
        }
    }

    override fun deserializeErrorBody(e: HttpException?): ErrorResponse? {
        val errorBody = e?.response()?.errorBody()
        return moshiAdapter.fromJson(errorBody?.string().orEmpty())
    }
}